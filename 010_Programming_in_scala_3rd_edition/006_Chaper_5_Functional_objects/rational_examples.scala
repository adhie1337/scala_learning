def isIt(condition: Boolean) = 
  s"It is${ if (condition) "" else " not"}."

val half = new Rational(1, 2)
val quarter = new Rational(2, 8)
val threeQuarters = half add quarter
val one = quarter + threeQuarters
val two = new Rational(2)
val twoAndAHalf = two + half
val infinity = new Rational(1, 0)
var subresult = infinity + one

println(raw"""-Is infinity equal to infinity + 1?
             |-${ isIt(infinity == subresult) }""")

val threeByEight = threeQuarters multiply half 
subresult = threeByEight * two

println(raw"""-Is 3/8 * 2 equal to 3/4?
             |-${ isIt(subresult == threeQuarters) }""")

val minusOneThird = new Rational(-1, 3)
val minusTwoNinth = new Rational(2, -9)
val minusOneAndAHalf = new Rational(-3, 2)
val third = new Rational(1, 3)
subresult = minusOneAndAHalf * minusTwoNinth

println(raw"""-Is -3/2 * -2/9 equal to 1/3?
             |-${ isIt(subresult == third) }""")

subresult = new Rational(5, 3) - new Rational(4, 3)

println(raw"""-Is 5/3 - 4/3 equal to 1/3?
             |-${ isIt(subresult == third) }""")


