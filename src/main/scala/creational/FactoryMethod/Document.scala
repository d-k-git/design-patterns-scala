package creational.FactoryMethod

/**
 * The Factory Method pattern defines an interface (Creator) for creating objects,
 * but it allows subclasses (concrete creators) to decide which (сoncrete product) class to instantiate
 *
 *  The FM pattern is suitable for tasks where:
    - a class can't anticipate the class of objects it must create (e.g. the class is created in runtime)
    - a class wants its subclasses to specify the objects it creates.
    - classes delegate responsibility to one of several helper subclasses,
      and you want to localize the knowledge of which helper subclass is the delegate. (GoF, Design Patterns)
 */


// Product interface
trait Header {
  def display(): Unit
}

// Concrete Product 1
class ResumeHeader extends Header {
  def display(): Unit = println("Resume Header")
}

// Concrete Product 2
class ReportHeader extends Header {
  def display(): Unit = println("Report Header")
}

// Creator interface with factory methods
trait Document {
  def createHeader(): Header
  // Other document-related methods...
}

// Concrete Creator 1
class Resume extends Document {
  def createHeader(): Header = new ResumeHeader
  // Other resume-related methods...
}

// Concrete Creator 2
class Report extends Document {
  def createHeader(): Header = new ReportHeader
  // Other report-related methods...
}

object Main extends App {
  val resume: Document = new Resume
  val report: Document = new Report

  val resumeHeader: Header = resume.createHeader()
  val reportHeader: Header = report.createHeader()

  resumeHeader.display()
  reportHeader.display()
}
