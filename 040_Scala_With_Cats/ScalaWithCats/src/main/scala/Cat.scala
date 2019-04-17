final case class Cat(name: String, age: Int, color: String)

object Cat {
  implicit val catPrintable: Printable[Cat] = cat => {
    import PrintableInstances._

    val name = Printable format cat.name
    val age = Printable format cat.age
    val color = Printable format cat.color

    s"$name is a $age year-old, $color cat"
  }
}