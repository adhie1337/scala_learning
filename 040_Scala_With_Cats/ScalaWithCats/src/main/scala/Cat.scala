import cats._
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._

final case class Cat(name: String, age: Int, color: String)

object Cat {
  implicit val printable: Printable[Cat] = cat => {
    import PrintableInstances._

    val name = Printable format cat.name
    val age = Printable format cat.age
    val color = Printable format cat.color

    s"$name is a $age year-old, $color cat"
  }

  implicit val show: Show[Cat] = cat => {
    val name = cat.name.show
    val age = cat.age.show
    val color = cat.color.show

    s"$name is a $age year-old, $color cat"
  }
}