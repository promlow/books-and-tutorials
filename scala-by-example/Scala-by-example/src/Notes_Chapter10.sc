object Notes_Chapter10 {

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
                                                  //> books  : List[Notes_Chapter10.Book] = List(Book(Structure and Interpretation
                                                  //|  of Computer Programs,List(Abelson, Harold, Sussman, Gerald J.)), Book(Princ
                                                  //| iples of Compiler Design,List(Aho, Alfred, Ullman, Jeffrey)), Book(Programmi
                                                  //| ng in Modula-2,List(Wirth, Niklaus)), Book(Introduction to Functional Progra
                                                  //| mming,List(Bird, Richard)), Book(The Java Language Specification,List(Goslin
                                                  //| g, James, Joy, Bill, Steele, Guy, Bracha, Gilad)))
  
  	for (b <- books; a <- b.authors if a startsWith "Ullman")
  	yield b.title                             //> res0: List[String] = List(Principles of Compiler Design)
  
  	for (b <- books if (b.title indexOf "Program") >= 0)
  	yield b.title                             //> res1: List[String] = List(Structure and Interpretation of Computer Programs,
                                                  //|  Programming in Modula-2, Introduction to Functional Programming)
 		//all authors that have written more than 2 books
 		for(b1 <- books; b2 <- books if b1 != b2;
 				a1 <- b1.authors; a2 <- b2.authors if a1 == a2)
 		yield a1                          //> res2: List[String] = List()
 		
 		object Demo {
 			//map as for-comprehension
 			def map[A, B](xs: List[A], f: A => B): List[B] =
 				for (x <- xs) yield f(x)
 				
 			//flatMap as for-comprehension
 			def flatMap[A, B](xs: List[A], f: A => List[B]): List[B] =
 				for (x <- xs; y <- f(x)) yield y
 				
 			//filter as for-comprehension
 			def filter[A](xs: List[A], p: A => Boolean): List[A] =
 				for (x <- xs if p(x)) yield x
 		}
}