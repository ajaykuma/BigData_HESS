from pyspark.sql.functions import current_timestamp
from pyspark.sql.types import IntegerType,StringType, StructType, StructField

from pyspark.sql import SparkSession
spark = SparkSession.builder.master("local").appName("sparkdfex").getOrCreate()
spark.conf.set("spark.sql.shuffle.partitions","2")

""" without streaming """
"""
inputDF = (spark.read
           .option("header", "true")
           .format("csv")
           .load("Data4/*.csv"))
inputDF1= inputDF.filter(inputDF.age > 55).sort(inputDF.age.desc()).show(5)
inputDF.filter(inputDF.age > 55).groupby("age").count().toDF("age","count").show(5)"""

"""with streaming"""
spark.conf.set("spark.sql.streaming.checkpointLocation","testtemp")
schema = StructType([StructField("serNo",IntegerType(),True),
                     StructField("age",IntegerType(),True),
                     StructField("job",StringType(),True),
                     StructField("marital",StringType(),True),
                     StructField("education",StringType(),True),
                     StructField("defaulter",StringType(),True),
                     StructField("balance",IntegerType(),True),
                     StructField("housing",StringType(),True),
                     StructField("loan",StringType(),True),
                     StructField("contact",StringType(),True),
                     StructField("day",IntegerType(),True),
                     StructField("month",StringType(),True),
                     StructField("duration",IntegerType(),True),
                     StructField("campaign",IntegerType(),True),
                     StructField("pdays",IntegerType(),True),
                     StructField("previous",IntegerType(),True),
                     StructField("poutcome",StringType(),True),
                     StructField("y",StringType(),True)])

inputDF = (spark.readStream.schema(schema)
           .option("header", "true")
           .format("csv")
           .load("Data4"))
print(inputDF.isStreaming)
"""inputDF1 = inputDF.filter(inputDF.age > 55).sort(inputDF.age.desc())"""
inputDF2 = inputDF.filter(inputDF.age > 55).groupby("age").count().toDF("age","count")
"""query = (inputDF1.writeStream.format("console").queryName("counts").outputMode("update").start())"""
query = (inputDF2.writeStream.option("numRows",5).format("console").queryName("counts").outputMode("update").start())

"""import time
time.sleep(100)
query.stop()"""
query.awaitTermination()



