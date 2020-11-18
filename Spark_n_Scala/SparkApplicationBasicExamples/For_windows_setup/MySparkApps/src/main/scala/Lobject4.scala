

package main.scala

object Code27 {
  def main(args: Array[String]){
  //classes are used as blue prints to created real world objects ,objects define attributes  which are called fields in OO world
  //& capabilities of those objects which are called methods
  //usually created outside main
  
  val wolf = new Animal
  wolf.setName("wolf")
  wolf.setSound("woo")
  printf("%s says %s\n", wolf.getName, wolf.getSound)
  } //end of main
  
}