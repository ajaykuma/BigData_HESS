package main.scala
/*Scala Code For loop to iterate */
object Code7 {
  def main(args: Array[String]) : Unit ={
    var i = 0
    for (i <- 1 to 5; j <- 6 to 10){
    println("i : " + i)
    println("j : " + j)}
  }
}