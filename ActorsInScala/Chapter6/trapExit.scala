import scala.actors.Exit
import scala.actors.Actor._
import scala.actors.UncaughtException

val a = actor {
  react {
    case 'start =>
      val somethingBadHappened = false
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
    //case Exit(from, reason) if from == a =>
    case Exit(from, UncaughtException(_, _, _, _, cause))
        if from == a =>
      println("Actor 'a' terminated because of " + cause)
  }
}
/**
 * Change from: case Exit(from, reason) if from == a =>
 * to: case Exit(from, UncaughtException(_, _, _, _, cause))
        if from == a =>
 * results in output change from:
 * Actor 'a' terminated because of UncaughtException(scala.actors.Actor$$anon$1@1742700,Some('start),Some(scala.actors.Actor$$anon$1@16c79d7),java.lang.Exception: Error!)
 * to: Actor 'a' terminated because of java.lang.Exception: Error!
 **/