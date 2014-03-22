object folding {
  val words: List[String] = List("the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog")
                                                  //> words  : List[String] = List(the, quick, brown, fox, jumped, over, the, lazy
                                                  //| , dog)
  
  (" " /: words) (_ +" "+ _)                      //> res0: String = "  the quick brown fox jumped over the lazy dog"
  (words.head /: words.tail) (_ +" "+ _)          //> res1: String = the quick brown fox jumped over the lazy dog
  
  (words :\ " ") (_ +" "+ _)                      //> res2: String = "the quick brown fox jumped over the lazy dog  "
  (words.tail :\ words.head) (_ +" "+ _)          //> res3: String = quick brown fox jumped over the lazy dog the
  (words.init :\ words.last) (_ +" "+ _)          //> res4: String = the quick brown fox jumped over the lazy dog
  
  
	(words.init :\ words.last) (_ +","+ _)    //> res5: String = the,quick,brown,fox,jumped,over,the,lazy,dog
	(words.head /: words.tail) (_ +","+ _)    //> res6: String = the,quick,brown,fox,jumped,over,the,lazy,dog
	
	val listWords = words map (_.toList)      //> listWords  : List[List[Char]] = List(List(t, h, e), List(q, u, i, c, k), Lis
                                                  //| t(b, r, o, w, n), List(f, o, x), List(j, u, m, p, e, d), List(o, v, e, r), L
                                                  //| ist(t, h, e), List(l, a, z, y), List(d, o, g))
	words flatMap(_.toList)                   //> res7: List[Char] = List(t, h, e, q, u, i, c, k, b, r, o, w, n, f, o, x, j, u
                                                  //| , m, p, e, d, o, v, e, r, t, h, e, l, a, z, y, d, o, g)
                                                  
  val nums: List[Int] = List(1,2,3,4,5,6,7,8,9)   //> nums  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  (0 /: nums) (_ + _)                             //> res8: Int = 45
  (nums :\ 0) (_ + _)                             //> res9: Int = 45
  (nums.init :\ nums.last) (_ * - _)              //> res10: Int = 362880
  (nums.head /: nums.tail) (- _ * _)              //> res11: Int = 362880
  
  def flattenLeft[T](xss: List[List[T]]) =
  	(List[T]() /: xss) (_ ::: _)              //> flattenLeft: [T](xss: List[List[T]])List[T]
 
 	def flattenRight[T](xss: List[List[T]]) =
 		(xss :\ List[T]()) (_ ::: _)      //> flattenRight: [T](xss: List[List[T]])List[T]
  	
  flattenLeft (listWords)                         //> res12: List[Char] = List(t, h, e, q, u, i, c, k, b, r, o, w, n, f, o, x, j, 
                                                  //| u, m, p, e, d, o, v, e, r, t, h, e, l, a, z, y, d, o, g)
 	flattenRight (listWords)                  //> res13: List[Char] = List(t, h, e, q, u, i, c, k, b, r, o, w, n, f, o, x, j, 
                                                  //| u, m, p, e, d, o, v, e, r, t, h, e, l, a, z, y, d, o, g)
  
}