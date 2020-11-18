

package main.scala

object Lobject4 extends App{
  
  //classes are used as blue prints to created real world objects ,objects define attributes  which are called fields in OO world
  //& capabilities of those objects which are called methods
  //usually created outside main
  
  val wolf = new KAnimal
  wolf.setName("wolf")
  wolf.setSound("woo")
  printf("%s says %s\n", wolf.getName, wolf.getSound)
  } //end of main
  
