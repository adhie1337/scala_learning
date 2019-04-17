class PrintableTests extends org.scalatest.FunSuite {

  test("Printing cats") {
    val cat = Cat("Cirmi", 4, "brown")

    assert(Printable.format(cat) == "Cirmi is a 4 year-old, brown cat")
  }

  test("Printing cats with syntax") {
    import PrintableSyntax._

    val cat = Cat("Cirmi", 4, "brown")

    assert(cat.format == "Cirmi is a 4 year-old, brown cat")
  }

}
