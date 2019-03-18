
abstract class IntSet {
  def contains(x: Int) : Boolean
  def incl(x: Int): IntSet
  def union(other: IntSet): IntSet
}

class NonEmpty(left: IntSet, value: Int, right: IntSet) extends IntSet {

  override def contains(x: Int): Boolean =
    if (x < value) left.contains(x)
    else if (x > value) right.contains(x)
    else true

  override def incl(x: Int): IntSet =
    if (x < value) new NonEmpty(left.incl(x), value, right)
    else if (x > value) new NonEmpty(left, value, right.incl(x))
    else this

  override def toString: String = s"{$left $value $right}"

  override def union(other: IntSet): IntSet = other union left union right incl value
}

class Empty extends IntSet {

  override def contains(x: Int): Boolean = false

  override def incl(x: Int): IntSet = new NonEmpty(new Empty, x, new Empty)

  override def toString: String = ""

  override def union(other: IntSet): IntSet = other
}
