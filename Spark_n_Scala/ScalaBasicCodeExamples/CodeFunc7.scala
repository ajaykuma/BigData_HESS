object CodeFunc7 {
  def main(args: Array[String]): Unit = {
    def areaRect(l: Float, b: Float): Float = {
      return l * b
    }
    def isEvenNumber(n:Int): Boolean = { n % 2 == 0 }
    //print(areaRect(10.23f,3f))
    print("area is " + areaRect(10.23f, 3f))
    print("\n" + isEvenNumber(23))
  }
  }
