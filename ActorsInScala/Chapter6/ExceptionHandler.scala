import scala.actors.Actor
import scala.actors.Actor._

object A extends Actor {
  def act() {
    react {
      case 'hello =>
        throw new Exception("Error!")
    }
  }
  override def exceptionHandler = {
    case e: Exception =>
      println(e.getMessage())
  }
}


object B extends Actor {
  def act() {
    react {
      case 'hello =>
        throw new Exception("Error!")
    }
  }
}

A.start()
A ! 'hello

////
// Uncomment to see an unhandled exception
// ouput may be interleaved with handled exception example
////
//B.start()
//B ! 'hello
