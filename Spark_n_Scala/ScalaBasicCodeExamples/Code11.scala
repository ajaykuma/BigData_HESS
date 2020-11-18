/*test your code*/
package main.scala
import scala.io.StdIn.{readLine,readInt}
object Code11{
 def main(args: Array[String]){
  println("enter a name:")
  val name = readLine
  println("enter age")
  val age = readInt
  println(s"his name is $name")
  println(s"his age is $age")
  printf("%7s", name)
  }
}