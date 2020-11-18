package main.scala
import org.apache.spark.SparkContext 
import org.apache.spark.SparkContext._ 
import org.apache.spark.SparkConf 

object AfirstApp {
  def main(args: Array[String]) {
    print("hello")
    //val conf = new SparkConf().setAppName("HelloSpark0610").setMaster("local")
    val conf = new SparkConf().setAppName("HelloSpark0610")
    val sc = new SparkContext(conf)
    val x = sc.textFile("abc1.txt")
    val y = x.flatMap(n => n.split(" "))
    val z = y.map(n => (n,1))
    val result = z.reduceByKey(_+_)
    result.count()
    result.first()
    result.collect()
    result.saveAsTextFile("outp" + java.util.UUID.randomUUID.toString)
    //YOUR CODE
    sc.stop()
  }}
