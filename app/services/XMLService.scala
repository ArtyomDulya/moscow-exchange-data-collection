package services

import java.text.SimpleDateFormat
import models.{SecurityModel, TradeHistoryModel}
import java.sql.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * Парсер xml
 */

class XMLService @Inject()() {

  def parseXMLSecurity(xmlData: String): Seq[SecurityModel] = {

    val xml = scala.xml.XML.loadString(xmlData)
    (xml \ "data" \ "rows" \ "row").map { row =>
      SecurityModel(
        id = None,
        idSecurity = (row \ "@id").text,
        secId = (row \ "@secid").text,
        regNumber = (row \ "@regnumber").text,
        name = (row \ "@name").text,
        emitentTitle = (row \ "@emitent_title").text
      )
    }
  }

  def parseXMLTradeHistory(xmlData: String): Seq[TradeHistoryModel] = {
    val xml = scala.xml.XML.loadString(xmlData)
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")

    (xml \ "data" \ "rows" \ "row").map { row =>
      val secId = (row \ "@SECID").text
      val tradeDateString = (row \ "@TRADEDATE").text
      val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
      val tradeDate = if (tradeDateString.isEmpty) None else Some(Date.valueOf(LocalDate.parse(tradeDateString, formatter)))
      val numTradesString = (row \ "@NUMTRADES").text
      val numTrades = if(numTradesString.nonEmpty) Some(numTradesString.toDouble) else None
      val openString = (row \ "@OPEN").text
      val open = if (openString.nonEmpty) Some(openString.toDouble) else None
      val closeString = (row \ "@CLOSE").text
      val close = if (closeString.nonEmpty) Some(closeString.toDouble) else None

      TradeHistoryModel(
        id = None,
        secId = secId,
        tradeDate = tradeDate,
        numTrades = numTrades,
        open = open,
        close = close
      )
    }
  }
}