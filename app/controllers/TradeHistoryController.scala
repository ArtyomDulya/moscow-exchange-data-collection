package controllers

import javax.inject._
import models.{TradeHistoryModel}
import play.api.mvc._
import repositories.TradeHistoryRepository
import services.{APIService, XMLService}

import scala.concurrent.{ExecutionContext, Future}

/**
 * Контроллер, отвечающий за Историю торгов
 * @param cc
 * @param apiService
 * @param xmlService
 * @param tradeHistoryRepository
 * @param ec
 */
@Singleton
class TradeHistoryController @Inject()(cc: ControllerComponents,
                                       apiService: APIService,
                                       xmlService: XMLService,
                                       tradeHistoryRepository: TradeHistoryRepository)
                                      (implicit ec: ExecutionContext) extends AbstractController(cc) {

  def tradeHistoryDataSave(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    apiService.fetchDataFromExternalAPITradeHistory().flatMap { xmlData =>
      val parsedData: Seq[TradeHistoryModel] = xmlService.parseXMLTradeHistory(xmlData)

      val tradeHistoryModels: Seq[TradeHistoryModel] = parsedData.map { value =>
        TradeHistoryModel(Some(0L), value.secId, value.tradeDate, value.numTrades, value.open, value.close)
      }

      val saveToDB = Future.sequence(tradeHistoryModels.map(tradeHistoryRepository.insertData))

      saveToDB.map { _ =>
        Ok(views.html.trade_history(tradeHistoryModels))
      }
    }
  }

  def deleteAllDataTradeHistory = Action.async { implicit request =>
    tradeHistoryRepository.deleteAllData.map { rowsAffected =>
      Ok(s"Удалено $rowsAffected записей.")
    }.recover {
      case ex => InternalServerError(s"Произошла ошибка при удалении данных: $ex")
    }
  }

  def getTradeHistory(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>

    val dataFromDB = tradeHistoryRepository.getData()

    dataFromDB.map { data =>
      Ok(views.html.trade_history(data))
    }
  }
}
