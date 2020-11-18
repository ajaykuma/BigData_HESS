package main.scala
object Code24 {
 def main(args: Array[String]){
   val employees = Map("Manager" -> "Bob","Secretary" -> "lucie")
   if(employees.contains("Manager"))
   printf("Manager: %s\n", employees("Manager"))

}
}