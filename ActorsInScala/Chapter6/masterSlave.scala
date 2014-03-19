import scala.actors.Actor
import scala.actors.Actor._

object Master extends Actor {
  def act() {
    Slave ! 'doWork
    react {
      case 'done =>
        throw new Exception("Master crashed")
    }
  }
}
object Slave extends Actor {
  def act() {
    link(Master)
    loop {
      react {
        case 'doWork =>
          println("Done")
          reply('done)
      }
    }
  }
}

Slave.start()
println("Slave state: " + Slave.getState)
Master.start()
while(Master.getState != State.Terminated) {}
println("Master state: " + Master.getState)
println("Slave state: " + Slave.getState)
/* This is supposed to result in output in the REPL where
 * the Slave is Terminated after the Master is Terminated.
 * This code won't load in the REPL because of forward 
 * references, and because it's nondeterministic, the output
 * is as expected. 50/50 whether Slave is Suspended or Terminated
 */