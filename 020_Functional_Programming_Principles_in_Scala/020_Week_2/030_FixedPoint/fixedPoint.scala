
def fixedPoint(isGoodEnough: Double => Boolean, improve: Double => Double, start: Double)(x: Double): Double = {

  def loop(guess: Double): Double = 
    if (isGoodEnough(guess)) guess
    else loop(improve(guess))

  loop(start)
}

def sqrt(x: Double): Double = {

  def abs(x: Double) =
    if (x < 0) -x
    else x

  def isGoodEnough(guess: Double) =
    abs(guess * guess - x) / x < 0.00001

  def improve(guess: Double) = (guess + x / guess) / 2

  fixedPoint(isGoodEnough, improve, 1)(x)
}
