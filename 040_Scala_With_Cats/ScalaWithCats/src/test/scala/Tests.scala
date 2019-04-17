class Tests extends org.scalatest.FunSuite {

  test("Printing cats") {
    val cat = Cat("Cirmi", 4, "brown")

    assert(Printable.format(cat) == "Cirmi is a 4 year-old, brown cat")
  }

  test("Printing cats with syntax") {
    import PrintableSyntax._

    val cat = Cat("Cirmi", 4, "brown")

    assert(cat.format == "Cirmi is a 4 year-old, brown cat")
  }

  test("Printing cats with cats show syntax") {
    import cats.syntax.show._

    val cat = Cat("Cirmi", 4, "brown")

    assert(cat.show == "Cirmi is a 4 year-old, brown cat")
  }

  test("equality of equal cats") {
    import cats.syntax.eq._

    val cat = Cat("Cirmi", 4, "brown")

    assert(cat eqv Cat("Cirmi", 4, "brown"))
  }

  test("equality of non equal cats") {
    import cats.syntax.eq._

    val cat = Cat("Cirmi", 4, "brown")

    assert(cat =!= Cat("Cirmi", 5, "brown"))
  }

}
