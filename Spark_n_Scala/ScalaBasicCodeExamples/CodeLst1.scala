package main.scala
/*Scala Code For loop to iterate through a list*/
object CodeLst1 {
  def main(args: Array[String]) : Unit ={
    var i = 0
    val aList = List(1,2,3,4,5)
    for ( i <- aList){
   println("List items " + i)}
  }
}