object Exercise941 {

	def square_List(xs: List[Int]): List[Int] = xs match {
		case List() => xs
		case y :: ys => y * y :: square_List(ys)
	}

	def squareList(xs: List[Int]): List[Int] =
		xs map (x => x * x)
		
	val l = List(0, 1,2,3,4,5,6,7,8,9)
	
	square_List(l)
	
	squareList(l)
	
	def posElems(xs: List[Int]): List[Int] =
		xs filter (x => x > 0)
		
	posElems(l)

	def isPrime(n: Int) =
		List.range(2, n) forall (x => n % x != 0)
		
  isPrime(7)
	
	def main(args: Array[String]) {
	}

}