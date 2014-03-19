
object TextExamples {

  class Rational(n: Int, d: Int) extends AnyRef {
    private def gcd(x: Int, y: Int): Int = {
      if (x == 0) y
      else if (x < 0) gcd(-x, y)
      else if (y < 0) gcd(x, -y)
      else gcd(y % x, x)
    }

    private val g = gcd(n, d)

    val numer: Int = n/g
    val denom: Int = d/g

    def +(that: Rational) =
      new Rational(numer * that.denom + that.numer * denom,
		   denom * that.denom)
    def -(that: Rational) =
      new Rational(numer * that.denom - that.numer * denom,
		   denom * that.denom)
    def *(that: Rational) =
      new Rational(numer * that.numer, denom * that.denom)
    def /(that: Rational) = 
      new Rational(numer * that.denom, denom * that.numer)

    override def toString = ""+ numer +"/"+ denom
    
    def square = new Rational(numer * numer, denom * denom)
  }

  abstract class IntSet {
    def incl(x: Int): IntSet
    def contains(x: Int): Boolean
  }

  class EmptySet extends IntSet {
    def contains(x: Int): Boolean = false
    def incl(x: Int): IntSet = new NonEmptySet(x, new EmptySet, new EmptySet)
  }

  class NonEmptySet(elem: Int, left: IntSet, right: IntSet) extends IntSet {
    def contains(x: Int): Boolean =
      if (x < elem) left contains x
      else if (x > elem) right contains x
      else true
    def incl(x: Int): IntSet =
      if (x < elem) new NonEmptySet(elem, left incl x, right)
      else if (x > elem) new NonEmptySet(elem, left, right incl x)
      else this
  }

  def main(args: Array[String]) {
    /*var i = 1
    var x = new Rational(0, 1)
    while (i <= 10) {
      x += new Rational(1, i)
      i += 1
    }
    println(x)
    val r = new Rational(3, 4)
    println(r.square)
    */
    val x = new Rational(1,3)
    val y = new Rational(5,7)
    val z = new Rational(3,2)
    println(x - y - z)
  }
}
