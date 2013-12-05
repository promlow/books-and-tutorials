Actors in Scala

http://www.artima.com/shop/actors_in_scala


=============

Running instructions/reminders

```Shell
cd Chapter4
scala ChatRoom.scala
```

```Shell
cd Chapter5
scala 5.1.scala <integer>
scala 5.1.thread-based.scala 1000 -Dactor.corePoolSize=1000 actor.maxPoolSize=1010
scala 5.2.scala
```
Interesting to see how well this scales when increasing from 100 to 1000 to 10000

The thread-based version's performance was OK up to about 10,000 when the JVM runs out of memory. Even tuning the actor.corePoolSize=1000 and actor.maxPoolSize=1010 JVM properties, there wasn't much performance difference.

Check comments within 5.2.scala for an explanation of behavior


```Shell
cd Chapter6
scala ExceptionHandler.scala
```

```
scala> :load exceptionHandlerLoop.scala
Loading exceptionHandlerLoop.scala...
import scala.actors.Actor
import scala.actors.Actor._
defined module A

scala> A.start()
res0: scala.actors.Actor = A$@1efbbb1

scala> A ! 'hello

scala> Error!


scala> A.getState
res2: scala.actors.Actor.State.Value = Suspended

scala> A ! 'hi
your message: 'hi

scala> A ! 'stop
your message: 'stop

scala> A.getState
res5: scala.actors.Actor.State.Value = Terminated

scala> 
```