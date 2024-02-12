package creational.Singleton

class NotSingletonExample private (val name: String) {
  println("Creating a new instance")

  def printMessage(): Unit = {
    println("Hello from NotSingleton!")
  }
}

object NotSingletonExample {

  def getInstance(name: String): NotSingletonExample = new NotSingletonExample(name)

  def main(args: Array[String]): Unit = {
    val singleton10 = NotSingletonExample.getInstance("Object 1")
    val singleton20 = NotSingletonExample.getInstance("Object 2")


    singleton10.printMessage()
    singleton20.printMessage()


    printInstanceInfo("singleton1", singleton10)
    printInstanceInfo("singleton2", singleton20)


    // Checking whether instances are singleton or not
    def printInstanceInfo(name: String, instance: AnyRef): Unit = {
      val isSingleton = singleton10 eq singleton20
      println(s"$name is${if (isSingleton) "" else " not"} a singleton")
    }

    val hashCode1 = singleton10.hashCode()
    val hashCode2 = singleton20.hashCode()

    println(s"Hash code of ${singleton10.name}: $hashCode1")
    println(s"Hash code of ${singleton20.name}: $hashCode2")
  }
}
