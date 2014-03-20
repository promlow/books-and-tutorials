object Notes_Chapter9 {

	def msort[A](less: (A, A) => Boolean)(xs: List[A]): List[A] = {
		def merge(xs1: List[A], xs2: List[A]): List[A] =
			if (xs1.isEmpty) xs2
			else if (xs2.isEmpty) xs1
			else if (less(xs1.head, xs2.head)) xs1.head :: merge(xs1.tail, xs2)
			else xs2.head :: merge(xs1, xs2.tail)
		val n = xs.length / 2
		if (n == 0) xs
		else merge(msort(less)(xs take n), msort(less)(xs drop n))
	}                                         //> msort: [A](less: (A, A) => Boolean)(xs: List[A])List[A]

	val unsorted = List(8, 4, 5, 3, 7, 9, 0, 1, 2, 6)
                                                  //> unsorted  : List[Int] = List(8, 4, 5, 3, 7, 9, 0, 1, 2, 6)
	
	msort((x: Int, y: Int) => x < y) (unsorted)
                                                  //> res0: List[Int] = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

	val intSort = msort((x: Int, y: Int) => x < y) _
                                                  //> intSort  : List[Int] => List[Int] = <function1>
	val reverseSort = msort((x: Int, y: Int) => x > y) _
                                                  //> reverseSort  : List[Int] => List[Int] = <function1>
	
	intSort(unsorted)                         //> res1: List[Int] = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
	
	reverseSort(unsorted)                     //> res2: List[Int] = List(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)

	def sum(xs: List[Int]) = (0 :: xs) reduceLeft {(x,y) => x + y}
                                                  //> sum: (xs: List[Int])Int
	def product(xs: List[Int]) = (1 :: xs) reduceLeft {(x,y) => x * y}
                                                  //> product: (xs: List[Int])Int

	sum(unsorted)                             //> res3: Int = 45
	product(unsorted)                         //> res4: Int = 0

	def sum_fold(xs: List[Int]) = (xs foldLeft 0) { (x, y) => x + y }
                                                  //> sum_fold: (xs: List[Int])Int
	def product_fold(xs: List[Int]) = (xs foldLeft 1) { (x, y) => x * y }
                                                  //> product_fold: (xs: List[Int])Int
  sum_fold(unsorted)                              //> res5: Int = 45
  product_fold(unsorted)                          //> res6: Int = 0

  def main(args: Array[String]) {
	}                                         //> main: (args: Array[String])Unit
}