trait Printable[A] {
  def format(value: A): String
}

object PrintableInstances {
  implicit val stringPrintable: Printable[String] = (value: String) => value

  implicit val intPrintable: Printable[Int] = (value: Int) => value.toString
}

object Printable {
  def format[A](value: A)(implicit p: Printable[A]): String = p format value

  def print[A](value: A)(implicit p: Printable[A]): Unit = println(format(value))
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit printable: Printable[A]): String = Printable format value

    def print(implicit printable: Printable[A]): Unit = Printable print value
  }
}