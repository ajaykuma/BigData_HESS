package main.scala
/*Scala Code For loop to iterate through a list and use if*/
object CodeLst2 {
  def main(args: Array[String]) : Unit ={
    var i = 0
    val evenList = for{i <- 1 to 20 if (i % 2) == 0 }
    yield i
   for (i <- evenList) println(i)
  }
}
