object Exercise1011 {
//solution from here: https://class.coursera.org/progfun-003/lecture/107
	def isSafe(col: Int, queens: List[Int], delta: Int): Boolean = {
		val row = queens.length
		val queensWithRow = (row - 1 to 0 by -1) zip queens
		queensWithRow forall {
			case (r, c) => col != c && math.abs(col - c) != row - r
		}
	}                                         //> isSafe: (col: Int, queens: List[Int], delta: Int)Boolean

  def queens(n: Int): List[List[Int]] = {
		def placeQueens(k: Int): List[List[Int]] =
			if (k==0) List(List())
			else for { queens <- placeQueens(k - 1)
								 column <- List.range(0, n) // there's a bug here: List.range(1, n + 1)
								 if isSafe(column, queens, 1) } yield column :: queens
			placeQueens(n)
  }                                               //> queens: (n: Int)List[List[Int]]
  
  def show(queens: List[Int]) = {
  	val lines =
  		for (col <- queens.reverse)
  		yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
  		"\n" + (lines mkString "\n")
  }                                               //> show: (queens: List[Int])String

	//queens(8) take 3 map show
  queens(5) map show                              //> res0: List[String] = List("
                                                  //| X * * * * 
                                                  //| * * X * * 
                                                  //| * * * * X 
                                                  //| * X * * * 
                                                  //| * * * X * ", "
                                                  //| X * * * * 
                                                  //| * * * X * 
                                                  //| * X * * * 
                                                  //| * * * * X 
                                                  //| * * X * * ", "
                                                  //| * X * * * 
                                                  //| * * * X * 
                                                  //| X * * * * 
                                                  //| * * X * * 
                                                  //| * * * * X ", "
                                                  //| * X * * * 
                                                  //| * * * * X 
                                                  //| * * X * * 
                                                  //| X * * * * 
                                                  //| * * * X * ", "
                                                  //| * * X * * 
                                                  //| X * * * * 
                                                  //| * * * X * 
                                                  //| * X * * * 
                                                  //| * * * * X ", "
                                                  //| * * X * * 
                                                  //| * * * * X 
                                                  //| * X * * * 
                                                  //| * * * X * 
                                                  //| X * * * * ", "
                                                  //| * * * X * 
                                                  //| X * * * * 
                                                  //| * * X * * 
                                                  //| * * * * X 
                                                  //| * X * * * ", "
                                                  //| * * * X * 
                                                  //| * X * * * 
                                                  //| * * * * X 
                                                  //| * * X * * 
                                                  //| X * * * * ", "
                                                  //| * * * * X 
                                                  //| * X * * * 
                                                  //| * * * X * 
                                                  //| X * * * * 
                                                  //| * * X * * ", "
                                                  //| * * * * X 
                                                  //| * * X * * 
                                                  //| X * * * * 
                                                  //| * * * X * 
                                                  //| * X * * * ")

}