import cats._
import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._

import cats.Eq
import cats.syntax.eq._ // for ===
import cats.instances.option._ // for Eq

object Main {
  final case class Cat(name: String, age: Int, color: String)

  trait Printable[A] {
    def format(value: A): String
  }

  object PrintableInstances {
    implicit val stringPrintable: Printable[String] = (input: String) => input
    implicit val intPrintable: Printable[Int] = (input: Int) => input.toString
  }

  object Printable {
    def format[A](input: A)(implicit p: Printable[A]): String =
      p.format(input)
    def print[A](input: A)(implicit p: Printable[A]): Unit = println(format(input))
  }

  import PrintableInstances._
  implicit val catPrintable = new Printable[Cat] {
    def format(cat: Cat) = {
      val name  = Printable.format(cat.name)
      val age   = Printable.format(cat.age)
      val color = Printable.format(cat.color)
      s"$name is a $age year-old $color cat."
    } }

  object PrintableSyntax {
    implicit class PrintableOps[A](value: A) {
      def format(implicit p: Printable[A]): String =
        p.format(value)
      def print(implicit p: Printable[A]): Unit =
        println(p.format(value))
    }
  }

  val cat = Cat("Garfield", 38, "ginger and black")
  // cat: Cat = Cat(Garfield,38,ginger and black)
  Printable.print(cat)
  // Garfield is a 38 year-old ginger and black cat.

  {
    import PrintableSyntax._
    Cat("Garfield", 38, "ginger and black").print
    // Garfield is a 38 year-old ginger and black cat.
  }

  import java.util.Date
  implicit val dateShow: Show[Date] = Show.show(date => s"${date.getTime}ms since the epoch.")
  new Date().show

  implicit val catShow: Show[Cat] = Show.show[Cat] { cat =>
    val name  = cat.name.show
    val age   = cat.age.show
    val color = cat.color.show
    s"$name is a $age year-old $color cat."
  }

  println(Cat("Garfield", 38, "ginger and black").show)
  // Garfield is a 38 year-old ginger and black cat.

  implicit val catEqual: Eq[Cat] =
    Eq.instance[Cat] { (cat1, cat2) =>
      (cat1.name  === cat2.name ) &&
      (cat1.age   === cat2.age  ) &&
      (cat1.color === cat2.color)
  }

  val cat1 = Cat("Garfield",   38, "orange and black")
  // cat1: Cat = Cat(Garfield,38,orange and black)
  val cat2 = Cat("Heathcliff", 32, "orange and black")
  // cat2: Cat = Cat(Heathcliff,32,orange and black)
  cat1 === cat2
  // res17: Boolean = false
  cat1 =!= cat2
  // res18: Boolean = true

  val optionCat1 = Option(cat1)
  // optionCat1: Option[Cat] = Some(Cat(Garfield,38,orange and black))
  val optionCat2 = Option.empty[Cat]
  // optionCat2: Option[Cat] = None
  optionCat1 === optionCat2
  // res19: Boolean = false
  optionCat1 =!= optionCat2
  // res20: Boolean = true
}
