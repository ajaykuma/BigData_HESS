package main.scala

object MyApp1 extends App{
 
    println("hello")
    val x = "welcome guys"
    val y = x.map(_.toUpper)
    y.foreach(println)
}