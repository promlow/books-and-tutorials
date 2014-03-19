import scala.actors.Actor
import scala.actors.Actor._

case class User(name: String)

case class Subscribe(user: User)

case class Unsubscribe(user: User)

case class Post(msg: String)

case class UserPost(user: User, post:Post)

class ChatRoom extends Actor {
  var session = Map.empty[User, Actor]

  def act() {
    // the actor's behavior
    while (true)
    {
      receive {
        case Subscribe(user) => 
	  val subscriber = sender
	  val sessionUser =
            actor {
	      while (true) {
                self.receive {
                  case Post(msg) => 
                    subscriber ! Post(msg)
                    println(user +" received:"+ msg)
                }
              }
            }
          session = session + (user -> sessionUser)
	  reply("Subscribed " + user)
        case Unsubscribe(user) =>
	  session = session - user
          reply("Unsubscribed " + user)
        case UserPost(user, post) => 
	  println(user +" posted:"+ post)
          for (key <- session.keys; if key != user){
            session(key) ! post
          }
      }
    }
  }   
}

val chatRoom = new ChatRoom
chatRoom.start()

//Synchronous
chatRoom !? Subscribe(User("Bob")) match {
  case response: String => println(response)
}
chatRoom ! Subscribe(User("Me"))

chatRoom ! UserPost(User("Me"), Post("Hi Bob"))

chatRoom ! UserPost(User("Bob"), Post("Hi you"))

for(p <- 1 to 100){ //Should arrive out of order, some anyway
  chatRoom ! UserPost(User("Me"), Post("Me"+p))
  chatRoom ! UserPost(User("Bob"), Post("Bob"+p))
}

//Future
val future = chatRoom !! Unsubscribe(User("Bob"))

val waiting = List("waiting... " * 10)
waiting.foreach(w => print(w))
println()

println(future())

chatRoom !? Unsubscribe(User("Me"))

