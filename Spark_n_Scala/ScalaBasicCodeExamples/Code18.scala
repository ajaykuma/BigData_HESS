package main.scala
object Code18 {
 def main(args: Array[String]){
   val favNums = new Array[Int](10) 
   favNums(0) = 1
   favNums(1) = 2
   favNums(3) = (23)
      //printing a specific index
	print(favNums(0))
      //printing array 
      favNums.foreach(println) 
}
}