package examples

object Part4Examples {
  def classes(): Unit = {
    class Foo {
      val dingus = "wingle wangle"
      def exclaimDingus = dingus + "!"
    }
    val foo = new Foo
    foo.dingus
    //res0: String = wingle wangle
    foo.exclaimDingus
    // res1: String = wingle wangle!
  }
  def classes2(): Unit = {
    class Foo(val dingus:String) {
      def exclaimDingus = dingus + "!"
    }
    val foo1 = new Foo("wingle")
    // foo1: derp.Foo = derp.Foo@76a11b29
    val foo2 = new Foo("wangle")
    // foo2: derp.Foo = derp.Foo@92260b5
    foo1.exclaimDingus + " " + foo2.exclaimDingus
    // res0: String = wingle! wangle!
  }
  def classes3(): Unit = {
    class Foo(val dingus:String, val punc: String, val howExcited:Int) {
      def this(dingus:String) = this(dingus, "!", 1)
      def this(dingus:String, punc:String) = this(dingus, punc, 1)
      def exclaimDingus = dingus + punc * howExcited
    }
    val foo = new Foo("yay")
    // res0: derp.Foo = derp.Foo@3881bb1
    foo.exclaimDingus
    // res1: String = yay!
    new Foo("f yea scala!", "?!", 4).exclaimDingus
    // res3: String = f yea scala!?!?!?!?!
  }
  def caseClasses(): Unit = {
    case class Person(first:String, last:String)
    val p1 = new Person("nathan", "dotz")
    val p2 = Person("martin", "odersky")
    p1 == p2
    // res3: Boolean = false
    p2 match {case Person("martin", _)=>println("found a marty!")}
    // found a marty!
  }
  def objects(): Unit = {
    object Ooble {
      def plop(s:String) = "*"+s+"*"
    }
    Ooble.plop("boop")
    // res0: String = *boop*
    // scala> ```new Ooble()```
    // <console>:12: error: not found: type Ooble
    //   new Ooble()
  }
  def companionObjects(): Unit = {
    class Digboogy(val i: Int, val s: String) {
      override def toString = "Digboody("+i+","+s+")"
    }
    object Digboogy {
      private var digboogyCount = 0
      def moar = {
        digboogyCount = digboogyCount + 1
        new Digboogy(digboogyCount, "I number "+digboogyCount)
      }
    }
    Digboogy.moar
    // res4: Digboogy = Digboody(1,I number 1)
    Digboogy.moar
    // res5: Digboogy = Digboody(2,I number 2)
    Digboogy.moar
    // res6: Digboogy = Digboody(3,I number 3)
    Digboogy.moar
    // res7: Digboogy = Digboody(4,I number 4)
    Digboogy.moar
    // res8: Digboogy = Digboody(5,I number 5)
  }
  def traits(): Unit = {
    trait PrintsItself { def print = println(this) }

    class Foo extends PrintsItself
    val f = new Foo
    f.print

    class Bar extends Foo
    val b = new Bar
    b.print
    // $line7.$read$$iw$$iw$Bar@1cad8848

    val o = new Object with PrintsItself
    o.print
    // $line15.$read$$iw$$iw$$anon$1@2b46950c
  }
  def definingANewBoolean(): Unit = {
    sealed abstract trait Bouillon
    case object True extends Bouillon
    case object False extends Bouillon
    def bap(b: Bouillon) = b match {
      case True => "You're right!"
      case False => "Incorrect"
    }
    bap(True)
    // res0: String = You're right!
    bap(False)
    // res1: String = Incorrect
  }
  def refiningTheNewBoolean(): Unit = {
    sealed abstract trait Bouillon {
      def ==(that: Bouillon): Bouillon
      def unary_! : Bouillon
      def &&(that: => Bouillon): Bouillon
      def ||(that: => Bouillon): Bouillon
    }
    case object True extends Bouillon {
      override def ==(that: Bouillon): Bouillon = that match {
        case True => True
        case _ => False
      }
      override def unary_! : Bouillon = False
      override def &&(that: => Bouillon): Bouillon = that
      override def ||(that: => Bouillon): Bouillon = this
    }
    case object False extends Bouillon {
      override def ==(that: Bouillon): Bouillon = that match {
        case False => True
        case _ => False
      }
      override def unary_! : Bouillon = True
      override def &&(that: => Bouillon): Bouillon = this
      override def ||(that: => Bouillon): Bouillon = that
    }
    False == True
    // res0: Bouillon = True
    False == !True
    // res1: Bouillon = True
    (!False && !False) || (False || True || True && False)
    // res2: Bouillon = True
  }
  def aSimplerOption(): Unit = {
    object Option {
      def apply[A](x: A): Option[A] = if (x == null) None else Some(x)
    }
    sealed abstract class Option[+A] {
      def get: A
    }
    final case class Some[+A](x: A) extends Option[A] {
      def get = x
    }
    case object None extends Option[Nothing] {
      def get = throw new NoSuchElementException("None.get")
    }
    val x = Option(5)
    // x: Option[Int] = Some(5)
    val y = Option(7)
    // y: Option[Int] = Some(7)
    val n = Option(null)
    // n: Option[Null] = None
    x.get
    // res0: Int = 5
    n.get
    // java.util.NoSuchElementException: None.get

    def sumSomes(a: Option[Int], b: Option[Int]) = (a, b) match {
      case (Some(x), Some(y)) => x + y
      case _ => 0
    }
    sumSomes(x, y)
    // res4: Int = 12

    // sumSomes(x, n)
    // <console>:12: error: type mismatch;
    //  found   : Option[Null]
    //  required: Option[Int]
    //  sumSomes(x, n)
    //              ^
  }
  def whyDefineOption(): Unit = {
    List[Int]().head * 2
    // java.util.NoSuchElementException: head of empty list
    List(2).map(_ * 2)
    // res1: List[Int] = List(4)
    List[Int]().map(_ * 2)
    // res2: List[Int] = List()
    None.map((x:Int) => x * 2)
    // res5: Option[Int] = None
    Option(2).map((x:Int) => x * 2)
    // res6: Option[Int] = Some(4)
    Option(2).map(_ * 2).map(_ + 3).map(_ toString).map(_ + "!!")
    // res7: Option[String] = Some(7!!!!)
    (None:Option[Int]).map(_ * 2).map(_ + 3).map(_ toString).map(_ + "!!")
    // res8: Option[String] = None
  }
  def recursiveTypes(): Unit = {
    1 :: 2 :: 3 :: 4 :: Nil
    // res10: List[Int] = List(1, 2, 3, 4)
    Nil
    // res0: scala.collection.immutable.Nil.type = List()
    4 :: Nil
    // res1: List[Int] = List(4)
 }
  def definingANewList(): Unit = {
    sealed trait Lyst[+A] {
      def ::[A](that: A) = Cawns(that, this)
    }
    case object Nihil extends Lyst[Nothing]
    case class Cawns[+A](head: A, tail: Lyst[A]) extends Lyst[A]

    val l1 = Cawns(5, Nihil)
    // l1: Cawns[Int] = Cawns(5,Nihil)
    val l2 = Cawns(4, l1)
    // l2: Cawns[Int] = Cawns(4,Cawns(5,Nihil))
    val l3 = Cawns(3, l2)
    // l3: Cawns[Int] = Cawns(3,Cawns(4,Cawns(5,Nihil)))
    val l4 = 2 :: l3
    // l4: Cawns[Any] = Cawns(2,Cawns(3,Cawns(4,Cawns(5,Nihil))))
    0 :: 1 :: l4
    // res4: Cawns[Any] = Cawns(0,Cawns(1,Cawns(2,Cawns(3,Cawns(4,Cawns(5,Nihil))))))
  }
  def aNewListWithTypeBoundaries(): Unit = {
    sealed trait Lyst[+A] {
      def ::[B >: A](that: B):Lyst[B] = Cawns(that, this)
    }
    case object Nihil extends Lyst[Nothing]
    case class Cawns[+A](head: A, tail: Lyst[A]) extends Lyst[A]

    val l5 = "fun" :: Nihil
    // l5: Lyst[String] = Cawns(fun,Nihil)
    val l6 = "scala" :: "is" :: l5
    // l6: Lyst[String] = Cawns(scala,Cawns(is,Cawns(fun,Nihil)))
    val l7 = 5 :: l6
    // l7: Lyst[Any] = Cawns(5,Cawns(scala,Cawns(is,Cawns(fun,Nihil))))
  }
  def implicitConversions(): Unit = {
    implicit def makeStringsInts(s: String) = s.toInt
    val a: Int = "3719"
    // a: Int = 3719
  }
  def theTypeClassPattern(): Unit = {
    object JustMathyThings {
      trait Mathy[A] {
        def add(x: A, y: A): A
        def subtract(x: A, y: A): A
        def multiply(x: A, y: A): A
        def divide(x: A, y: A): A
      }
      implicit object MathyDoubles extends Mathy[Double] {
        override def add(x:Double, y:Double):Double = x + y
        override def subtract(x:Double, y:Double):Double = add(x, -y)
        override def multiply(x:Double, y:Double):Double = x * y
        override def divide(x:Double, y:Double):Double = multiply(x, 1 / y)
      }
      implicit object MathyStrings extends Mathy[String] {
        override def add(x:String, y:String):String = x + y
        override def divide(x:String, y:String):String = x.splitAt(y.length)._1
        override def multiply(x:String, y:String):String = x * y.length
        override def subtract(x:String, y:String):String = x.splitAt(y.length)._2
      }
    }
    import JustMathyThings.Mathy

    def doTheAddings[T](x: T, y: T)(implicit m: Mathy[T]) = m.add(x, y)
    // doTheAddings: [T](x: T, y: T)(implicit m: mathy.JustMathyThings.Mathy[T])T

    println(doTheAddings(5.0, 2.0))
    // 7.0

    doTheAddings("five", "two point oh")
    // res8: String = fivetwo point oh

  }
}
