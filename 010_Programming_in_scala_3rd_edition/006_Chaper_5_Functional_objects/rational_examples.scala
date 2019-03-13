val half = new Rational(1, 2)
val quarter = new Rational(2, 8)
val threeQuarters = half add quarter
val one = quarter + threeQuarters
val two = new Rational(2)
val twoAndAHalf = two + half
val infinity = new Rational(1, 0)

println(s"infinity == infinity + 1? It is ${infinity == infinity + one}.")

val threeByEight = threeQuarters multiply half 

println(s"(3/8) * 2 == 3/4? It is ${ threeByEight * two == threeQuarters }.")
