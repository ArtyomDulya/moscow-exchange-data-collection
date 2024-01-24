package models

import java.sql.Date

/**
 * Модель сводной таблицы
 * @param id
 * @param idSecurity
 * @param secId
 * @param regNumber
 * @param name
 * @param emitentTitle
 * @param tradeDate
 * @param numTrades
 * @param open
 * @param close
 */
case class CombineModel(
                         id: Option[Long],
                         idSecurity: String,
                         secId: String,
                         regNumber: String,
                         name: String,
                         emitentTitle: String,
                         tradeDate: Option[Date],
                         numTrades: Option[Double],
                         open: Option[Double],
                         close: Option[Double]
                       )
