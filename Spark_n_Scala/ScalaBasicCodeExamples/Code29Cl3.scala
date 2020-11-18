package main.scala
object Code29Cl3 {
  def main(args: Array[String]){
  val fang = new Tiger ("fang")
  fang.moveSpeed = 36.0
  println(fang.move)

 } //end of main
 abstract class Mammal (val name: String){
 var moveSpeed : Double //abstract field that doesnt have a value assigned to it  
  
 def move: String //abstract method which defines only paramters it gets, however it doesnt get any 
 }
  //create another class
  class Tiger(name: String) extends Mammal(name) {
  var moveSpeed = 35.0
  def move = "the Tiger %s runs %.2f mph".format(this.name,this.moveSpeed)  
}
 }