#!/usr/bin/env python
# coding: utf-8

# In[1]:


#import necessary libraries
import json
import pandas as pd
import numpy as np


# In[2]:


#Reading from location on cluster, change the file_location accordingly
#df = pd.read_json("https://s3-eu-west-1.amazonaws.com/dwh-test-resources/recipes.json",lines=True)
#File location and type
file_location = "/FileStore/tables/recipes.json"
file_type = "json"

# CSV options
infer_schema = "false"
first_row_is_header = "false"
delimiter = ","

# The applied options are for CSV files. For other file types, these will be ignored.
df = spark.read.format(file_type)   .option("inferSchema", infer_schema)   .option("header", first_row_is_header)   .option("sep", delimiter)   .load(file_location)

display(df)
df.dtypes


# In[3]:


#Function to extract recipes that contain beef.
#Optionally can done fully using lambda
def findingbeef(x:str):
    if 'beef' in x:
        return x
    elif 'Beef' in x:
        return x
    else:
        return 'not beef'


# In[4]:


#working will continue on newdf created (note** using spark thus lazy evaluation implicitly happening unless invoking action)
#Option 1
#df.createOrReplaceTempView("newrecipes")
#newdf = spark.sql("select * from newrecipes where lcase(ingredients) like '%beef%'")
#newdf.count()

#Option 2 using udf
from pyspark.sql.functions import UserDefinedFunction
from pyspark.sql.types import StringType
udf = UserDefinedFunction(lambda x: findingbeef(x), StringType())
newdf = df.withColumn('selrecipe',udf(df.ingredients))
newdf = newdf.filter(newdf['selrecipe'] != 'not beef')
newdf.count()
#newdf.columns
#newdf.select("selreci").show(1,truncate=False)


# In[5]:


#newdf.groupBy("cookTime").count().show()
#newdf.groupBy("prepTime").count().show()
#or
from pyspark.sql.functions import countDistinct
newdf.select(countDistinct("cookTime")).show()
newdf.select(countDistinct("prepTime")).show()

#To look at unique values for these fields
#newdf.select("cookTime").distinct().collect()
#newdf.select("prepTime").distinct().collect()


# In[6]:


#Using udf to strip out unnecessary characters(for easier calculation later)
#optionally instead of newcolumn names, same column names can be used..(better approach when data is huge)

udf = UserDefinedFunction(lambda x: x[2:], StringType())
newdf = newdf.withColumn('newprepTime',udf(newdf.prepTime))
newdf = newdf.withColumn('newcookTime',udf(newdf.cookTime))
newdf.select("newcookTime").show(2)
newdf.select("newprepTime").show(2)


# In[7]:


#for conversion of fields into computable time duration in minutes
#using timedelta
def timeff(x):
    n = pd.to_timedelta(x)
    return n/np.timedelta64(1, 'm')


# In[8]:


#before applying function,confirming datatypes
#newdf.dtypes

udfx = UserDefinedFunction(lambda x: timeff(x), StringType())
newdf = newdf.withColumn('newprepTime1',udfx(newdf.newprepTime))
newdf = newdf.withColumn('newcookTime1',udfx(newdf.newcookTime))


# In[9]:


newdf.select("prepTime","newPrepTime","newPrepTime1").show(2)


# In[10]:


newdf.select("prepTime","newPrepTime","newPrepTime1").dtypes


# In[11]:


#assessing difficulty level based on time spent
def difficulty(x):
    if x<30:
        return 'easy'
    elif x>=30 and x<=60:
        return 'medium'
    else:
        return 'hard'


# In[12]:


#applying function
udfn = UserDefinedFunction(lambda x: difficulty(x), StringType())
from pyspark.sql.types import FloatType
newdf = newdf.withColumn("newcookTime1",newdf["newcookTime1"].cast(FloatType()))
newdf = newdf.withColumn("newprepTime1",newdf["newprepTime1"].cast(FloatType()))
newdf = newdf.withColumn('TotalTime',(newdf.newcookTime1+newdf.newprepTime1))
newdf = newdf.withColumn('difficulty',udfn(newdf.TotalTime))
newdf.select("newprepTime1","newcookTime1","difficulty").show(5)
                                                            


# In[13]:


#looking at results and saving it as csv in one file..
Result = newdf.groupby(['difficulty']).agg({"newcookTime1": "avg"}).withColumnRenamed("avg(newcookTime1)","avg_total_cooking_time" )
Result.show()
Result.coalesce(1).write.format("csv").save("Finalreport.csv")

