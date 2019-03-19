abstract class Expr {
  def eval: Int
  def show: String = this.toString

  override def toString = this match {
    case Number(n) => n.toString
    case Sum(left, right) => s"$left + $right"
    case Prod(left, right) => s"$left * $right"
  }
}

case class Number(n: Int) extends Expr {
  val eval = n
}

case class Sum(left: Expr, right: Expr) extends Expr {
  val eval = left.eval + right.eval
}

case class Prod(left: Expr, right: Expr) extends Expr {
  val eval = left.eval * right.eval

  override def toString = {
    val leftString = left match { case Sum(_, _) => s"($left)"; case rest => rest.toString }
    val rightString = right match { case Sum(_, _) => s"($left)"; case rest => rest.toString }

    s"$leftString * $rightString"
  }
}

// in a separate assembly:
case class Variable(name: String) extends Expr {
  def eval = throw new Error()

  override def toString: String = name
}