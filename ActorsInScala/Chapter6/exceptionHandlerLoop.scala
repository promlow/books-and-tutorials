import scala.actors.Actor
import scala.actors.Actor._

object A extends Actor {
  def act() {  
    var lastMsg: Option[Symbol] = None
    loopWhile (lastMsg.isEmpty || lastMsg.get != 'stop) {
      react {
        case 'hello =>
          throw new Exception("Error!")
        case any: Symbol =>
          println("your message: " + any)
          lastMsg = Some(any)
      }
    }
  }
  override def exceptionHandler = {
    case e: Exception =>
      println(e.getMessage())
  }
}