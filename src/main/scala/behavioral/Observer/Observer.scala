package behavioral.Observer

/**
 * The pattern defines a one-to-many dependency between objects so that when one object changes state, all its dependents are
   notified and updated automatically (aka Dependents, Publish-Subscribe)
   The Client creates the publisher and the subscriber objects separatly and registers the subscrbers
   for publisher updates.

 * The Strategy pattern is suitable for tasks where:
  - an abstraction has two aspects, one dependent on the other. Encapsulating these aspects in separate
    objects lets you vary and reuse them independently.
  - a change to one object requires changing others, and you don't know how many objects need to be
    changed.
  - an object should be able to notify other objects without making assumptions about who these objects are.
    In other words, you don't want these objects tightly coupled. (GoF, Design Patterns)
 */

import scala.collection.mutable.{ListBuffer, Map}

case class Event(newItem: String, salePrice: Double)

// Subject trait representing the entity being observed
trait Store {
  private val observers: Map[String, ListBuffer[Observer]] = Map()

  def addObserver(event: String, observer: Observer): Unit = {
    val eventObservers = observers.getOrElseUpdate(event, ListBuffer())
    eventObservers += observer
  }

  def removeObserver(event: String, observer: Observer): Unit = {
    observers.get(event).foreach(obsList => obsList -= observer)
  }

  def notifyObservers(event: String, eventData: Event): Unit = {
    observers.getOrElse(event, ListBuffer()).foreach(_.update(eventData))
  }
}


// Publisher -  the class being observed
class NotificationSystem extends Store {
  def sendNotification(event: String, eventData: Event): Unit = {
    println(s">> Sending notification about the event '$event': $eventData")
    notifyObservers(event, eventData)
  }
}

// Subscriber (aka EventListener) - observer trait representing the observers
trait Observer {
  def update(eventData: Event): Unit
}


// Concrete subscribers
class EmailObserver extends Observer {
  def update(eventData: Event): Unit = {
    println(s"Email notification: $eventData")
  }
}

class SmsObserver extends Observer {
  def update(eventData: Event): Unit = {
    println(s"SMS notification: $eventData")
  }
}

class MobileObserver extends Observer {
  def update(eventData: Event): Unit = {
    println(s"Mobile notification: $eventData")
  }
}

object ObserverPatternExample extends App {
  // Create instances of concrete subjects and observers
  val notificationSystem = new NotificationSystem

  val emailObserver = new EmailObserver
  val smsObserver = new SmsObserver
  val mobileObserver = new MobileObserver

  // Register observers with specific events
  notificationSystem.addObserver("EventA", emailObserver)
  notificationSystem.addObserver("EventA", smsObserver)
  notificationSystem.addObserver("EventB", mobileObserver)

  // Send notifications for specific events
  val eventAData = Event(newItem = "Green coat", salePrice = 1000.0)
  val eventBData = Event(newItem = "Autumn sweater", salePrice = 500.0)
  println("=================================")


  notificationSystem.sendNotification("EventA", eventAData)
  notificationSystem.sendNotification("EventB", eventBData)

  println("=================================")

  // Unregister an observer for a specific event
  notificationSystem.removeObserver("EventA", smsObserver)

  // Send another notification, and only remaining observers will be notified
  notificationSystem.sendNotification("EventA", eventAData)
}



