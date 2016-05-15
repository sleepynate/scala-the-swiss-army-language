package examples

object Part2Examples {
  def typeInferenceIsHandy(): Unit = {
    var b = 5.0
    // b: Double = 5.0
    var d = '5'
    // d: Char = 5
    var e = true
    // e: Boolean = true
    var f = null
    // f: Null = null
    var g = List('x, 'y, 'z)
    // g: List[Symbol] = List('x, 'y, 'z)
    var h = List('x, 'y', 'z)
    // h: List[Any] = List('x, y, 'z)
  }
  def typeAnnotationsAndDeclarations(): Unit = {
    val addAnotherOne = (x:Int) => x + 1
    // addAnotherOne: Int => Int = <function1>
    def revMe(s: String) = s.reverse
    // revMe: (s: String)String
    def thereAndBack(t: String, b: String) = t + b.reverse
    // thereAndBack: (t: String, b: String)String
    def foo(f: Int => String => Boolean => Double) = f.apply(5)
    // foo: (f: Int => (String => (Boolean => Double)))String => (Boolean => Double)
  }
  def typeParameters(): Unit = {
    val a = List(1, 2, 3)
    // a: List[Int] = List(1, 2, 3)
    (1 to 5).toList
    // res5: List[Int] = List(1, 2, 3, 4, 5)
    Set('a', 'b', 'c').toList
    // res6: List[Char] = List(a, b, c)
    def gimmeThreeOf[A](x: A):List[A] = List(x, x, x)
    // gimmeThreeOf: [A](x: A)List[A]
    gimmeThreeOf(5)
    // res7: List[Int] = List(5, 5, 5)
    gimmeThreeOf('r')
    // res8: List[Char] = List(r, r, r)
  }
  def patternMatching(): Unit = {
    def magicNumber(n: Int) = n match {
      case 3 => "IT'S THE MAGIC NUMBER!"
      case _ => "just an ordinary number"
    }
    magicNumber(4)
    // res0: String = just an ordinary number
    magicNumber(5)
    // res1: String = just an ordinary number
    magicNumber(3)
    // res2: String = IT'S THE MAGIC NUMBER!
  }
  def failedMatching(): Unit = {
    def failMatch(a: Any) = a match {
      case _:Int => "int!"
    }
    failMatch(1)
    // res3: String = int!
    failMatch(5.5)
    // scala.MatchError: 5.5 (of class java.lang.Double)
  }
  def patternMatchingWithBinding(): Unit = {
    def magicNumber(n: Int) = n match {
      case 3 => "IT'S THE MAGIC NUMBER!"
      case x => x + " is just an ordinary number"
    }
    magicNumber(3)
    // res5: String = IT'S THE MAGIC NUMBER!
    magicNumber(436782)
    // res6: String = 436782 is just an ordinary number
  }
  def recursivePatternMatching(): Unit = {
    def triangular(n: Int):Int = n match {
      case 1 => 1
      case x => x + triangular(x - 1)
    }
    triangular(10)
    // res0: Int = 55
    triangular(32843)
    // res1: Int = 539347746
  }
  def decomposition(): Unit = {
    ("first", "second") match {
      case (f, s) => f + " is first and next comes " + s
    }

    def vectorAdd1(first: (Int, Int), second: (Int, Int)) =
      (first._1 + second._1, first._2 + second._2)

    vectorAdd1((3,0), (1,4))
    // res0: (Int, Int) = (4,4)

    def vectorAdd2(first: (Int, Int), second: (Int, Int)) =
      (first, second) match {
        case ((x1, y1), (x2, y2)) => (x1+x2, y1+y2)
      }

    vectorAdd2((3,0), (1,4))
    // res1: (Int, Int) = (4,4)

    def head[A](l:List[A]) = l match {
      case Nil => throw new Exception("Oops!")
      case h :: _ => h
    }
    head(List(1,2,3))
    // res7: Int = 1
    val tuples = List((1, 2), (3, 4), (5, 6))
    // res3: List[(Int, Int)] = List((1,2), (3,4), (5,6))
    for ((x,y) <- tuples) yield x+y
    //res4: List[Int] = List(3, 7, 11)

    (1, "hurray!") match {
      case w@(i:Int, s:String) => w + " is made of a " +
        i + " and a " + s
    }
  }
  def guards1(): Unit = {
    def mySteps(steps:Int) =
      if (steps < 2500) "you aren't even trying"
      else if (steps < 5000) "that is pitiful"
      else if (steps < 7500) "ok but still walk more"
      else "hey you walked. good for you."
  }
  def guards2(): Unit = {
    def mySteps(steps:Int) = steps match {
      case s if (s < 2500) => "you aren't even trying"
      case s if (s < 5000) => "that is pitiful"
      case s if (s < 7500) => "ok but still walk more"
      case _ => "hey you walked. good for you."
    }
  }
  def guards3(): Unit = {
    def mySteps(t:(String, Int)) =
      if(t._1 == "fatbot" && t._2 < 2500) "you aren't even trying"
      else if(t._1 == "fatbot" && t._2 < 5000) "that is pitiful"
      else if(t._1 == "fatbot" && t._2 < 7500) "ok but still walk more"
      else if(t._1 == "fatbot") "hey you walked. good for you."
      else "What the heck is a " + t._1 + "?"
  }
  def guards4(): Unit = {
    def mySteps(t:(String, Int)) = {
      val device: String = t._1
      if (device == "fatbot") {
        val steps: Int = t._2
        if (steps < 2500) "you aren't even trying"
        else if (steps < 5000) "that is pitiful"
        else if (steps < 7500) "ok but still walk more"
        else "hey you walked. good for you."
      }
      else "What the heck is a " + device + "?"
    }
  }
  def guards5(): Unit = {
    def mySteps(t:(String, Int)) = t match {
      case ("fatbot", s) if (s < 2500) => "you aren't even trying"
      case ("fatbot", s) if (s < 5000) => "that is pitiful"
      case ("fatbot", s) if (s < 7500) => "ok but still walk more"
      case ("fatbot", _) => "hey you walked. good for you."
      case (d, _) => "What the heck is an " + d + "?"
    }
  }
}
