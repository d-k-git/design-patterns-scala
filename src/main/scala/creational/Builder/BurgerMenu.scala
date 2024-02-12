package creational.Builder

/**
 * The pattern is needed to separate the construction (the builder interface and concrete builders) of a complex object (class Product)
 * from its representation (the product class fields) so that the same construction process can create different representations.
 * The builder interface defines the steps for creating a product and the method that allows to get the result
 *
 * The Builder pattern is suitable for tasks where:
   - the algorithm for creating a complex object should be independent of the parts that make up the object and
     how they're assembled.
   - the construction process must allow different representations for the object that's constructed. (GoF, Design Patterns)
 */


// Product
case class Burger (var bun: String,
                   var patty: String,
                   var cheese: String,
                   var toppings: List[String],
                   var extra: String)

// Builder Interface
trait IBurgerBuilder {
  def buildBun(): Unit
  def buildPatty(): Unit
  def buildCheese(): Unit
  def buildToppings(): Unit
  def buildExtra(): Unit
  def getBurger(): Burger
}

// Concrete Builder for VeggieBurger
class VeggieBurgerBuilder extends IBurgerBuilder {
  private val burger = new Burger("", "", "", List(),"")

  override def buildBun(): Unit = burger.bun = "Whole Wheat Bun"

  override def buildPatty(): Unit = burger.patty = "Veggie Patty"

  override def buildCheese(): Unit = burger.cheese = "Swiss Cheese"

  override def buildToppings(): Unit = burger.toppings = List("Lettuce", "Tomato", "Onion")

  override def buildExtra(): Unit = burger.extra = "Olives"

  override def getBurger(): Burger = burger
}

// Concrete Builder for ChickenBurger
class ChickenBurgerBuilder extends IBurgerBuilder {
  private val burger = new Burger("", "", "",  List(),"")

  override def buildBun(): Unit = burger.bun = "Whole Wheat Bun"

  override def buildPatty(): Unit = burger.patty = "Chicken Patty"

  override def buildCheese(): Unit = burger.cheese = "Swiss Cheese"

  override def buildToppings(): Unit = burger.toppings = List("Lettuce", "Tomato", "Onion")

  override def buildExtra(): Unit = burger.extra = "Bacon"

  override def getBurger(): Burger = burger
}

// Director
class BurgerDirector(builder: IBurgerBuilder) {
  def construct(): Burger = {
    builder.buildBun()
    builder.buildPatty()
    builder.buildCheese()
    builder.buildToppings()
    builder.buildExtra()
    builder.getBurger()
  }
}


object BuilderPatternExample {
  def main(args: Array[String]): Unit = {

    val veggieBurgerBuilder = new VeggieBurgerBuilder()
    val directorVeggie = new BurgerDirector(veggieBurgerBuilder)

    val chickenBurgerBuilder = new ChickenBurgerBuilder
    val directorChicken = new BurgerDirector(chickenBurgerBuilder)

    val veggieBurger = directorVeggie.construct()
    println(">>> Veggie Burger Details: <<<")
    println(s"Bun: ${veggieBurger.bun}")
    println(s"Patty: ${veggieBurger.patty}")
    println(s"Cheese: ${veggieBurger.cheese}")
    println(s"Toppings: ${veggieBurger.toppings.mkString(", ")}")
    //println(s"Bacon: ${veggieBurger.extra}")

    println("=================================")

    val chickenBurger = directorChicken.construct()
    println(">>> Chicken Burger Details: <<<")
    println(s"Bun: ${chickenBurger.bun}")
    println(s"Patty: ${chickenBurger.patty}")
    println(s"Cheese: ${chickenBurger.cheese}")
    println(s"Toppings: ${chickenBurger.toppings.mkString(", ")}")
    println(s"Extra: ${chickenBurger.extra}")
  }
}