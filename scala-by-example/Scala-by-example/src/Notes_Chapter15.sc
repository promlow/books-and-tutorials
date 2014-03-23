import scala.language.implicitConversions

object Notes_Chapter15 {
  
  abstract class SemiGroup[A] {
  	def add(x: A, y: A): A
  }
  
  abstract class Monoid[A] extends SemiGroup[A] {
  	def unit: A
  }
  
  //object stringMonoid extends Monoid[String] {
  implicit object stringMonoid extends Monoid[String] {
  	def add(x: String, y: String): String = x.concat(y)
  	def unit: String = ""
  }
  
  //object intMonoid extends Monoid[Int] {
  implicit object intMonoid extends Monoid[Int] {
  	def add(x: Int, y: Int): Int = x + y
  	def unit: Int = 0
  }
  
  //def sum[A](xs: List[A])(m: Monoid[A]): A =
  def sum[A](xs: List[A])(implicit m: Monoid[A]): A =
  	if (xs.isEmpty) m.unit
  	else m.add(xs.head, sum(xs.tail)(m))      //> sum: [A](xs: List[A])(implicit m: Notes_Chapter15.Monoid[A])A
 
 	sum(List("a", "bc", "def"))(stringMonoid) //> res0: String = abcdef
 	sum(List(1,2,3))(intMonoid)               //> res1: Int = 6
 	sum(List(4,5,6))                          //> res2: Int = 15
 	sum(List("g", "hi", "jkl"))               //> res3: String = ghijkl
  
  
  implicit def int2ordered(x: Int): Ordered[Int] = new Ordered[Int] {
  	def compare(y: Int): Int =
  		if (x < y) -1
  		else if (x > y) 1
  		else 0
  }                                               //> int2ordered: (x: Int)Ordered[Int]
  
  def merge[A <% Ordered[A]](xs: List[A], ys: List[A]): List[A] =
  	if (xs.isEmpty) ys
  	else if (ys.isEmpty) xs
  	else if (xs.head < ys.head) xs.head :: merge(xs.tail, ys)
  	else ys.head :: merge(xs, ys.tail)        //> merge: [A](xs: List[A], ys: List[A])(implicit evidence$1: A => Ordered[A])L
                                                  //| ist[A]
  
  
  def sort[A <% Ordered[A]](xs: List[A]): List[A] =
  	if (xs.isEmpty || xs.tail.isEmpty) xs
  	else {
  		val (ys, zs) = xs.splitAt(xs.length / 2)
  		merge(ys, zs)
  	}                                         //> sort: [A](xs: List[A])(implicit evidence$2: A => Ordered[A])List[A]
  
  def main(args: Array[String]) {}                //> main: (args: Array[String])Unit
}