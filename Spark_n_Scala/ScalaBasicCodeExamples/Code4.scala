package main.scala
/*Scala Code For loop*/
object Code4 {
  def main(args: Array[String]) : Unit ={
    var i = 0
    val randLetters = "ABCDFGTGHYSDEFHJKILOPMNGSH"
    for ( i <- 0 until randLetters.length)
     println(randLetters(i))
  }
}
