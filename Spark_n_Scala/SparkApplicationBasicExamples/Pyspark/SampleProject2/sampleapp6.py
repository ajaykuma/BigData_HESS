""" with streaming """
from pyspark.sql.functions import current_timestamp
from pyspark.sql.types import IntegerType,StringType, StructType, StructField

from pyspark.sql import SparkSession
spark = SparkSession.builder.master("local").appName("sparkdfex").getOrCreate()
spark.conf.set("spark.sql.shuffle.partitions","2")
spark.conf.set("spark.sql.streaming.checkpointLocation","testtemp")

inputpath = "Data"
schema = StructType([StructField("name",StringType(),True),
                     StructField("age",IntegerType())])

inputDF = (spark.readStream.schema(schema).option("maxFilesPerTrigger",1).json(inputpath))
print(inputDF.isStreaming)

query = inputDF.writeStream.\
        format("parquet").option("path","DataLoaded").\
        outputMode("append").start()

print(type(query))

import time
time.sleep(20)
query.stop()

print(spark.read.load("DataLoaded/*.parquet").count())


"""query.awaitTermination()"""

