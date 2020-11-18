package main.scala
import org.apache.spark.streaming._
import org.apache.spark.SparkContext
//import org.apache.spark.SparkContext._ 
import org.apache.spark.SparkConf 

object IninethApp extends App{
val conf = new SparkConf().setAppName("HeySparkStreaming")
//.setMaster("local[2]")
val sc = new SparkContext(conf)
val ssc = new StreamingContext(sc , Seconds(10))
//setting up receiver
val streamRDD = ssc.socketTextStream("127.0.0.1",2222)
val wordcounts = streamRDD.flatMap(line => line.split(" ")).map(word => (word,1))
                  .reduceByKey(_+_)
//wordcounts.count()
wordcounts.print()
//wordcounts.saveAsTextFiles("output"+ java.util.UUID.randomUUID.toString)
ssc.start()
ssc.awaitTermination()
}