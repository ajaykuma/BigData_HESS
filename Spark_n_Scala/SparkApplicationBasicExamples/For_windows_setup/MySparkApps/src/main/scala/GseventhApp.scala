package main.scala

import org.apache.spark.SparkContext 
import org.apache.spark.SparkContext._ 
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object GseventhApp extends App{
val conf = new SparkConf().setAppName("HelloSpark").setMaster("local")
val sc = new SparkContext(conf)
println("printing metadata info from fileInfo.txt")
println("printing column headings i.e. fields for different markings")
val x = sc.textFile("C:\\Users\\alay.singhal\\Desktop\\Codes\\After_markings_SMPC522A1DC0040_SE-PGA454_20171012_082108_FileInfo.txt")
val sample1 = x
val newDS = sample1.zipWithIndex().map{case(line,i)=>i.toString + "," + line}
val pairz = newDS.map(x => (x.split(",")(0),x))
//pairz.take(100).foreach(println)
pairz.take(22).foreach(println)
val test1 = pairz.values.filter(line => line.startsWith("22,"))
//val y = test1.flatMap(line => (line.split(",")))
test1.flatMap(line => (line.split(","))).take(54).mkString(", ").foreach(print)
val test2 = pairz.values.filter(line => line.startsWith("241,"))
//val y = test1.flatMap(line => (line.split(",")))
test2.flatMap(line => (line.split(","))).take(54).mkString(", ").foreach(print)
val test3 = pairz.values.filter(line => line.startsWith("1166,"))
//val y = test1.flatMap(line => (line.split(",")))
test3.flatMap(line => (line.split(","))).take(54).mkString(", ").foreach(print)
val test4 = pairz.values.filter(line => line.startsWith("3887,"))
//val y = test1.flatMap(line => (line.split(",")))
test4.flatMap(line => (line.split(","))).take(54).mkString(", ").foreach(print)

sc.stop()

}