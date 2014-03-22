object Notes_Chapter12 {

	def isPrime(n: Int) =
		List.range(2, n) forall (x => n % x != 0)
                                                  //> isPrime: (n: Int)Boolean
	
	def sum(nums: List[Int]): Int = (nums.head /: nums.tail)(_ + _)
                                                  //> sum: (nums: List[Int])Int
	
	def ssum(nums: Stream[Int]): Int = (nums.head /: nums.tail)(_ + _)
                                                  //> ssum: (nums: Stream[Int])Int
	
	def _range(start: Int, end: Int): List[Int] =
		List.range(start, end)            //> _range: (start: Int, end: Int)List[Int]
		
	def range(start:Int, end: Int): Stream[Int] =
		if (start >= end) Stream.empty
		else Stream.cons(start, range(start + 1, end))
                                                  //> range: (start: Int, end: Int)Stream[Int]

	def sumPrimes(start: Int, end: Int): Int = {
		var i = start
		var acc = 0
		while (i < end) {
			if (isPrime(i)) acc += i
			i += 1
		}
		acc
	}                                         //> sumPrimes: (start: Int, end: Int)Int

	def _sumPrimes(start: Int, end: Int) =
		ssum(range(start, end) filter isPrime)
                                                  //> _sumPrimes: (start: Int, end: Int)Int
	sumPrimes(100, 1000)                      //> res0: Int = 75067
	_sumPrimes(100, 1000)                     //> res1: Int = 75067
}