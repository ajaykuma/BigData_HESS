package main.scala

import org.apache.spark.SparkContext 
import org.apache.spark.SparkContext._ 
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object HeighthApp extends App {
val conf = new SparkConf().setAppName("HelloSpark").setMaster("local")
//.set("spark.debug.maxToStringFields","100")

val sc = new SparkContext(conf)
val fileInfoFile_with_Marked_Data = args(0)
val x = sc.textFile(fileInfoFile_with_Marked_Data)
val sample1 = x

val newDS = sample1.zipWithIndex().map{case(line,i)=>i.toString + "," + line}

val n = newDS.filter(line => line.contains("countryCode")).take(1).flatMap(line => line.split(","))
val f = sc.parallelize(n).first().toInt
//println("Metadata in this fileInfo file is:")
//newDS.take(f).slice(2,f).foreach(println)
//sample1.take(f).slice(2,f).foreach(println)

val pairD = sample1.take(f).slice(3,f).map(line => (line.split(","))).map(fields => (fields(0),fields(1).toInt,fields(2).toInt))
//pairD.foreach(println)

//println("to convert to Json this file contains data for: ===> ")
//print("[")
//val test = pairD.map(x => x._1).mkString(" , ").foreach(print)
//print("]\n")

//To replace SS4Json with AutovariableName
//To replace x in take(x) where x = sum of ("row number that has fieldNames" + "no of rows including row number that has fieldNames")
//To replace slice(a,b), where a = ( row number -1 ) & b = (x -1)
//To append g with x for every row in metadata till we reach f
//To append DataInC+x , where x corresponds to x with g
//To append df+x, where x corresponds to x with g
//To replace variable appended with output directory by the name of field.
//or put this whole processing for all rows (2,f) in a loop.

//val c = pairD.map(x => x._1).mkString(" , ").split(",")
//
//environmentMetaData,13,2,emd
//fileMetaData,15,2,fmd
//systemMetaData,17,2,smd
//vehicleInformationMetaData,19,2,vimd
//visionMetaData,21,2,vmd
//Pedestrian,23,219,Pedestrian
//TwoWheeler,242,925,TwoWheeler
//Unclassified3DMarking,1167,2721,Unclassified3DMarking
//Vehicle,3888,2474,Vehicle
//sample1.take(15).slice(12,14).foreach(println)
//sample1.take(17).slice(14,16).foreach(println)
//sample1.take(19).slice(16,18).foreach(println)
//sample1.take(21).slice(18,20).foreach(println)
//sample1.take(23).slice(20,22).foreach(println)
//sample1.take(242).slice(22,241).foreach(println)
//sample1.take(1167).slice(241,1166).foreach(println)
//sample1.take(3888).slice(1166,3887).foreach(println)
//sample1.take(6362).slice(3887,6361).foreach(println)

val spark = SparkSession.builder.master("local").appName("Spark_SQL_basic_example").getOrCreate()
//sample1.take(15).slice(12,14).foreach(println)
val SS4Json0 = sample1.take(15).slice(12,14)
val g0 = sc.parallelize(SS4Json0)
g0.saveAsTextFile("C:\\Users\\ajay\\Desktop\\Codes\\DataInC0")
val df = spark.read.format("csv").option("inferSchema","true").option("header","true").load("C:\\Users\\ajay\\Desktop\\Codes\\DataInC0" + "\\" + "part-00000")
//println("fileinfo data in dataframe")
//df.show(10)
df.write.format("json").save("C:\\Users\\ajay\\Desktop\\Codes\\DataInJson" + "-" + "envM")

//sample1.take(17).slice(14,16).foreach(println)
val SS4Json1 = sample1.take(17).slice(14,16)
val g1 = sc.parallelize(SS4Json1)
g1.saveAsTextFile("C:\\Users\\ajay\\Desktop\\Codes\\DataInC1")
val df1 = spark.read.format("csv").option("inferSchema","true").option("header","true").load("C:\\Users\\ajay\\Desktop\\Codes\\DataInC1" + "\\" + "part-00000")
//println("fileinfo data in dataframe")
//df1.show(10)
df1.write.format("json").save("C:\\Users\\ajay\\Desktop\\Codes\\DataInJson1" + "-" + "fileM")

//sample1.take(19).slice(16,18).foreach(println)
val SS4Json2 = sample1.take(19).slice(16,18)
val g2 = sc.parallelize(SS4Json2)
g2.saveAsTextFile("C:\\Users\\ajay\\Desktop\\Codes\\DataInC2")
val df2 = spark.read.format("csv").option("inferSchema","true").option("header","true").load("C:\\Users\\ajay\\Desktop\\Codes\\DataInC2" + "\\" + "part-00000")
//println("fileinfo data in dataframe")
//df2.show(10)
df2.write.format("json").save("C:\\Users\\ajay\\Desktop\\Codes\\DataInJson2" + "-" + "sysM")

//sample1.take(21).slice(18,20).foreach(println)
val SS4Json3 = sample1.take(21).slice(18,20)
val g3 = sc.parallelize(SS4Json3)
g3.saveAsTextFile("C:\\Users\\ajay\\Desktop\\Codes\\DataInC3")
val df3 = spark.read.format("csv").option("inferSchema","true").option("header","true").load("C:\\Users\\ajay\\Desktop\\Codes\\DataInC3" + "\\" + "part-00000")
//println("fileinfo data in dataframe")
//df3.show(10)
df3.write.format("json").save("C:\\Users\\ajay\\Desktop\\Codes\\DataInJson3" + "-" + "VehIn")

//sample1.take(23).slice(20,22).foreach(println)
val SS4Json4 = sample1.take(23).slice(20,22)
val g4 = sc.parallelize(SS4Json4)
g4.saveAsTextFile("C:\\Users\\ajay\\Desktop\\Codes\\DataInC4")
val df4 = spark.read.format("csv").option("inferSchema","true").option("header","true").load("C:\\Users\\ajay\\Desktop\\Codes\\DataInC4" + "\\" + "part-00000")
//println("fileinfo data in dataframe")
//df4.show(10)
df4.write.format("json").save("C:\\Users\\ajay\\Desktop\\Codes\\DataInJson4" + "-" + "visM")

//sample1.take(242).slice(22,241).foreach(println)
val SS4Json5 = sample1.take(242).slice(22,241)
val g5 = sc.parallelize(SS4Json5)
g5.saveAsTextFile("C:\\Users\\ajay\\Desktop\\Codes\\DataInC5")
val df5 = spark.read.format("csv").option("inferSchema","true").option("header","true").load("C:\\Users\\ajay\\Desktop\\Codes\\DataInC5" + "\\" + "part-00000")
//println("fileinfo data in dataframe")
//df5.show(10)
df5.write.format("json").save("C:\\Users\\ajay\\Desktop\\Codes\\DataInJson5" + "-" + "pede")

//sample1.take(1167).slice(241,1166).foreach(println)
val SS4Json6 = sample1.take(1167).slice(241,1166)
val g6 = sc.parallelize(SS4Json6)
g6.saveAsTextFile("C:\\Users\\ajay\\Desktop\\Codes\\DataInC6")
val df6 = spark.read.format("csv").option("inferSchema","true").option("header","true").load("C:\\Users\\ajay\\Desktop\\Codes\\DataInC6" + "\\" + "part-00000")
//println("fileinfo data in dataframe")
//df6.show(10)
df6.write.format("json").save("C:\\Users\\ajay\\Desktop\\Codes\\DataInJson6" + "-" + "twoW")

//sample1.take(3888).slice(1166,3887).foreach(println)
val SS4Json7 = sample1.take(3888).slice(1166,3887)
val g7 = sc.parallelize(SS4Json7)
g7.saveAsTextFile("C:\\Users\\ajay\\Desktop\\Codes\\DataInC7")
val df7 = spark.read.format("csv").option("inferSchema","true").option("header","true").load("C:\\Users\\ajay\\Desktop\\Codes\\DataInC7" + "\\" + "part-00000")
//println("fileinfo data in dataframe")
//df7.show(10)
df7.write.format("json").save("C:\\Users\\ajay\\Desktop\\Codes\\DataInJson7" + "-" + "unclassi")

//sample1.take(6362).slice(3887,6361).foreach(println)
val SS4Json8 = sample1.take(6362).slice(3887,6361)
val g8 = sc.parallelize(SS4Json8)
g8.saveAsTextFile("C:\\Users\\ajay\\Desktop\\Codes\\DataInC8")
val df8 = spark.read.format("csv").option("inferSchema","true").option("header","true").load("C:\\Users\\ajay\\Desktop\\Codes\\DataInC8" + "\\" + "part-00000")
//println("fileinfo data in dataframe")
//df8.show(10)
df8.write.format("json").save("C:\\Users\\ajay\\Desktop\\Codes\\DataInJson8" + "-" + "Veh")

println("Metadata in this fileInfo file is:")
//newDS.take(f).slice(2,f).foreach(println)
//sample1.take(f).slice(2,f).foreach(println)

pairD.foreach(println)

println("to convert to Json this file contains data for: ===> ")
print("[")
val test = pairD.map(x => x._1).mkString(" , ").foreach(print)
print("]\n")

sc.stop()
}