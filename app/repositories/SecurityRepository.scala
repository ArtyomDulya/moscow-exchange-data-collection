package repositories

import models.{SecurityModel, SecurityTable}
import slick.jdbc.JdbcBackend.Database
import slick.lifted.TableQuery
import slick.jdbc.PostgresProfile.api._

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

/**
 * Репозиторий Информации о ценных бумагах
 */

@Singleton
class SecurityRepository @Inject()() {

  val db = Database.forConfig("postgres")

  private lazy val securityTable = TableQuery[SecurityTable]

  db.run(securityTable.schema.create)

  def insertData(data: SecurityModel): Future[Int] = db.run(securityTable += data)

  def getData(): Future[Seq[SecurityModel]] = db.run(securityTable.result)

  def getDataByName(name: String): Future[Seq[SecurityModel]] =
    db.run(securityTable.filter(_.value == name).result)

  def getDataById(id: Long): Future[Option[SecurityModel]] =
    db.run(securityTable.filter(_.id === id).result.headOption)

  def deleteAllData: Future[Int] = {
    val deleteAction: DBIO[Int] = securityTable.delete
    db.run(deleteAction)
  }
}
