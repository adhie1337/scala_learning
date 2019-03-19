
abstract class Bool {
  def ifThenElse[T](t: => T, e: => T): T

  def &&(other: Bool) = ifThenElse(other, False)
  def ||(other: Bool) = ifThenElse(True, other)
  def unary_! : Bool = ifThenElse(False, True)
  def ==(other: Bool) = ifThenElse(other, !other)
  def !=(other: Bool) = ifThenElse(!other, other)
}

object True extends Bool {
  override def ifThenElse[T](t: => T, e: => T): T = t
}

object False extends Bool {
  override def ifThenElse[T](t: => T, e: => T): T = e
}