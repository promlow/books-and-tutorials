import scala.actors.Exit
import scala.actors.Actor._

val a = actor {
  react {
    case 'start =>
      val somethingBadHappened = true
      if (somethingBadHappened)
        throw new Exception("Error!")
      println("Nothing bad happened")
  }
}
val b = actor {
  self.trapExit = true
  link(a)
  a ! 'start
  react {
    case Exit(from, reason) if from == a =>
      println("Actor 'a' terminated because of " + reason)
  }
}
