package models

import java.sql.Date


final case class TradeHistoryModel(
                                    id: Option[Long],
                                    secId: String,
                                    tradeDate: Option[Date],
                                    numTrades: String,
                                    open: Double,
                                    close: Double
                                  )
