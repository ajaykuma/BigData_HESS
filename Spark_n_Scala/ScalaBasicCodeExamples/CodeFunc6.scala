package main.scala
import scala.math._
object CodeFunc6 {
   def main(args: Array[String]){
	
     val log10Func = log10 _  //use underscore to show its a function,and fn can be used to store
     println(log10Func(1000))

     //Apply a function to all items of a list
     List(100.0,1000.0).map(log10Func).foreach(println)
     List(1,2,3,4,5).map((x: Int) => x * 50 ).foreach(println)
     List(1,2,3,4,5).filter(_ % 2 == 0 ).foreach(println)

//passing functions to different functions
     def times2(num: Int) = num * 2
     def times20(num: Int) = num * 20

     def multIt(func: (Int) => Double, num : Int) = {
     func(num)
     }
     printf("3 * 100 = %.1f\n", multIt(times2, 100)) 
 //Closure: A function that depends on variables defined outside the function
     val divisorVal = 5
     val divisor5 = (num: Double) => num/divisorVal
     println("5/5= " + divisor5(5.0))
}// End of Main
} //End of Object[Code31]