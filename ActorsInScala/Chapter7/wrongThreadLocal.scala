import scala.actors.Actor._

val tname = new ThreadLocal[String] {
  override protected def initialValue = "john"
}
val joeActor = actor {
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
val a = actor {
  println("your name: " + (joeActor !? 'YourName))
  println("your name: " + (joeActor !? 'YourName))
}
a.start()

/**
 * Run it several times and you'll get the following output:
your name: joe jr.
your name: john
 **/