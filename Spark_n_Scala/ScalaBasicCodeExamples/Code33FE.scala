package main.scala
import java.io.PrintWriter
import scala.io.Source


object Code33FE {
   def main(args: Array[String]){

   def divideNums(num1: Int, num2: Int) = try 
   {
    (num1/num2)
   } catch {
     case ex : java.lang.ArithmeticException => "can't dvide by zero"
  }  finally {
     //cleanup after exception    
}

    println("3 / 0 = " + divideNums(0,3))
}// End of Main
} //End of Object[Code33]