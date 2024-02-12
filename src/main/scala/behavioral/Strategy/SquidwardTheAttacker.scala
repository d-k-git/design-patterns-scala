package behavioral.Strategy

/**
 * Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the
   algorithm vary independently from clients that use it.

 The Strategy pattern is suitable for tasks where:
 - many related classes differ only in their behavior. Strategies provide a way to configure a class
   with one of many behaviors.
 - you need different variants of an algorithm. For example, you might define algorithms reflecting
   different space/time trade-offs. Strategies can be used when these variants are implemented as a
   class hierarchy of algorithms.
 - an algorithm uses data that clients shouldn't know about. Use the Strategy pattern to avoid
   exposing complex, algorithm-specific data structures.
 - a class defines many behaviors, and these appear as multiple conditional statements in its
   operations. Instead of many conditionals, move related conditional branches into their own
   Strategy class. (GoF, Design Patterns)
 */

trait IWeapon {
  def shoot
}

class WaterGun extends IWeapon {
  def shoot: Unit = println("attacks with a water gun")
}

class Plunger extends IWeapon {
  def shoot: Unit = println("attacks with a plunger")
}

class Broom extends IWeapon {
  def shoot: Unit = println("attacks with a broom")
}

class Boomerang extends IWeapon {
  def shoot: Unit = println("attacks with a boomerang")
}

object Hero {
  def apply(name: String): Hero = new Hero(name)
}

class Hero private (private val _name: String) {
  private var _weapon: Option[IWeapon] = None

  def setWeapon(weapon: IWeapon): Unit = {
    _weapon = Some(weapon)
  }

  def attack(): Unit = {
    println(">>>")

    _weapon match {
      case None =>
        println(s"${_name} can't attack. Set a weapon.")
      case Some(_) =>
        println(s"${_name} stands menacingly")
        print(s"${_name} ")
      _weapon.get.shoot
        println(s"${_name} ceases to stand menacingly")
    }
  }
}


object Game {
  def main(args: Array[String]): Unit = {

    val hero: Hero =  Hero("Squidward")

    hero.attack()

    hero.setWeapon(new Broom)
    hero.attack()

    hero.setWeapon(new Plunger)
    hero.attack()

    hero.setWeapon(new Boomerang)
    hero.attack()
  }
}