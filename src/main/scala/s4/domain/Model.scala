package s4.domain

import scala.slick.driver.H2Driver
import scala.slick.session.{ Database, Session }
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Model(name: String, dal: DAL, db: Database) {
  // We only need the DB/session imports outside the DAL
  import dal._
  import dal.profile.simple._

  // Put an implicitSession in scope for database actions
  implicit val implicitSession = db.createSession

  def createDB = dal.create

  def dropDB = dal.drop
  
  def purgeDB = dal.purge

   def getPersons(): List[Person] = {
    val result = Persons.findAllPersons
    println("Got persons: " + result)
    result
  }

  def addPerson(person: Person): Person = {
    val result = Persons.insert(person)
    println("Inserted person: " + result)
    result
  }
}