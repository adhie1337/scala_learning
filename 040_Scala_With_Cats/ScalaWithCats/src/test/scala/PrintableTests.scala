class PrintableTests extends org.scalatest.FunSuite {

  test("Printing cats") {
    val cat = Cat("Cirmi", 4, "brown")

    assert(Printable.format(cat) == "Cirmi is a 4 year-old, brown cat")
  }

}
