package main.scala
import scala.collection.mutable.ArrayBuffer

object Code23 {
 def main(args: Array[String]){
   var multTable = Array.ofDim[Int](10,10)
   for(i <- 0 to 9){
   for(j <- 0 to 9) { 
   multTable(i)(j) = i * j 
  }
  }

   for(i <- 0 to 9){
   for(j <- 0 to 9) { 
   printf("%d : %d = %d\n" , i , j, multTable(i)(j))
   }
   }

}
}