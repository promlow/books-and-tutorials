import scala.actors.Actor
import scala.actors.Actor._

object Chain {
  def buildChain(size: Int, next: Actor): Actor = {
    val a = actor {
      receive {
        case 'Die =>
          val from = sender
          if (next != null) {
            next ! 'Die
            receive {
              case 'Ack => from ! 'Ack
            }
          }else from ! 'Ack
      }
    }
    if (size > 0) buildChain(size - 1, a)
    else a
  }

/* scala 5.1.thread-based.scala 10,000 will result in
 * OutOfMemoryError on my machine
 */
  def main(args: Array[String]) {
    val numActors = args(0).toInt
    val start = System.currentTimeMillis
    buildChain(numActors, null) ! 'Die
    receive {
      case 'Ack =>
        val end = System.currentTimeMillis
        println("Took "+ (end - start) +" ms")
    }
  }
}