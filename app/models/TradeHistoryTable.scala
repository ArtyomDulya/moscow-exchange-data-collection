package models



import slick.jdbc.PostgresProfile.api._

import java.sql.Date

class TradeHistoryTable(tag: Tag) extends Table[TradeHistoryModel](tag, "trade_history") {

  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  def secId = column[String]("sec_id")

  def tradeDate = column[Option[Date]]("trade_date")

  def numTrades = column[String]("num_trades")

  def open = column[Double]("open")

  def close = column[Double]("close")

  def * = (id, secId, tradeDate, numTrades, open, close) <> (TradeHistoryModel.tupled, TradeHistoryModel.unapply)
}
