package examples

object Part3Examples {
  def definingFactorial(): Unit = {
    def factorial(n:Int):Int = n match {
      case 0 => 1
      case _ => n * factorial(n-1)
    }
  }
  def replicatingMinimum(): Unit = {
    List(1,2,3,4).min
    // res4: Int = 1
    List('d','z','r','q','y')
    // res5: List[Char] = List(d, z, r, q, y)
    List('d','z','r','q','y').min
    // res6: Char = d
    List("hawkeye", "superman", "spiderman").min
    // res7: String = hawkeye

    def minimum(l:List[Int]):Int = l match {
      case x :: Nil => x
      case x :: y :: Nil => if (x < y) x else y
      case x :: xs => if (x < minimum(xs)) x else minimum(xs)
    }
  }
  def aBetterMinimum(): Unit = {
    def minimum(l:List[Int]):Int = l match {
      case x :: Nil => x
      case x :: xs => {
        val y = minimum(xs)
        if (x < y) x else y
      }
    }
  }
  def reimplementingFill(): Unit = {
    List.fill(7)(0)
    // res0: List[Int] = List(0, 0, 0, 0, 0, 0, 0)
    def moar(howMany:Int)(thing:Int):List[Int] = {
      if (howMany <= 0) Nil
      else thing :: moar(howMany - 1)(thing)
    }
    moar(7)(0) == List.fill(7)(0)
    // res3: Boolean = true
  }
  def reimplementingTake(): Unit = {
    (1 to 10).toList.take(3)
    // res7: List[Int] = List(1, 2, 3)
    List(1,2,3).take(32789213)
    // res8: List[Int] = List(1, 2, 3)
    Nil.take(5)
    // res9: List[Nothing] = List()
    def gimme(howMany:Int, things:List[Int]):List[Int] = {
      if (howMany <= 0) Nil
      else things match {
        case Nil => Nil
        case t :: ts => t :: gimme(howMany - 1, ts)
      }
    }
    val ls = (1 to 10).toList
    // res0: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    gimme(4, ls) == ls.take(4)
    // res1: Boolean = true
  }
  def reimplementingReverse(): Unit = {
    def backwards(things: List[Int]):List[Int] = things match {
      case Nil => Nil
      case t :: ts => backwards(ts) ++ List(t)
    }
    backwards(List(1,2,3,4)) == List(1,2,3,4).reverse
    // res0: Boolean = true
  }
  def reimplementingZip(): Unit = {
    def zip(things: List[Int], moreThings: List[Int]):List[(Int, Int)] =
      (things, moreThings) match {
        case (Nil, _) => Nil
        case (_, Nil) => Nil
        case (t :: ts, m :: ms) => (t, m) :: zip(ts, ms)
      }
    zip(List(1,2,3), List(5,6,7,8)) == List(1,2,3).zip(List(5,6,7,8))
    // res2: Boolean = true
  }
  def reimplementingContains(): Unit = {
    def has(things: List[Any], thing:Any):Boolean = things match {
      case Nil => false
      case (t :: ts) if (t == thing) => true
      case (t :: ts) => has(ts, thing)
    }
    has(List(1,2,3,4), 2) == List(1,2,3,4).contains(2)
    // res4: Boolean = true
  }
  def implementingQuicksort(): Unit = {
    def quicksort(l: List[Int]): List[Int] = l match {
      case Nil => Nil
      case i :: Nil => List(i)
      case pivot :: rest => {
        val left = quicksort(for(x <- rest if (x < pivot)) yield x)
        val right = quicksort(for(x <- rest if (x >= pivot)) yield x)
        left ++ List(pivot) ++ right
      }
    }
    quicksort(List(4, 9, 3, 2, 1, 7, 6, 8, 5))
  }
  def higherOrderFunctions(): Unit = {
    val f:(Int) => Int = _ + 1
    def doTwice(x: Int, f: Int => Int) = f(f(x))
    doTwice(5, _ + 1)
    // res0: Int = 7
    doTwice(3, _ * 7)
    // res1: Int = 147
    def f2(x:Int) = x.toString.reverse.toInt + x
    // f2: (x: Int)Int
    doTwice(123, f2)
    // res4: Int = 888
  }
  def implementingZipwith(): Unit = {
    def zipWith[A,B,C](l:List[A], r:List[B], f:(A,B) => C):List[C] =
      (l, r) match {
        case (Nil, _) => Nil
        case (_, Nil) => Nil
        case (t :: ts, m :: ms) => f(t, m) :: zipWith(ts, ms, f)
      }
    val l = List(1, 2, 3)
    // l: List[Int] = List(1, 2, 3)
    val r = List("happy puppy", "bananas", "generic types")
    // r: List[String] = List(happy puppy, bananas, generic types)
    zipWith(l, r, (a:Int, b:String) => a.toString + " " + b)
    // res6: List[String] = List(1 happy puppy, 2 bananas, 3 generic types)

    def zipWith2[A,B,C](l:List[A], r:List[B])(f: (A, B) => C):List[C] =
      (l, r) match {
        case (Nil, _) => Nil
        case (_, Nil) => Nil
        case (t :: ts, m :: ms) => f(t, m) :: zipWith2(ts, ms)(f)
      }

    val needsWith:((Int,String)=>String)=>List[String] = zipWith(l,r,_)
    // needsWith: ((Int, String) => String) => List[String] = <function1>
    zipWith2(l,r)((a:Int, b:String) => a.toString + " " + b)
    // res0: List[String] = List(1 happy puppy, 2 bananas, 3 generic types)
    needsWith((a:Int, b:String) => a.toString + " " + b)
    // res1: List[String] = List(1 happy puppy, 2 bananas, 3 generic types)
    zipWith2(l,r)((a, b) => a.toString + " " + b)
    // res2: List[String] = List(1 happy puppy, 2 bananas, 3 generic types)
    needsWith((a, b) => a.toString + " " + b)
    // res3: List[String] = List(1 happy puppy, 2 bananas, 3 generic types)
    zipWith2(l,r)(_ + " " + _)
    // res4: List[String] = List(1 happy puppy, 2 bananas, 3 generic types)
    needsWith(_ + " " + _)
    // res5: List[String] = List(1 happy puppy, 2 bananas, 3 generic types)
  }
  def reimplementingMap(): Unit = {
    def map[A, B](l: List[A])(f: A => B): List[B] = l match {
      case Nil => Nil
      case h :: t => f(h) :: map(t)(f)
    }
    map(List(1,2,3,4))(_ + 1)
    // res0: List[Int] = List(2, 3, 4, 5)
    map(List(1,2,3,4))(_ + "!")
    // res1: List[String] = List(1!, 2!, 3!, 4!)
    map(List(1,2,3,4))(n => n * n + 17)
    // res2: List[Int] = List(18, 21, 26, 33)
    for(x <- List(1,2,3)) yield x * x

    List(1,2,3).map(x => x * x)
  }
  def reimplementingFilter(): Unit = {
    def filter[A](l: List[A])(f: A => Boolean): List[A] = l match {
      case Nil => Nil
      case h :: t =>
        if (f(h)) h :: filter(t)(f)
        else filter(t)(f)
    }
    filter(List(1,2,3,4,5,6,7))( _ % 2 != 0)
    // res0: List[Int] = List(1, 3, 5, 7)
    filter(List("apple", "banana", "pickle", "guava"))(_ contains 'p')
    // res1: List[String] = List(apple, pickle)
  }
  def aQuicksortWithFilter(): Unit = {
    def quicksort(l: List[Int]): List[Int] = l match {
      case Nil => Nil
      case i :: Nil => List(i)
      case pivot :: rest => quicksort(rest.filter(_ < pivot)) ++
        List(pivot) ++
        quicksort(rest.filter(_ >= pivot))
    }

    quicksort(List(4, 9, 3, 2, 1, 7, 6, 8, 5))
    // res2: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  }
  def definingCollatzSequence(): Unit = {
    def collatzSequence(from:Int):List[Int] = from match {
      case 1 => List(1)
      case n if(n % 2 == 0) => n :: collatzSequence(n / 2)
      case n => n :: collatzSequence(n * 3 + 1)
    }
    collatzSequence(13).length
    // res0: Int = 10
    (1 to 20).map(collatzSequence).dropWhile(_.length <= 10).head
    // res3: List[Int] = List(7, 22, 11, 34, 17, 52, 26, //…
    (1 until 100).map(collatzSequence).filter(_.length > 10).length
    // res5: Int = 79
  }
  def lambdaFunctions(): Unit = {
    (x:Int) => x + 1
    // res0: Int => Int = <function1>
    val f:Int => Int = _ + 1
    // f: Int => Int = <function1>
    (1 to 100).takeWhile(_ < 10)
    // same as
    (1 to 100).takeWhile(x => x < 10)
  }
  def funWithFolding(): Unit = {
    List(1,2,3,4,5).foldLeft(1)( (acc, i) => acc * i)
    List(1,2,3,4,5).foldRight(1)((acc, i) => acc * i)

    def product(ints: List[Int]) = ints.foldRight(1)((acc,i)=>acc * i)
    product(List(1,2,3,4,5))
    // res1: Int = 120
    product(List(4,2,5,6,2,6,3,9))
    // res1: Int = 120
    product(List(4,2,5,6,2,6,3,9))
    // res2: Int = 77760
  }
  def definingMapAsAFold(): Unit = {
    def map[A, B](l: List[A])(f: A => B): List[B] =
      l.foldRight(Nil: List[B])((x, xs) => f(x) :: xs)

    map(List(1,2,3,4))(_ * 2)
    // res4: List[Int] = List(2, 4, 6, 8)
  }
  def usingReduce(): Unit = {
    List(1,2,3,4).reduceLeft(_*_)
    // res0: Int = 24
    def maxi(l: List[Int]) = l.reduceLeft(_ max _)
    // maxi: (l: List[Int])Int
    maxi(List(3,5,12,6,12,3,6,1,6,34,2))
    // res1: Int = 34
  }
  def composingFunctions(): Unit = {
    val g = (i:Int) => i * 2
    // g: Int => Int = <function1>
    val f = (i:Int) => i + 1
    // f: Int => Int = <function1>
    val h = f compose g
    // h: Int => Int = <function1>
    h(2)
    // res0: Int = 5
    h(2) == f(g(2))
    // res1: Boolean = true
    List(4, 9, 16).map(math.abs _).map(_.toDouble).map(math.sqrt _)
    // res0: List[Double] = List(2.0, 3.0, 4.0)
    List(4, 9, 16).map(i => math.sqrt(math.abs(i).toDouble))
    // res1: List[Double] = List(2.0, 3.0, 4.0)
    val abs = math.abs _
    // abs: Int => Int = <function1>
    val toD = (i:Int) => i.toDouble
    // toD: Int => Double = <function1>
    val sqrt = math.sqrt _
    // sqrt: Double => Double = <function1>
    List(4,9,16).map(sqrt compose toD compose abs)
    // res3: List[Double] = List(2.0, 3.0, 4.0)
  }
}
