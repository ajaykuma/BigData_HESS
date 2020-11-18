package main.scala
object Code26 {
 def main(args: Array[String]){
   var tupleM = (103,"tom",1.3)
   printf("%s owes us $%.1f\n", tupleM._2,tupleM._3)
   tupleM.productIterator.foreach( i => println(i))
   println(tupleM.toString)
}
}