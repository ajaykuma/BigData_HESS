package main.scala
/*Functions*/
//def funcName(param1:dataType,param2:dataType): returnType = {
//function body
//return valueToReturn
//}
object CodeFunc2 {
 def main(args: Array[String]){
  def getSum(i: Int,j: Int):Int = { i + j}
  // or use return--> def getSum(i: Int,j: Int):Int = { return i + j}
  println("5+5:" + getSum(5,5))
  //or passing values to arguments--> println("i+j:" + getSum(i=1005,j=5))
  }
}