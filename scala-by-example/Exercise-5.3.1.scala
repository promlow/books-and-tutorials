

object Exercise5dot3dot1 {

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

  def cubert(x: Double) = fixedPoint(averageDamp(y => x/y/y))(1.0)

  def main(args: Array[String]) {
    println(cubert(729.0))
  }
}
    
