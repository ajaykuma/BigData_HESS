package main.scala
object Code28Cl2 {
  def main(args: Array[String]){

  
  val wolf = new Animal
  wolf.setName("wolf")
  wolf.setSound("woo")
  printf("%s says %s\n", wolf.getName, wolf.getSound)

  val whiskers = new Animal("Whiskers","Meow")
  println(s"${whiskers.getName} with id ${whiskers.id} says ${whiskers.sound}")

  val spike = new Dog("Spike","woof","Grrr")
  spike.setName("Spike")
  println(spike.toString)
  } //end of main
  
  //define class and default constructors
  class Animal(var name: String, var sound: String){
  this.setName(name)
  
  //usually in scala we dont have static variables and methods,but we can create as below
  val id = Animal.newIdNum

  //define getters and setters to protect data
  def getName() : String = name
  def getSound() : String = sound
  
  def setName(name: String){
  //if name doesnt contain any numbers of decimals
  if(!(name.matches(".*\\d+.*")))
	this.name = name
  else
        this.name = "No name"
  }
  
  def setSound(sound: String){
  this.sound = sound
  }

  //creating other constructors,using this (which is a constructor in scala)
  def this(name: String){
  this("No name","No sound")
  this.setName(name)
  }

  def this(){
  this("No name","No sound")
  }

  //to override a method
  //a method available with every object we create, in scala to override is toString
  override def toString() : String = {
  return "%s with the id %d says %s".format(this.name,this.id,this.sound)
  }
  }

  //create a companion object for class to find static variables and functions
  object Animal {
  private var idNumber = 0
  //creating static function
  private def newIdNum = { idNumber += 1;idNumber}
  }
  
//to test inheritance
  class Dog(name: String,sound: String, growl: String) extends Animal(name,sound){
 def this(name: String, sound: String){
 this("No name",sound,"No growl")
 this.setName(name)
 }
 def this(name: String){
 this("No name","No Sound","No Growl")
 this.setName(name) 
 }
 def this(){
 this("No name","No Sound","No Growl")
 }
 override def toString() : String = {
  return "%s with the id %d says %s or %s".format(this.name,this.id,this.sound,this.growl)
  }  
 }
 }