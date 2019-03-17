
def sqrt(x: Double): Double = {

  def abs(x: Double) = 
    if (x < 0) -x else x

  def isGoodEnough(guess: Double) = 
    abs(guess * guess - x) / x < 0.00001

  def improve(guess: Double) = (guess + x / guess) / 2

  def sqrtIter(guess: Double): Double = 
    if (isGoodEnough(guess)) guess
    else sqrtIter((guess + x / guess) / 2)

  sqrtIter(1)
}


