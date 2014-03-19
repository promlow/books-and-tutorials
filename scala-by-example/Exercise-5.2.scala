import scala.annotation.tailrec

object Exercise5dot2 {

  def sum(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0 else f(a) + sumF(a + 1, b)
    sumF
  }

  def sumtail(f: Int => Int)(a: Int, b: Int): Int = {
    @tailrec
    def iter(a: Int, result: Int): Int = {
      if (a > b) result
      else iter(a + 1, f(a) + result)
    }
    iter(a, 0)
  }

  // 5.2.2
  def product(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 1
    else f(a) * product(f)(a + 1, b)

  //5.2.3
  def factorial(x: Int) =
    product(x => x)(1, x)

  //5.2.4
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))

  def product2(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)
  def sum2(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x + y, 0)(a, b)

  def main(args: Array[String]) {
    println(product(x => x * x)(2, 3))
    println(factorial(5))
    println(product2(x => x * x)(2, 3))
    println(sum(x => x)(1, 5))
    println(sum2(x => x)(1, 5))
    println(sumtail(x => x)(1, 5))
  }
}


