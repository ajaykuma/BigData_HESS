package main.scala
//usage of traits
object Code30tr {
    def main(args: Array[String]){
  val superman = new Superhero("Superman")
  println(superman.fly)
  println(superman.hitByBullet)
  println(superman.ricochet(2500))
 } //end of main
    trait Flyable {
    def fly: String 
 }
    trait BulletProof{
    def hitByBullet : String 
    
   //def concrete classes
     def ricochet(startSpeed: Double): String = {
      "the bullet ricochets at a speed of %.1f ft/sec".format(startSpeed * .75) 
  } 
}
    //using traits to create another class
    class Superhero(val name: String) extends Flyable with BulletProof {
    def fly = "%s flys through the air".format(this.name)
    def hitByBullet = "The bullet bounces off of %s".format(this.name)    
}
}

