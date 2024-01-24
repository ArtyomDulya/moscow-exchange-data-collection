package models

import java.sql.Date

/**
 * Модель История торгов
 * @param id
 * @param secId
 * @param tradeDate
 * @param numTrades
 * @param open
 * @param close
 */
final case class TradeHistoryModel(
                                    id: Option[Long],
                                    secId: String,
                                    tradeDate: Option[Date],
                                    numTrades: Option[Double],
                                    open: Option[Double],
                                    close: Option[Double]
                                  )
