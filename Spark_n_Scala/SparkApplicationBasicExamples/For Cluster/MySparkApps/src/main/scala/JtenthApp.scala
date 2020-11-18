package main.scala

import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._ 
import org.apache.spark.SparkConf 
import org.apache.spark.api.java.function._
import org.apache.spark.streaming.api._
import org.apache.spark.storage.StorageLevel

object JtenthApp extends App{
//val conf = new SparkConf().setAppName("HeySparkStreamingW").setMaster("local[2]")
val conf = new SparkConf().setAppName("HeySparkStreamingW")
val sc = new SparkContext(conf)
val ssc = new StreamingContext(sc , Seconds(10))
//setting up receiver
val streamRDD = ssc.socketTextStream("127.0.0.1",2222,StorageLevel.MEMORY_ONLY)
//val test = streamRDD.saveAsObjectFiles("mydata")
val wordcounts = streamRDD.flatMap(line => line.split(" "))
                          .map(word => (word,1))
                          .reduceByKeyAndWindow((a:Int,b:Int)=>a+b,Seconds(30),Seconds(10))
//wordcounts.count()
wordcounts.print()
//wordcounts.saveAsTextFiles("output"+ java.util.UUID.randomUUID.toString)
ssc.start()
ssc.checkpoint(".")
ssc.awaitTermination()
}