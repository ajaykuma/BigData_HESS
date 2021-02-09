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

inputDF = spark.readStream.schema(schema). \
               option("maxFilesPerTrigger",1). \
               json(inputpath)

"""additional options to be tested for managing cleanup when reading 
               option("cleanSource", "archive").\
               option("sourceArchiveDir", "Processed").
               """

countDF = inputDF.groupby(inputDF.age).count()
query = countDF.writeStream.format("console").queryName("counts").outputMode("complete").start()
print(type(query))
"""import time
time.sleep(100)
query.stop()"""

"""OR"""
query.awaitTermination()

