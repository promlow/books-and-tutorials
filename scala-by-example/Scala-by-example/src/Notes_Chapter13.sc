object Notes_Chapter13 {
  val it: Iterator[Int] = Iterator.range(1,100)   //> it  : Iterator[Int] = non-empty iterator
  while (it.hasNext){
  	val x = it.next
  	//println(x * x)
  }
  
}