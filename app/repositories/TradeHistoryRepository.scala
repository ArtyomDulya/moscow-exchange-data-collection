package repositories

import models.{TradeHistoryModel, TradeHistoryTable}
import slick.jdbc.JdbcBackend.Database
import slick.lifted.TableQuery
import slick.jdbc.PostgresProfile.api._

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future


/**
 * Репозиторий Истории торгов
 */
@Singleton
class TradeHistoryRepository @Inject()() {

  val db = Database.forConfig("postgres")

  private lazy val tradeHistoryTable = TableQuery[TradeHistoryTable]

  def insertData(data:TradeHistoryModel): Future[Int] = db.run(tradeHistoryTable += data)

  def getData(): Future[Seq[TradeHistoryModel]] = db.run(tradeHistoryTable.result)

  def getDataByName(name: String): Future[Seq[TradeHistoryModel]] =
    db.run(tradeHistoryTable.filter(_.value == name).result)

  def getDataById(id: Long): Future[Option[TradeHistoryModel]] =
    db.run(tradeHistoryTable.filter(_.id === id).result.headOption)

  def deleteAllData: Future[Int] = {
    val deleteAction: DBIO[Int] = tradeHistoryTable.delete
    db.run(deleteAction)
  }

}
