import scala.actors.Actor
import scala.actors.Actor._

case class User(name: String)

case class Subscribe(user: User)

case class Unsubscribe(user: User)

case class Post(msg: String)

case class UserPost(user: User, post:Post)

class ChatRoom extends Actor {

  def act() {
    // the actor's behavior
    var session = Map.empty[User, Actor]
    while (true)
    {
      receive {
        case Subscribe(user) => 
	  val subscriber = sender
	  val sessionUser =
            actor {
	      while (true) {
                self.receive {
                  case Post(msg) => subscriber ! Post(msg)
                }
              }
            }
          session = session + (user -> sessionUser)
	  reply("Subscribed " + user)
        case Unsubscribe(user) => //handle unsubscriptions
        case UserPost(user, post) => //handle user posts
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

