abstract class List[+T] {
  def isEmpty: Bool
  def head: T
  def tail: List[T]
  def prepend[U >: T](elem: U): List[U] = new Cons(elem, this)
}

object Nil extends List[Nothing] {
  val isEmpty = True
  def head = throw new Error("empty list")
  def tail = throw new Error("empty list")
}

class Cons[+T](val head: T, val tail: List[T]) extends List[T]{
  val isEmpty = False
}