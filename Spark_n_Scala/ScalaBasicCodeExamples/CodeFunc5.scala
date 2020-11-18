package main.scala
//function calling itself

object CodeFunc5 {
 def main(args: Array[String]){
  def factorial(num: BigInt): BigInt = { 
      if(num <= 1)
      1
      else 
      num * factorial(num -1)
      } 
      println("factorial of 4 is :" + factorial(4))
}
}