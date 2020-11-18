package main.scala
import java.io.PrintWriter
import scala.io.Source

object Code32 {
   def main(args: Array[String]){

   //writing to a file
	val writer = new PrintWriter("sample.txt")
   writer.write("some non imp data\nbut not to be ignored")
   writer.close()

   //reading from a file
   val textFromFile = Source.fromFile("sample.txt", "UTF-8")
    //iterating over lines of text
    val lineIterator = textFromFile.getLines
    for(line <- lineIterator)
    println(line)

   textFromFile.close()
}// End of Main
} //End of Object[Code32]