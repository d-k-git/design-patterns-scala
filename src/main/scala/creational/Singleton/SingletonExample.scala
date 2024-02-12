package creational.Singleton

/**
 * The Singleton pattern is suitable for tasks when:
   - there must be exactly one instance of a class, and it must be accessible to clients from a well-known access point.
   - the sole instance should be extensible by subclassing, and clients should be able to use an extended instance
     without modifying their code. (GoF, Design Patterns)
 */

//class MyClass

object SingletonExample {

  private var instanceCount = 0

  def getInstance: SingletonExample.type = {
    instanceCount += 1
    println(s"Creating instance $instanceCount")
    SingletonExample
  }

  def printMessage(): Unit = {
    println("Hello from Singleton!")
  }


  def main(args: Array[String]): Unit = {



    val singleton1 = SingletonExample.getInstance
    val singleton2 = SingletonExample.getInstance
    val singleton3 = SingletonExample.getInstance

    //    val myObject1 = new MyClass
    //    val myObject2 = new MyClass

    singleton1.printMessage()
    singleton2.printMessage()
    singleton3.printMessage()

    printInstanceInfo("singleton1", singleton1)
    printInstanceInfo("singleton2", singleton2)
    printInstanceInfo("singleton3", singleton3)
    // printInstanceInfo("myObject1", myObject1)
    // printInstanceInfo("myObject2", myObject2)

    // Checking by HashCode whether instances are singleton or not
    def printInstanceInfo(name: String, instance: AnyRef): Unit = {
      val isSingleton = singleton1 eq singleton2
      //val isSingleton =  myObject1 eq myObject2
      println(s"$name is${if (isSingleton) "" else " not"} a singleton")
    }

    val hashCode1 = singleton1.hashCode()
    val hashCode2 = singleton2.hashCode()
    val hashCode3 = singleton3.hashCode()

    println(s"Hash code of singleton1: $hashCode1")
    println(s"Hash code of singleton2: $hashCode2")
    println(s"Hash code of singleton3: $hashCode3")

  }
}




//object SimpliestSingleton {
//  def sayHello(): Unit = {
//    println("Hello from SimpleSingleton!")
//  }
//}
//
//object SingletonExample {
//  def main(args: Array[String]): Unit = {
//    SimpliestSingleton.sayHello()
//  }
//}