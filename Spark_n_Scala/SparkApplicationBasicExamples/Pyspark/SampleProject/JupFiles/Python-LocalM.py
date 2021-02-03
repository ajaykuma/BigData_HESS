#!/usr/bin/env python
# coding: utf-8

# In[1]:


#Import necessary libraries
import json
import pandas as pd
import numpy as np


# In[2]:


#Reading data from s3
df = pd.read_json("https://s3-eu-west-1.amazonaws.com/dwh-test-resources/recipes.json",lines=True)

#creating a copy of df
Workdf = df.copy()

#Looking at columns
Workdf.columns


# In[3]:


#Function to extract recipes that contain beef
def findingbeef(x:str):
    if 'beef' in x:
        return x
    elif 'Beef' in x:
        return x
    else:
        return 'not beef'


# In[4]:


#extracting rows with recipes without beef
Workdf['ingredients'] = Workdf['ingredients'].apply(lambda n:findingbeef(n))
Workdf = Workdf[Workdf.ingredients != 'not beef'] 
Workdf.count()


# In[5]:


#looking at unique values
Workdf['cookTime'].unique()
Workdf['prepTime'].unique()


# In[6]:


#replacing 'PT' and '' values with PT0
#if there are missing values or no time duration (as it exists in the dataset)
#Workdf['cookTime'] = Workdf['cookTime'].replace(['PT',''],'PT0')
#Workdf['prepTime'] = Workdf['prepTime'].replace(['PT',''],'PT0')

#we dont need for our use case
Workdf['prepTime'] = Workdf['prepTime'].apply(lambda n:n[2:])
Workdf['cookTime'] = Workdf['cookTime'].apply(lambda n:n[2:])


# In[7]:


#using timedelta to convert 
def timeff(x):
    n = pd.to_timedelta(x)
    return n


# In[8]:


#before applying fucntion,confirming datatypes
Workdf.dtypes


# In[9]:


#applying function
Workdf['cookTime'] = Workdf['cookTime'].apply(lambda n:timeff(n))
Workdf['prepTime'] = Workdf['prepTime'].apply(lambda n:timeff(n))


# In[10]:


#converting values in cookTime n PrepTime into minutes
Workdf['cookTime'] = Workdf['cookTime']/np.timedelta64(1, 'm')
Workdf['prepTime'] = Workdf['prepTime']/np.timedelta64(1, 'm')


# In[11]:


#function to assess difficulty level
def difflvl(x):
    if x<30:
        return 'easy'
    elif x>=30 and x<=60:
        return 'medium'
    else:
        return 'hard'


# In[12]:


#computing total cook time and applying function
Workdf['cookTimeTot'] = Workdf['cookTime']+Workdf['prepTime']
Workdf['difficulty'] = Workdf['cookTimeTot'].apply(lambda n:difflvl(n))


# In[13]:


#Groupby and fining avg cookTime for each level of difficulty
Result = Workdf.groupby(['difficulty']).agg({'cookTime':['mean']})
Result.columns = Result.columns.get_level_values(1)
Result = Result.reset_index()


# In[14]:


#Result['difficulty'] = Result['difflvl']
Result['avg_total_cooking_time'] = Result['mean']
#Result.drop(['mean','difflvl'],axis=1,inplace=True)
Result.drop(['mean'],axis=1,inplace=True)
#Result.to_csv('report.csv',index=False)


# In[15]:


Result


# In[16]:




