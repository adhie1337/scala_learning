package calculator

sealed abstract class Expr
final case class Literal(v: Double) extends Expr
final case class Ref(name: String) extends Expr
final case class Plus(a: Expr, b: Expr) extends Expr
final case class Minus(a: Expr, b: Expr) extends Expr
final case class Times(a: Expr, b: Expr) extends Expr
final case class Divide(a: Expr, b: Expr) extends Expr

object Calculator {
  def computeValues(
      namedExpressions: Map[String, Signal[Expr]]): Map[String, Signal[Double]] =
    namedExpressions mapValues (s => Signal(eval(s(), namedExpressions)))

  def eval(expr: Expr, references: Map[String, Signal[Expr]]): Double = {
    def innerEval(expr: Expr, names: List[String]): Double = expr match {
      case Literal(value) => value
      case Plus(a, b) => innerEval(a, names) + innerEval(b, names)
      case Minus(a, b) => innerEval(a, names) - innerEval(b, names)
      case Times(a, b) => innerEval(a, names) * innerEval(b, names)
      case Divide(a, b) => innerEval(a, names) / innerEval(b, names)
      case Ref(name) =>
        if(names.contains(name)) Double.NaN
        else innerEval(getReferenceExpr(name, references), name :: names)
    }

    innerEval(expr, List())
  }

  /** Get the Expr for a referenced variables.
   *  If the variable is not known, returns a literal NaN.
   */
  private def getReferenceExpr(name: String, references: Map[String, Signal[Expr]]) = {
    references.get(name).fold[Expr] {
      Literal(Double.NaN)
    } { exprSignal =>
      exprSignal()
    }
  }
}
