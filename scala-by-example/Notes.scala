

object TextExamples {

  def abs(x: Double) = if (x >= 0) x else -x

  val tolerance = 0.0001
  def isCloseEnough(x: Double, y: Double) = abs((x - y) / x) < tolerance
  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = f(guess)
      println(next)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }

  def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) /2
  /* Does not converge. Oscillates between 1.0 and 144.0, for sqrt(144.0)
  def sqrt(x: Double) = fixedPoint(y => x / y)(1.0)
  */
  //def sqrt(x: Double) = fixedPoint(y => (y + x/y)/ 2)(1.0)
  def sqrt(x: Double) = fixedPoint(averageDamp(y => x/y))(1.0)

  def cubert(x: Double) = fixedPoint(averageDamp(y => x/y/y))(1.0)

  def main(args: Array[String]) {
    println(sqrt(144.0))
    println(cubert(27.0))
  }
}
    
