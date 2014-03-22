object Exercise1031 {

	case class Book(title: String, authors: List[String])
	val books: List[Book] = List(
		Book("Structure and Interpretation of Computer Programs",
				List("Abelson, Harold", "Sussman, Gerald J.")),
		Book("Principles of Compiler Design",
				List("Aho, Alfred", "Ullman, Jeffrey")),
		Book("Programming in Modula-2",
				List("Wirth, Niklaus")),
		Book("Introduction to Functional Programming", List("Bird, Richard")),
		Book("The Java Language Specification",
				List("Gosling, James", "Joy, Bill", "Steele, Guy", "Bracha, Gilad")))
                                                  //> books  : List[Exercise1031.Book] = List(Book(Structure and Interpretation of
                                                  //|  Computer Programs,List(Abelson, Harold, Sussman, Gerald J.)), Book(Principl
                                                  //| es of Compiler Design,List(Aho, Alfred, Ullman, Jeffrey)), Book(Programming 
                                                  //| in Modula-2,List(Wirth, Niklaus)), Book(Introduction to Functional Programmi
                                                  //| ng,List(Bird, Richard)), Book(The Java Language Specification,List(Gosling, 
                                                  //| James, Joy, Bill, Steele, Guy, Bracha, Gilad)))
	val words: List[String] = List("the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog")
                                                  //> words  : List[String] = List(the, quick, brown, fox, jumped, over, the, lazy
                                                  //| , dog)
  //val listWords = words map (_.toList)
  val listWords = for (word <- words) yield word.toList
                                                  //> listWords  : List[List[Char]] = List(List(t, h, e), List(q, u, i, c, k), Lis
                                                  //| t(b, r, o, w, n), List(f, o, x), List(j, u, m, p, e, d), List(o, v, e, r), L
                                                  //| ist(t, h, e), List(l, a, z, y), List(d, o, g))
  
  def flatten[A](xss: List[List[A]]): List[A] =
		(xss :\ (Nil: List[A])) ((xs,ys) => xs ::: ys)
                                                  //> flatten: [A](xss: List[List[A]])List[A]
 
 	def _flatten[A](xss: List[List[A]]): List[A] =
 		for (xs <- xss; x <- xs) yield x  //> _flatten: [A](xss: List[List[A]])List[A]
 
 	_flatten (listWords)                      //> res0: List[Char] = List(t, h, e, q, u, i, c, k, b, r, o, w, n, f, o, x, j, u
                                                  //| , m, p, e, d, o, v, e, r, t, h, e, l, a, z, y, d, o, g)
 ///////////////////////
 //10.3.2
 ///////////////////////
 for (b <- books; a <- b.authors if a startsWith "Bird") yield b.title
                                                  //> res1: List[String] = List(Introduction to Functional Programming)
 
 def birdTitle(xs: List[Book]): List[String] = {
 	def birdAuthor(ys: List[String]): Boolean =
 		if (ys.isEmpty) false
 		else if ((ys filter (y => y startsWith "Bird")).isEmpty) false
 		else true
 		
 	if (xs.isEmpty) Nil
 	else if (birdAuthor(xs.head.authors)) xs.head.title :: birdTitle(xs.tail)
 	else birdTitle(xs.tail)
 }                                                //> birdTitle: (xs: List[Exercise1031.Book])List[String]
 birdTitle(books)                                 //> res2: List[String] = List(Introduction to Functional Programming)
 	
 for (b <- books; if (b.title indexOf "Program") >= 0) yield b.title
                                                  //> res3: List[String] = List(Structure and Interpretation of Computer Programs
                                                  //| , Programming in Modula-2, Introduction to Functional Programming)
 
 def programTitle(xs: List[Book]): List[String] =
 	if (xs.isEmpty) Nil
 	else (xs filter (x => x.title contains "Program")) map (_.title)
                                                  //> programTitle: (xs: List[Exercise1031.Book])List[String]
 
 	
 	//books filter (b => b.title contains "Program")
  programTitle(books)                             //> res4: List[String] = List(Structure and Interpretation of Computer Programs
                                                  //| , Programming in Modula-2, Introduction to Functional Programming)
 	
}