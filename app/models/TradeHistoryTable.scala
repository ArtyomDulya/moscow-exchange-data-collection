package models

import slick.jdbc.PostgresProfile.api._
import java.sql.Date



/**
 * таблица модели сущности: история торгов
 */


class TradeHistoryTable(tag: Tag) extends Table[TradeHistoryModel](tag, "trade_history") {

  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  def secId = column[String]("sec_id")

  def tradeDate = column[Option[Date]]("trade_date")

  def numTrades = column[Option[Double]]("num_trades")

  def open = column[Option[Double]]("open")

  def close = column[Option[Double]]("close")

  def * = (id, secId, tradeDate, numTrades, open, close) <> (TradeHistoryModel.tupled, TradeHistoryModel.unapply)
}
