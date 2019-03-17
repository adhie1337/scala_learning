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
    def balance(chars: List[Char]): Boolean = ???
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = ???
  }
