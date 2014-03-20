
object TextExamples {

  /*
  abstract class IntStack {
    def push(x: Int): IntStack = new IntNonEmptyStack(x, this)
    def isEmpty: Boolean
    def top: Int
    def pop: IntStack
  }

  class IntEmptyStack extends IntStack {
    def isEmpty = true
    def top = sys.error("EmptyStack.top")
    def pop = sys.error("EmptyStack.pop")
  }

  class IntNonEmptyStack(elem: Int, rest: IntStack) extends IntStack {
    def isEmpty = false
    def top = elem
    def pop = rest
  }
  */
  /* Stacks replaced by new that have co-variant subtyping, generalized push
   * method and a single object for the empty stack
   *
  abstract class Stack[A] {
    def push(x: A): Stack[A] = new NonEmptyStack[A](x, this)
    def isEmpty: Boolean
    def top: A
    def pop: Stack[A]
  }

  class EmptyStack[A] extends Stack[A] {
    def isEmpty = true
    def top = sys.error("EmptyStack.top")
    def pop = sys.error("EmptyStack.pop")
  }

  class NonEmptyStack[A](elem: A, rest: Stack[A]) extends Stack[A] {
    def isEmpty = false
    def top = elem
    def pop = rest
  }
  */

  abstract class Stack[+A] {
    def push[B >: A](x: B): Stack[B] = new NonEmptyStack(x, this)
    def isEmpty: Boolean
    def top: A
    def pop: Stack[A]
  }
  object EmptyStack extends Stack[Nothing] { //Nothing is a subtype of every other type
    def isEmpty = true
    def top = sys.error("EmptyStack.top")
    def pop = sys.error("EmptyStack.pop")
  }

  class NonEmptyStack[+A](elem: A, rest: Stack[A]) extends Stack[A] {
    def isEmpty = false
    def top = elem
    def pop = rest
  }
  
  def isPrefix[A](p: Stack[A], s: Stack[A]): Boolean = {
    p.isEmpty ||
    p.top == s.top &&
    isPrefix[A](p.pop, s.pop)
  }
  
  /********
   * Change to use view bounds, instead of type bounds.
   * The type is able to be converted to Ordered
   *
  abstract class Set[A <: Ordered[A]] {
    def incl(x: A): Set[A]
    def contains(x: A): Boolean
  }

  class EmptySet[A <: Ordered[A]] extends Set[A] {
    def contains(x: A): Boolean = false
    def incl(x: A): Set[A] = new NonEmptySet(x, new EmptySet[A], new EmptySet[A])
  }

  class NonEmptySet[A <: Ordered[A]]
	  (elem: A, left: Set[A], right: Set[A]) extends Set[A] {
    def contains(x: A): Boolean =
      if (x < elem) left contains x
      else if (x > elem) right contains x
      else true
    def incl(x: A): Set[A] =
      if (x < elem) new NonEmptySet(elem, left incl x, right)
      else if (x > elem) new NonEmptySet(elem, left, right incl x)
      else this
  }

  case class Num(value: Double) extends Ordered[Num] {
    def compare(that: Num): Int =
      if (this.value < that.value) -1
      else if (this.value > that.value) 1
      else 0
  }
  */ 

  abstract class Set[A <% Ordered[A]] {
    def incl(x: A): Set[A]
    def contains(x: A): Boolean
  }

  class EmptySet[A <% Ordered[A]] extends Set[A] {
    def contains(x: A): Boolean = false
    def incl(x: A): Set[A] = new NonEmptySet(x, new EmptySet[A], new EmptySet[A])
  }

  class NonEmptySet[A <% Ordered[A]]
	  (elem: A, left: Set[A], right: Set[A]) extends Set[A] {
    def contains(x: A): Boolean =
      if (x < elem) left contains x
      else if (x > elem) right contains x
      else true
    def incl(x: A): Set[A] =
      if (x < elem) new NonEmptySet(elem, left incl x, right)
      else if (x > elem) new NonEmptySet(elem, left, right incl x)
      else this
  }

  case class Num(value: Double) {
    def compare(that: Num): Int =
      if (this.value < that.value) -1
      else if (this.value > that.value) 1
      else 0
  }

  //def divmod(x: Int, y: Int) = new Tuple2[Int, Int](x / y, x % y)
  def divmod(x: Int, y: Int): (Int, Int) = (x / y, x % y)

  def main(args: Array[String]) {

    val x = EmptyStack
    val y = x.push(1).push(2)
    println(y.pop.top)

    val s1 = EmptyStack.push("abc")
    val s2 = EmptyStack.push("abx").push(s1.top)
    println(isPrefix[String](s1, s2))
    /*
    val x = new EmptyStack[Int]
    val y = x.push(1).push(2)
    println(y.pop.top)

    val s1 = new EmptyStack[String].push("abc")
    val s2 = new EmptyStack[String].push("abx").push(s1.top)
    println(isPrefix[String](s1, s2))
    */
    //val s = new EmptySet[Num].incl(Num(1.0)).incl(Num(2.0))
    //s.contains(Num(1.5))
    //val s = new EmptySet[Double].incl(1.0).incl(2.0)
    val s = new EmptySet[Double].incl(1.0).incl(2.0)
    println(s.contains(1.5))

    val xy = divmod(5, 6)
    println("quotient: "+ xy._1 +", rest: "+ xy._2)
    divmod(9, 4) match {
      case Tuple2(n, d) =>
	println("quotient: "+ n +", rest: "+ d)
    }
    divmod(88,33) match {
      case (n,d) => println("quotient: "+ n +", rest: "+ d)
    }
  }
}
