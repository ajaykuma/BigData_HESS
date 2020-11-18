package main.scala
import scala.io.Source
/*using Strings*/

object Code12 {
  def main(args: Array[String]){
  var randSent = "hi how are you"
  //finding index
  println("indexed element: " + randSent(2))
  //finding string length
  println("String length: " + randSent.length)
  //concatinating
  println(randSent.concat(" and am here"))
  //comparing
  println("are strings equal:" + "hi who are you".equals(randSent))
  //searching
  println("how starts at :" + randSent.indexOf("how"))

}
}