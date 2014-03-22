object Exercise1011 {

	def isSafe(col: Int, queens: List[Int], delta: Int): Boolean = {
		false
	}                                         //> isSafe: (col: Int, queens: List[Int], delta: Int)Boolean

  def queens(n: Int): List[List[Int]] = {
		def placeQueens(k: Int): List[List[Int]] =
			if (k==0) List(List())
			else for { queens <- placeQueens(k - 1)
								 column <- List.range(1, n + 1)
								 if isSafe(column, queens, 1) } yield column :: queens
			placeQueens(n)
  }                                               //> queens: (n: Int)List[List[Int]]

}