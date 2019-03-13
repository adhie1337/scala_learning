class Rational(numerator: Int, denominator: Int) {
  val g = gcd(numerator.abs, denominator.abs)
  val n = numerator / g
  val d = denominator / g
  val isFinite = d != 0
  val isWhole = d == 1

  def add(that: Rational): Rational =
    if (!isFinite) this
    else if (!that.isFinite) that 
    else new Rational(
      n * that.d + d * that.n,
      d * that.d)

  override def toString = 
    if (isWhole) n.toString
    else if (isFinite) s"$n/$d" 
    else "Infinity"

  private def gcd(a: Int, b: Int): Int = 
    if (b == 0) a else gcd(b, a % b)
}
