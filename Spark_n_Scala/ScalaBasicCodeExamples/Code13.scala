package main.scala
/*using Strings & creating array*/
object Code13 {
 def main(args: Array[String]){
  var randSent = "hi how are you"
  val randArray = randSent.toArray
  val randArray1 = randSent.split(" ")
  print("first array\n")
  for (v <- randArray)
  println(v)
  print("second array\n")
  for (v <- randArray1)
  println(v)

}
}