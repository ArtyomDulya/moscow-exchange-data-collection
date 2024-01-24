package controllers


import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import play.api.mvc._

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.http.scaladsl.unmarshalling._
import akka.util.ByteString
import models.SecurityModel

import scala.concurrent.{ExecutionContext, Future}
import scala.xml.XML
import play.api.mvc._
import repositories.SecurityRepository
import services.{APIService, XMLService}

@Singleton
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

  def getDataSecurity(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val dataFromDB = securityRepository.getData()

    dataFromDB.map { data =>
      Ok(views.html.security(data))
    }
  }
}
