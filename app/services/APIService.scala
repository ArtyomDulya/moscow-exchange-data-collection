package services

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.Materializer
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

/**
 * Http клиент
 * @param system
 * @param materializer
 * @param ec
 * @param xmlService
 */

class APIService @Inject()(implicit system: ActorSystem,
                           materializer: Materializer,
                           ec: ExecutionContext, xmlService: XMLService) {

  def fetchDataFromExternalAPISecurity(): Future[String] = {

    val responseFuture: Future[HttpResponse] = Http().singleRequest( HttpRequest(uri = "https://iss.moex.com/iss/securities.xml?start=0"))

    responseFuture.flatMap { response =>
      if (response.status.isSuccess()) {
        Unmarshal(response).to[String]
      } else {
        Future.failed(new RuntimeException(s"Failed to fetch data from API. Status: ${response.status}"))
      }
    }
  }

  def fetchDataFromExternalAPITradeHistory(): Future[String] = {

    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities?start=0"))

    responseFuture.flatMap { response =>
      if (response.status.isSuccess()) {
        Unmarshal(response).to[String]
      } else {
        Future.failed(new RuntimeException(s"Failed to fetch data from API. Status: ${response.status}"))
      }
    }
  }
}
