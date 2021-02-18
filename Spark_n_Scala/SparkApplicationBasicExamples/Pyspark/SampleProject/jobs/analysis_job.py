"""
analysis_job.py
~~~~~~~~~~

    $SPARK_HOME/bin/spark-submit \
    --master spark://localhost:7077 \
    --py-files packages.zip \
    --files configs/config.json \
    jobs/analysis_job.py

"""

#import necessary libraries
import json
import pandas as pd
import numpy as np
from pyspark.sql.functions import udf
from pyspark.sql.functions import UserDefinedFunction
from pyspark.sql.types import StringType
from pyspark.sql.functions import countDistinct
from pyspark.sql.types import FloatType
from dependencies.spark import start_spark


def main():
    """Proj to find avg cooking time 
       for each category of difficulty (based on time consumed for preparation & cooking)
    :return: None
    """
    # start Spark application and get Spark session, logger and config
    spark, log, config = start_spark(
        app_name='my_analysis_job')

    # log that main analysis job is starting
    log.warn('analysis_job is up-and-running')

    # Analysis pipeline
    data = extract_data(spark)
    data_transformed = transform_data(data)
    load_data(data_transformed)

    # log the success and terminate Spark application
    log.warn('analysis_job is finished')
    spark.stop()
    return None


def extract_data(spark):
    """Load data in json format from local storage /s3
    :param spark: Spark session object.
    :return: Spark DataFrame.
    """
    file_location = "C:\\Users\\Win10\\Desktop\\SampleProject\\data\\recipes.json"
    df = spark.read.format("json").load(file_location)
    return df

def transform_data(df):
    """Transform original dataset.
    :param df: Input DataFrame.
    :return: Transformed DataFrame.
    """
    udf = UserDefinedFunction(lambda x: findingbeef(x), StringType())
    newdf = df.withColumn('ingredients',udf(df.ingredients))
    newdf = newdf.filter(newdf['ingredients'] != 'not beef')
    udf1 = UserDefinedFunction(lambda x: x[2:], StringType())
    newdf = newdf.withColumn('prepTime',udf1(newdf.prepTime))
    newdf = newdf.withColumn('cookTime',udf1(newdf.cookTime))
    udf3 = UserDefinedFunction(lambda x: timeff(x), StringType())
    newdf = newdf.withColumn('prepTime',udf3(newdf.prepTime))
    newdf = newdf.withColumn('cookTime',udf3(newdf.cookTime))
    udf4 = UserDefinedFunction(lambda x: difficulty(x), StringType())
    newdf = newdf.withColumn("cookTime",newdf["cookTime"].cast(FloatType()))
    newdf = newdf.withColumn("prepTime",newdf["prepTime"].cast(FloatType()))
    newdf = newdf.withColumn('TotalTime',(newdf.cookTime+newdf.prepTime))
    newdf = newdf.withColumn('difficulty',udf4(newdf.TotalTime))
    ResultDF = newdf.groupby(['difficulty']).agg({"cookTime": "avg"}).withColumnRenamed("avg(cookTime)","avg_total_cooking_time" )
    return ResultDF

def findingbeef(x:str):
    """check if recipes contain beef
    :param x: string i.e. recipes.
    :return: either string or 'not beef'.
    """
    if 'beef' in x:
        return x
    elif 'Beef' in x:
        return x
    else:
        return 'not beef'

def timeff(x):
    """to convert duration into timedelta and then to minutes for easy computation
    :param x: cookTime/prepTime recipes.
    :return: time in minutes.
    """
    n = pd.to_timedelta(x)
    return n/np.timedelta64(1, 'm')

def difficulty(x):
    """Assessing difficulty
    :param x: TotalTime
    :return: if easy,medium or hard
    """
    if x<30:
        return 'easy'
    elif x>=30 and x<=60:
        return 'medium'
    else:
        return 'hard'

def load_data(df):
    """Collect data locally and write to CSV.

    :param df: DataFrame to save
    :return: None
    """
    df \
        .coalesce(1) \
        .write.format("csv") \
        .option("header","true") \
        .mode("overwrite") \
        .save("C:\\Users\\Win10\\Desktop\\SampleProject\\data\\Finalreport")
    df.toPandas().to_csv("C:\\Users\\Win10\\Desktop\\SampleProject\\data\\report.csv")
    return None

# entry point for PySpark ETL application
if __name__ == '__main__':
    main()
