import scala.actors.Actor
import scala.actors.scheduler.DaemonScheduler

object DaemonActors {

  class MyDaemon extends Actor {
    override def scheduler = DaemonScheduler
    def act() {
      loop {
        react { case num: Int => reply(num + 1) }
      }
    }
  }

  def main(args: Array[String]) {
    val d = (new MyDaemon).start()
    println(d !? 41)
  }
}