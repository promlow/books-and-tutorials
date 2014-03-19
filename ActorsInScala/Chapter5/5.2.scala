import scala.actors.Actor._

val a = actor {
  react {
    case "hello" =>
      println("a: hi")
  }
 println("a: finished")
}

val b = actor {
  react {
    case "hello" =>
      println("b: hi")
      println("b: finished")
  }
}

a ! "hello"
b ! "hello"

/*
 * 
OUTPUT
scala 5.2.scala 
a: hi
b: hi
b: finished

The fact that the current thread’s call stack is discarded when an event-
based actor suspends bears an important consequence on the event-based
actor programming model: a call to react never returns normally. Instead,
react always throws an internal control exception, which the actor runtime
handles.
*/
