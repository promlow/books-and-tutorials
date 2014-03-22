object Exercise1121 {
  
  def whileLoop(condition: => Boolean)(command: => Unit) {
  	if (condition) {
  		command; whileLoop(condition)(command)
  	} else ()
  }                                               //> whileLoop: (condition: => Boolean)(command: => Unit)Unit
  
  //repeatLoop {command} ( condition )
  def repeatLoop(condition: => Boolean)(command: => Unit){
  	command
  	if (condition) repeatLoop(condition)(command)
  	else () //not sure if I need this
  }                                               //> repeatLoop: (condition: => Boolean)(command: => Unit)Unit
  
  //Unsure if this is the question being asked
  def repeatUntil(condition: => Boolean)(command: => Unit){
  	command
  	if (condition) ()
  	else repeatUntil(condition)(command)
  }                                               //> repeatUntil: (condition: => Boolean)(command: => Unit)Unit
}