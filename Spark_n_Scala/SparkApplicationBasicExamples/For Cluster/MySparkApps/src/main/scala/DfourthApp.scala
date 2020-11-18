package main.scala

import org.apache.spark.SparkContext 
import org.apache.spark.SparkContext._ 
import org.apache.spark.SparkConf
//import org.apache.spark.sql.SparkSession
//import org.apache.spark.sql
//import org.apache.spark.sql.types._
//import org.apache.spark.sql.Row
//import scala.collection.mutable.ArrayBuffer
//import scala.io.Source

object DfourthApp extends App{
  //val conf = new SparkConf().setAppName("DataSpark").setMaster("local")
  val conf = new SparkConf().setAppName("DataSpark")
  val sc = new SparkContext(conf)
  val fileInfoFile_with_Marked_Data = "DataFile.txt"
  //val fileInfoFile_with_Marked_Data = args(0)
  val x = sc.textFile(fileInfoFile_with_Marked_Data)
  //val sample1 = x
  val newDS = x.zipWithIndex().map{case(line,i)=>i.toString + "," + line}
  newDS.take(10).foreach(println)
sc.stop()
}