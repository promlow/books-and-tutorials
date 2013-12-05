import scala.actors.Actor._
import scala.actors.Actor
import scala.actors.SchedulerAdapter


abstract class ActorWithThreadLocal(private var name: String)
  extends Actor {

  override val scheduler = new SchedulerAdapter {
    def execute(codeBlock: => Unit): Unit =
    ActorWithThreadLocal.super.scheduler execute {
      tname set name
      codeBlock
      name = tname.get
    }
  }
}

val tname = new ThreadLocal[String] {
  override protected def initialValue = "john"
}

val joeActor = new ActorWithThreadLocal("joe jr.") {
  def act() {
    react {
      case 'YourName =>
        tname set "joe jr."
        sender ! tname.get
        react {
          case 'YourName =>
            sender ! tname.get
        }
    }
  }
}
val a = actor {
  println("your name: " + (joeActor !? 'YourName))
  println("your name: " + (joeActor !? 'YourName))
}
a.start()

