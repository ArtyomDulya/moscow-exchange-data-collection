package services

import java.text.SimpleDateFormat
import models.{SecurityModel, TradeHistoryModel}
import java.sql.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class XMLService @Inject()() {

  def parseXMLSecurity(xmlData: String): Seq[SecurityModel] = {
    val xml = scala.xml.XML.loadString(xmlData)
    (xml \ "element" \ "subelement").map { node =>
      SecurityModel(
        0,
        secId = (node \ "@SECID").text,
        regNumber = (node \ "@REGNUMBER").text,
        name = (node \ "@NAME").text,
        emitentTitle = (node \ "@EMITENTTITLE").text
      )
    }
  }

  def parseXMLTradeHistory(xmlData: String): Seq[TradeHistoryModel] = {
    val xml = scala.xml.XML.loadString(xmlData)
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")

    (xml \ "element" \ "subelement").map { node =>
      val id = None
      val secId = (node \ "@SECID").text
      val tradeDateStr = (node \ "@TRADEDATE").text
      val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
      val tradeDate = if (tradeDateStr.isEmpty) None else Some(Date.valueOf(LocalDate.parse(tradeDateStr, formatter)))
      val numTrades = (node \ "@NUMTRADES").text
      val openString = (node \ "@OPEN").text
      val open = if (openString.nonEmpty) openString.toDouble else 0.0
      val closeString = (node \ "@CLOSE").text
      val close = if (closeString.nonEmpty) closeString.toDouble else 0.0

      TradeHistoryModel(
        id = id,
        secId = secId,
        tradeDate = tradeDate,
        numTrades = numTrades,
        open = open,
        close = close
      )
    }
  }
}