package main.scala
import scala.io.StdIn.{readLine, readInt}
object Code9 {
  def main(args: Array[String]){
  var numberGuess = 0
   /*readInt readDouble readByte
    readShort readLong */  
do{
   print("Guess a number ")
   numberGuess = readLine.toInt
   } while(numberGuess != 5)
   printf("you guessed right %d\n",5)
}
}