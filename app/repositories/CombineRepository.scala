package repositories

import models.{CombineModel, SecurityModel, SecurityTable, TradeHistoryModel, TradeHistoryTable}
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery
import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject.{Inject, Singleton}
import scala.concurrent.Future


/**
 * Репозиторий сводной таблицы
 */
@Singleton
class CombineRepository @Inject()() {

  val db = Database.forConfig("postgres")

  private lazy val tradeHistoryTableQuery = TableQuery[TradeHistoryTable]
  private lazy val securityTableQuery = TableQuery[SecurityTable]

  val securityQueryResult: Future[Seq[SecurityModel]] = db.run(securityTableQuery.result)
  val tradeHistoryQueryResult: Future[Seq[TradeHistoryModel]] = db.run(tradeHistoryTableQuery.result)

  implicit def securityToCombinedModel(security: SecurityModel): CombineModel =
    CombineModel(security.id, security.idSecurity, security.secId, security.regNumber, security.name, security.emitentTitle, None, None, None, None)

  implicit def tradeHistoryToCombinedModel(tradeHistory: TradeHistoryModel): CombineModel =
    CombineModel(tradeHistory.id, tradeHistory.secId, "", "", "", "", tradeHistory.tradeDate, tradeHistory.numTrades, tradeHistory.open, tradeHistory.close)

  val combineResult: Future[Seq[CombineModel]] = for {
    securityResult <- securityQueryResult
    tradeHistoryResult <- tradeHistoryQueryResult
  } yield {
    securityResult.map(securityToCombinedModel) ++ tradeHistoryResult.map(tradeHistoryToCombinedModel)
  }
}
