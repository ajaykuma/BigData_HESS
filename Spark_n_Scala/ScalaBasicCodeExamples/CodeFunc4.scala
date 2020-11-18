package main.scala
object CodeFunc4 {
 def main(args: Array[String]){
  def getSum(args: Int*):Int = { 
      var sum: Int = 0
      for(num <- args){
      sum += num
      } 
      sum 
      }
      println("get sum " + getSum(1,2,3,5,5))
}
}