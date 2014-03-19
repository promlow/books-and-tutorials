
object Exercise6dot0dot1 {
  /* This is implemented as a sorted binary tree.
   * Explained here: https://class.coursera.org/progfun-003/lecture/51
   */

  abstract class IntSet {
    def excl(x: Int): IntSet
    def incl(x: Int): IntSet
    def contains(x: Int): Boolean
    def union(other: IntSet): IntSet
    def isEmpty: Boolean
  }

  class EmptySet extends IntSet {
    def contains(x: Int): Boolean = false
    def excl(x: Int): IntSet = this
    def incl(x: Int): IntSet = new NonEmptySet(x, new EmptySet, new EmptySet)
    def union(other: IntSet): IntSet = other
    def isEmpty: Boolean = true
    override def toString = "."
  }

  class NonEmptySet(elem: Int, left: IntSet, right: IntSet) extends IntSet {
    def contains(x: Int): Boolean =
      if (x < elem) left contains x
      else if (x > elem) right contains x
      else true
    def incl(x: Int): IntSet =
      if (x < elem) new NonEmptySet(elem, left incl x, right)
      else if (x > elem) new NonEmptySet(elem, left, right incl x)
      else this       
    def union(other: IntSet): IntSet =
      ((left union right) union other) incl elem
    override def toString = "{"+ left + elem + right +"}"
  }

  def main(args: Array[String]) {
    val t1 = new NonEmptySet(3, new EmptySet, new EmptySet)
    val t2 = t1 incl 4
    val t3 = t2 incl 7
    val t4 = t3 incl 7
    println(t1)
    println(t2)
    println(t3 union t4)
  }
}
