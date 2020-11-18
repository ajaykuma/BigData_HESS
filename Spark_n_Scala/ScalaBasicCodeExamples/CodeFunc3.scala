package main.scala
/*Scala Code for creating functions */
object CodeFunc3 {
  def main(args: Array[String]) : Unit ={
    var i = 0
    def printPrimes() : Unit = {
    val primeList = List(1,2,3,5,7,11)
    for(i <- primeList){
    if(i == 11) {
	return }
    if(i != 1){
       println(i)
    }
}}
 	printPrimes()
  }
}