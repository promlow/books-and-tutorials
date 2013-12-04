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


