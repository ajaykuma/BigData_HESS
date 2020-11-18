package main.scala

//import org.apache.spark.SparkContext 
//import org.apache.spark.SparkContext._ 
//import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object CthirdApp extends App{

//val conf = new SparkConf().setAppName("HelloSpark").setMaster("local")
//val sc = new SparkContext(conf)
//val spark = SparkSession.builder.master("local").appName("Spark_SQL_basic_example").getOrCreate()
val spark = SparkSession.builder.appName("Spark_SQL_basic_example").getOrCreate()
val x = spark.read.json("employees.json")
x.show()
}