object CodeLst3 {

  def main(args: Array[String]): Unit = {
    val x = (1 to 1000).toList
    //lazy evaluation
    //lazy val x = (1 to 1000).toList
    val y = x.reduce(_ + _ )
    print(y)
  }

}
