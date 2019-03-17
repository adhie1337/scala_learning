
def factorial(i: Double): Double = {
  def factorial(i: Double, accum: Double): Double = 
    if (i == 1) accum
    else factorial(i - 1, i * accum)

  factorial(i, 1)
}

