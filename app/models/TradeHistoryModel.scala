package models

import java.sql.Date


final case class TradeHistoryModel(
                                    id: Option[Long],
                                    secId: String,
                                    tradeDate: Option[Date],
                                    numTrades: Option[Double],
                                    open: Option[Double],
                                    close: Option[Double]
                                  )
