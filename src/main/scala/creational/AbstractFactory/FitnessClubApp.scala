package creational.AbstractFactory

/**
 * This pattern provides an interface (AbstractFactory) for creating families (concrete factories) of related
 * or dependent objects without specifying their concrete product classes for clients.
 * Clients can use these factories to create memberships without knowing the details encapsulated
 * within each product class (here: description, price, and bonus details).
 *
 * The AF pattern is suitable for tasks where:
   - a system should be independent of how its products are created, composed, and represented.
   - a system should be configured with one of multiple families of products.
   - a family of related product objects is designed to be used together, and you need to enforce this constraint.
   - you want to provide a class library of products, and you want to reveal just their interfaces, not their implementations. (GoF, Design Patterns)
 */


// Product: Membership
trait IMembership {
  def getDescription: String
  def getPrice: String
  def getBonus: String
}

// Concrete Products
class BasicMembership extends IMembership {
  def getDescription: String = "Basic Membership - Access to basic facilities"
  def getPrice: String = "$100"
  def getBonus: String = ""
}

class PremiumMembership extends IMembership {
  def getDescription: String = "Premium Membership - Access to all facilities, including classes"
  def getPrice: String = "$200"
  def getBonus: String = ""
}

class VIPMembership extends IMembership {
  def getDescription: String = "VIP Membership - Exclusive access to all facilities, personalized training"
  def getPrice: String = "$300"
  def getBonus: String = "Free craft towels"
}

// Membership AbstractFactory
trait IMembershipFactory {
  def createMembership: IMembership
}

// Concrete Factories
class BasicMembershipFactory extends IMembershipFactory {
  def createMembership: IMembership = new BasicMembership
}

class PremiumMembershipFactory extends IMembershipFactory {
  def createMembership: IMembership = new PremiumMembership
}

class VIPMembershipFactory extends IMembershipFactory {
  def createMembership: IMembership = new VIPMembership
}

// Client
object FitnessClubApp {
  def main(args: Array[String]): Unit = {
    val basicFactory: IMembershipFactory = new BasicMembershipFactory
    val premiumFactory: IMembershipFactory = new PremiumMembershipFactory
    val vipFactory: IMembershipFactory = new VIPMembershipFactory

    val basicMembership: IMembership = basicFactory.createMembership
    val premiumMembership: IMembership = premiumFactory.createMembership
    val vipMembership: IMembership = vipFactory.createMembership

    println("Welcome to the Fitness Club!")
    println("=================================")
    println("Your Memberships:")
    println("1. " + basicMembership.getDescription + ". >> Price: " + basicMembership.getPrice)
    println("2. " + premiumMembership.getDescription + ". >> Price: " + premiumMembership.getPrice)
    println("3. " + vipMembership.getDescription + ". >> Price: " + vipMembership.getPrice + ". +Bonus: " + vipMembership.getBonus)
  }
}
