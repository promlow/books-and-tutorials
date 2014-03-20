object Chapter7 {

  //abstract class Expr
  trait Expr {
  	def eval: Int = this match {
  		case Number(n) => n
  		case Sum(e1, e2) => e1.eval + e2.eval
  	}
  }
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr

	/*
  def eval(e: Expr): Int = e match {
    case Number(n) => n
    case Sum(l, r) => eval(l) + eval(r)
  }
	*/
	
	//eval(Sum(Number(1), Number(2)))
	Sum(Number(1), Number(2)).eval            //> res0: Int = 3
	
  def main(args: Array[String]) {
  
  }                                               //> main: (args: Array[String])Unit
}