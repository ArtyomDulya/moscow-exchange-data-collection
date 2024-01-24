package controllers



import play.api.mvc._
import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext
import models.SecurityModel
import scala.concurrent.{Future}
import repositories.SecurityRepository
import services.{APIService, XMLService}

/**
 * Контроллер, отвечающий за ценные бумаги
 * @param cc
 * @param apiService
 * @param xmlService
 * @param securityRepository
 * @param ec
 */

@Singleton()
class SecurityController @Inject()()(cc: ControllerComponents,
                                     apiService: APIService,
                                     xmlService: XMLService,
                                     securityRepository: SecurityRepository)
                                  (implicit ec: ExecutionContext) extends AbstractController(cc) {

  def securityDataSave() = Action.async { implicit request: Request[AnyContent] =>

    apiService.fetchDataFromExternalAPISecurity().flatMap { xmlDataDoc =>

      val parsedData: Seq[SecurityModel] = xmlService.parseXMLSecurity(xmlDataDoc)

      val securityModels: Seq[SecurityModel] = parsedData.map { value =>
        SecurityModel(value.id, value.idSecurity, value.secId, value.regNumber, value.name, value.emitentTitle)
      }

      val saveToDB = Future.sequence(securityModels.map(securityRepository.insertData))

      saveToDB.map { _ =>
        Ok(views.html.security(securityModels))
      }
    }
  }

  def deleteAllDataSecurity = Action.async { implicit request =>
    securityRepository.deleteAllData.map { rowsAffected =>
      Ok(s"Удалено $rowsAffected записей.")
    }.recover {
      case ex => InternalServerError(s"Произошла ошибка при удалении данных: $ex")
    }
  }

  def getDataSecurity(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val dataFromDB = securityRepository.getData()

    dataFromDB.map { data =>
      Ok(views.html.security(data))
    }
  }
}
