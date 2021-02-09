""" without streaming """
from pyspark.sql.types import IntegerType,StringType, StructType, StructField

from pyspark.sql import SparkSession
spark = SparkSession.builder.master("local").appName("sparkdfex").getOrCreate()
inputpath = "Data"
"""schema = StructType([StructField("name",StringType(),True),
                     StructField("age",IntegerType())])

inputDF = (spark.read.schema(schema).json(inputpath))"""
inputDF = spark.read.json(inputpath)

inputDF.show()
inputDF.printSchema()
print(inputDF.count())