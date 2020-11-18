package main.scala
import org.apache.spark.SparkContext 
import org.apache.spark.SparkContext._ 
import org.apache.spark.SparkConf 

object WholeScala extends App{
  
    print("hello")
    val conf = new SparkConf().setAppName("HelloSpark0610").setMaster("local")
    //val conf = new SparkConf().setAppName("HelloSpark0610")
    val sc = new SparkContext(conf)
    val x = sc.wholeTextFiles("I:\\Trainings\\MyContent\\Books\\resources")
    x.keys.take(20).foreach(println)
    val y = x.values
    y.saveAsTextFile("outp" + java.util.UUID.randomUUID.toString)
    //YOUR CODE
    sc.stop()
  }
