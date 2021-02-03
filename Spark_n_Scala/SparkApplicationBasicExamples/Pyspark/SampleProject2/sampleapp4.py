""" with streaming """
from pyspark.sql.types import IntegerType,StringType, StructType, StructField

from pyspark.sql import SparkSession
spark = SparkSession.builder.master("local").appName("sparkdfex").getOrCreate()
spark.conf.set("spark.sql.shuffle.partitions","2")

inputpath = "Data"
schema = StructType([StructField("name",StringType(),True),
                     StructField("age",IntegerType())])

inputDF = (spark.readStream.schema(schema).option("maxFilesPerTrigger",1).json(inputpath))
countDF = (inputDF.groupby(inputDF.name)).count()
print(countDF.isStreaming)

query = (countDF.writeStream.format("console").queryName("counts").outputMode("complete").start())
import time
time.sleep(80)
query.stop()