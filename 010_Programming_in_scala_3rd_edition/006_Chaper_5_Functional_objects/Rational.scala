class Rational(numerator: Int, denominator: Int) {
  val n = numerator 
  val d = denominator
  val isFinite = d != 0

  override def toString =  if (isFinite) s"$n/$d" else "Infinity"
}
