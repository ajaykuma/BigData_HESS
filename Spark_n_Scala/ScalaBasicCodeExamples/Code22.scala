package main.scala
import scala.collection.mutable.ArrayBuffer

object Code22 {
 def main(args: Array[String]){
   val favNumsx = new ArrayBuffer[Int]() 
   favNumsx += 11
   favNumsx += 2
   favNumsx ++= Array(23,34,45)
   favNumsx += (23,45,56)
   favNumsx.insert(8,100)
   favNumsx.mkString(",").foreach(print)
   println("\nremoving element\n")
   favNumsx.remove(0,1)
      //printing a specific index
	println(favNumsx(0))
      //printing array 
      favNumsx.foreach(println) 
   print("printing new array\n")
   val favNumsy = for(num <- favNumsx) yield 2 * num
   favNumsy.foreach(println)
   print("printing new array 2nd time\n")
   val favNumsz = for(num <- favNumsy if num % 2 == 0) yield num
   favNumsz.foreach(println)
   println("Sum : " + favNumsx.sum)
   println("min : " + favNumsx.min)
   println("max : " + favNumsx.max)
   val sortedNumsD = favNumsx.sortWith(_>_)
   val sortedNumsA = favNumsx.sortWith(_<_)
   println("sorted value:" + sortedNumsD.mkString(","))
}
}