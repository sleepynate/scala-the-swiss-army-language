package examples

object Part1Examples {
  def mathWorksAsExpected(): Unit = {
    5 + 82
    37 * 18
    3293 - 3721
    7 / 3
    7 / 3.0
  }
  def booleanLogicNoSurprises(): Unit = {
    true && true
    false && true
    true || false
    !false
    !(true && true || false)
  }
  def equalityShouldSeemFamiliar(): Unit = {
    1 == 2
    3 == 3
    7 != 9
    0 != 0
  }
  def butEqualityIsBetterThanEver(): Unit = {
    "Scala" == "Scala"
    "Scala" == "Crappy"
    "Scala" != "Crappy"
    "Scala" == 7
    "7" == 7
    '7' == 7
    "Scala" == 'Scala
  }
  def operatorsAreJustMethods(): Unit = {
    5.==(6)
    5 ==(6)
    5 == 6
  }
  def assigningAndDefining(): Unit = {
    val a = 5
    var b = 6
    def c = 7
    a + b
    a + c
    def d = a + b
    d
  }
  def definingMethodsWithArguments(): Unit = {
    def addOne(x:Int):Int = x + 1
    val addAnotherOne = (x:Int) => x + 1
    addAnotherOne(4)
    def addWeirdOne = (x:Int) => x + 1
    addWeirdOne(5)
    val addHardToTypeOne:(Int => Int) = x => x + 1
    def hardToTypeMethod:(Int => Int) = addHardToTypeOne
    addHardToTypeOne(6)
    hardToTypeMethod(7)
  }
  def asbtractingMethods(): Unit = {
    def twiceTheFun(x:Int):Int = x + x
    twiceTheFun(8)
    twiceTheFun(9)
    def sumOf(x:Int, y:Int):Int = x + y
    def addOne(x:Int):Int = sumOf(x,1)
    def twiceTheFun(x:Int):Int = sumOf(x,x)
    addOne(5)
    twiceTheFun(8)
    sumOf(5, 8)
    def someDoublyFun(x:Int,y:Int) = sumOf(twiceTheFun(x), twiceTheFun(y))
    someDoublyFun(5,8)
  }
  def conditionalsAreStandard(): Unit = {
    // But pay attention to the return types!
    if (1 > 0) 5
    //res0: AnyVal = 5
    if (1 > 0) 5 else 10
    //res1: Int = 5
  }
  def aConditionalFunctionThatIsPure(): Unit = {
    def nextCollatz(i:Int) = {
      if (i % 2 == 0) i / 2
      else 3 * i + 1
    }
    nextCollatz(13)
    // res0: Int = 40
    nextCollatz(40)
    // res1: Int = 20
    nextCollatz(20)
    // res2: Int = 10
    nextCollatz(10)
    // res3: Int = 5
    nextCollatz(5)
    // res4: Int = 16
    nextCollatz(16)
    // res5: Int = 8
    nextCollatz(8)
    // res6: Int = 4
    nextCollatz(4)
    // res7: Int = 2
    nextCollatz(2)
    // res8: Int = 1
  }
  def basicListStuff(): Unit = {
    List(1,2,3)
    List("Galactus", "Mephisto", "Doom", "The Stranger")
    List()
    List(1, "derp")
    // res3: List[Any] = List(1, derp)
    List(1, 2, 3) ++ List(4, 5)
    // res4: List[Int] = List(1, 2, 3, 4, 5)
    1 :: List(2)
    // res5: List[Int] = ```List(1, 2)```
  }
  def usingLists(): Unit = {
    1 :: 2 :: 3 :: 4 :: Nil
    // res10: List[Int] = List(1, 2, 3, 4)
    1 :: 2 :: 3 :: List(4)
    // res11: List[Int] = List(1, 2, 3, 4)
    1 :: 2 :: List(3, 4)
    // res12: List[Int] = List(1, 2, 3, 4)
    1 :: List(2, 3, 4)
    // res13: List[Int] = List(1, 2, 3, 4)
    val l = List(1,2,3)
    // l: List[Int] = List(1, 2, 3)
    l(1)
    // res1: Int = 2
    l.apply(1)
    // res2: Int = 2
    l.apply(3)
    // java.lang.IndexOutOfBoundsException: 3
    l.head
    // res3: Int = 1
    l.tail
    // res4: List[Int] = List(2, 3)
    l.last
    // res5: Int = 3
    l.init
    // res6: List[Int] = List(1, 2)
    List().head
    // java.util.NoSuchElementException: head of empty list
    List().tail
    // java.lang.UnsupportedOperationException: tail of empty list
    l.length
    // res9: Int = 3
    l.size
    // res10: Int = 3
    l.isEmpty
    // res11: Boolean = false
    List().isEmpty
    // res12: Boolean = true
    l.take(1)
    // res16: List[Int] = List(1)
    l.take(3)
    // res17: List[Int] = List(1, 2, 3)
    l.take(2)
    // res18: List[Int] = List(1, 2)
    l.take(4)
    // res19: List[Int] = List(1, 2, 3)
    List().take(1)
    // res20: List[Nothing] = List()
    List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).drop(2)
    // res21: List[Int] = List(3, 4, 5, 6, 7, 8, 9, 10)
    List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).drop(8)
    // res22: List[Int] = List(9, 10)
    List().drop(9001)
    // res23: List[Nothing] = List()
    List(1,2,3).reverse
    // res24: List[Int] = List(3, 2, 1)
    List(1,2,3).max
    // res25: Int = 3
    List(1,2,3).min
    // res26: Int = 1
    List("jane", "kid koala", "bob").min
    // res27: String = bob
    List("jane", "kid koala", "bob").max
    // res28: String = kid koala
    List("jane", "kid koala", "bob").sorted
    // res29: List[String] = List(bob, jane, kid koala)
    List(1, 4, 6, 2, 6, 7.0).sum
    // res30: Double = 26.0
    List(1, 4, 6, 2, 6, 7.0).product
    // res31: Double = 2016.0
  }
  def rangesAndComprehensions(): Unit = {
    1 until 10
    // res0: scala.collection.immutable.Range = Range(1, 2, 3, 4, 5, 6, 7, 8, 9)
    (1 to 10).max
    // res2: Int = 10
    ('a' until 'm').min
    // res3: Char = a
    (5 to 20 by 5).sum
    // res4: Int = 50
    2 to 10 by 2
    // res0: scala.collection.immutable.Range = Range(2, 4, 6, 8, 10)
    (2 to 5000 by 2).take(5)
    // res1: scala.collection.immutable.Range = Range(2, 4, 6, 8, 10)
    for (x <- 1 to 5) yield x * 2
    // res2: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 4, 6, 8, 10)
    for (x <- -10 to 10 if (x > 5 || x < -5)) yield x * 2
    // res3: IndexedSeq[Int] = Vector(-20, -18, -16, -14, -12, 12, 14, //…)
    for (x <- 1 to 3; y <- 5 to 15 by 5) yield y + x
    // res12: IndexedSeq[Int] = Vector(6, 11, 16, 7, 12, 17, 8, 13, 18)
    for (x<-1 to 3; y<-5 to 15 by 5; if((x+y)%2==0)) yield y + x
    // res14: IndexedSeq[Int] = Vector(6, 16, 12, 8, 18)
  }
  def usingTuples(): Unit = {
    (1, "two")
    // res0: (Int, String) = (1,two)
    List(1, "two")
    // res1: List[Any] = List(1, two)
    ('a', 4, "woohoo!", 'b)
    // res2: (Char, Int, String, Symbol) = (a,4,woohoo!,'b)
    (List('a', 'b', 'c'), "easy as", List(1, 2, 3),
      "simple as", List("do", "re", "mi"), 'abc, 123)
    // res3: (List[Char], String, List[Int], String, List[String], Symbol, Int) = …
    (1, "two")._1
    // res4: Int = 1
    (1, "two")._2
    // res5: String = two
    List(1,2,3,4,5,6).zip(List("a", "b", "c", "d"))
    // res6: List[(Int, String)] = List((1,a), (2,b), (3,c), (4,d))
  }
  def perimeters1(): Unit = {
    def allTriangles(a:Int) =
      for(x <- 1 to a;
          y <- 1 to a;
          z <- 1 to a) yield (x, y, z)
  }
  def perimeters2(): Unit = {
    def rightTriangles(a:Int) =
      for(x <- 1 to a;
          y <- 1 to a;
          z <- 1 to a;
          if (Math.pow(x,2) + Math.pow(y,2) == Math.pow(z,2)))
        yield (x, y, z)


    rightTriangles(5)
  }
  def perimeters3(): Unit = {
    def rightTriangles(a:Int) =
      for(z <- 1 to a;
          y <- 1 to z;
          x <- 1 to y;
          if (Math.pow(x,2) + Math.pow(y,2) == Math.pow(z,2)))
        yield (x, y, z)


    rightTriangles(5)
  }
  def perimeters4(): Unit = {
    def rightTriangles(a:Int, b:Int) =
      for(z <- 1 to a;
          y <- 1 to z;
          x <- 1 to y;
          if (Math.pow(x,2) + Math.pow(y,2) == Math.pow(z,2));
          if (x + y + z) == b)
        yield (x, y, z)


    rightTriangles(5, 12)
    rightTriangles(50, 24)
    // res4: IndexedSeq[(Int, Int, Int)] = Vector((6,8,10))
    rightTriangles(50, 30)
    // res5: IndexedSeq[(Int, Int, Int)] = Vector((5,12,13))
    rightTriangles(50, 44)
    // res6: IndexedSeq[(Int, Int, Int)] = Vector()
    rightTriangles(50, 40)
    // res7: IndexedSeq[(Int, Int, Int)] = Vector((8,15,17))

    rightTriangles(5, 12) map { t =>
      (180 * Math.acos(t._2/5.0) / Math.PI,
        180 * Math.acos(t._1/5.0) / Math.PI)
    }
  }
}
