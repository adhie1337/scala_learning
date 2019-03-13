class Rational(numerator: Int, denominator: Int) {
  val g = gcd(numerator.abs, denominator.abs)
  val isNegative = numerator * denominator < 0
  val n = (if (isNegative) -1 else 1) * numerator.abs / g
  val d = denominator.abs / g
  val isFinite = d != 0
  val isWhole = d == 1

  def this(numerator: Int) = this(numerator, 1)

  def add(that: Rational) =
    if (!isFinite) this
    else if (!that.isFinite) that 
    else new Rational(
      n * that.d + d * that.n,
      d * that.d)

  def + = add(_)

  def multiply(that: Rational) =
    if (!isFinite) this
    else if (!that.isFinite) that
    else new Rational(n * that.n, d * that.d)

  def * = multiply(_)

  override def equals(that: Any) =
    that match {
      case that:Rational => 
        !that.isFinite && !this.isFinite ||
        that.n == this.n && that.d == this.d
      case _ => false
    }

  override def toString = 
    if (isWhole) n.toString
    else if (!isFinite) "Infinity"
    else if (n.abs > d.abs) s"${n / d} ${ (n % d).abs }/${ d }"
    else s"$n/$d" 

  private def gcd(a: Int, b: Int): Int = 
    if (b == 0) a else gcd(b, a % b)
}
