import scala.annotation.tailrec

object Exercise921 {
  
  def listLength[A](l: List[A]): Int = {
  	@tailrec
  	def tailLength(size: Int, xs: List[A]): Int = xs match {
  		case Nil => size
  		case x ::xs => tailLength(1 + size, xs)
  	}
  	tailLength(0, l)
  }                                               //> listLength: [A](l: List[A])Int
    
  val list = List(1, 2, 3, 4, 5, 6)               //> list  : List[Int] = List(1, 2, 3, 4, 5, 6)
  listLength(list)                                //> res0: Int = 6
    
  def main(args: Array[String]) {
  	val list = List(1, 2, 3, 4, 5, 6)
  	//println(listLength(list))
  	listLength(list)
	}                                         //> main: (args: Array[String])Unit
}