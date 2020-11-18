package main.scala
import scala.collection.mutable.Map

object Code25 {
 def main(args: Array[String]){
   val customers = Map(100 -> "Bob",101 -> "lucie")
   //without importing package
   //val customers = collection.mutable.Map(100 -> "Bob",101 -> "lucie")
      printf("Cust1: %s\n", customers(100))
   customers(100) = "Tom"
   customers(102) = "mathew"
   for((k,v) <- customers)
    printf("%d : %s\n", k,v)

}
}