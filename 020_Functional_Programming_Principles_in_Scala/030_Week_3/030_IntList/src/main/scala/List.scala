package lists

trait List[+T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

object Nil extends List[Nothing] {
  val isEmpty = true
  def head = throw new NoSuchElementException("head of Nil")
  def tail = throw new NoSuchElementException("tail of Nil")

  override def toString: String = "Nil"
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  val isEmpty = false

  override def toString: String = s"$head :: $tail"
}