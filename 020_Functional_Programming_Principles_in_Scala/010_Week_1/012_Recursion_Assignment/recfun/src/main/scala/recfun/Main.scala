package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row) {
        print(pascal(col, row) + " ")
      }
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {

    def nextRow(row: Array[Int]): Array[Int] =
      Array(row.head) ++
        row.take(row.length - 1).zip(row.tail).map(p => p._1 + p._2) ++
        Array(row.last)

    def pascal(rowNumber: Int, row: Array[Int]): Array[Int] =
      if (rowNumber == r) row
      else pascal(rowNumber + 1, nextRow(row))

    pascal(0, Array(1))(c)
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    def toValue(c: Char) = c match {
      case '(' => 1
      case ')' => -1
      case _ => 0
    }

    val runningSum = chars.map(toValue).scanLeft(0)(_ + _)

    runningSum.last == 0 && runningSum.forall(_ >= 0)
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    def countSorted(money: Int, coins: List[Int]): Int =
      if (money < 0 || money > 0 && coins.isEmpty) 0
      else if (money == 0) 1
      else countSorted(money - coins.head, coins) + countSorted(money, coins.tail)

    countSorted(money, coins.sorted)
  }
}
