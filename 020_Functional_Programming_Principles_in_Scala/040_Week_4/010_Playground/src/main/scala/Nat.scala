abstract class Nat {
  def isZero: Bool

  def pred: Nat
  def suc: Nat = new Succ(this)
  def +(other: Nat): Nat
  def -(other: Nat): Nat = other.isZero.ifThenElse(this, this.pred - other.pred)
}

object Zero extends Nat {
  val isZero = True
  def pred: Nat = throw new Error("negative nat")
  def +(other: Nat): Nat = other
}

class Succ(val pred: Nat) extends Nat {
  val isZero = False
  def +(other: Nat) = new Succ(other) + pred
}