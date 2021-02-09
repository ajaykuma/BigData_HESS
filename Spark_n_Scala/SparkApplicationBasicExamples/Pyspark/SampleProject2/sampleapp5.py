""" without streaming """
from pyspark.sql.types import IntegerType,StringType, StructType, StructField
from pyspark.sql import SparkSession
spark = SparkSession.builder.master("local").appName("sparkdfex").getOrCreate()
spark.conf.set("spark.sql.shuffle.partitions","2")

inputpath = "Data2/*.json"
"""
x = spark.read.json("Data2",multiLine=True)
x.printSchema()

x.write.saveAsTable("Data5")
y = spark.read.load("spark-warehouse/data5")
y.show(5)
"""
"""with streaming"""
spark.conf.set("spark.sql.streaming.schemaInference","true")
inputDF = spark.readStream. \
               option("maxBytesPerTrigger",10000).\
               option("maxFilesPerTrigger",1).\
               json(inputpath)

query = inputDF.writeStream.\
        option("numrows",5).\
        format("console").\
        trigger(processingTime='3 seconds'). \
        queryName("counts").outputMode("update").start()
print(type(query))

import time
time.sleep(100)
query.stop()

"""OR"""
query.awaitTermination()



