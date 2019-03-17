
def factorial(i: Int): Int = {
  def factorial(i: Int, accum: Int): Int = 
    if (i == 1) accum
    else factorial(i - 1, i * accum)

  factorial(i, 1)
}

