package controllers


import repositories.CombineRepository
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject.{Inject, Singleton}


/**
 * Контроллер сводной таблицы
 * @param cc
 * @param combineRepository
 */

@Singleton
class CombineController @Inject()(val cc: ControllerComponents, combineRepository: CombineRepository) extends AbstractController (cc){


  def combineTable = Action.async { implicit request =>
    val combineResult = combineRepository.combineResult

    combineResult.map { result =>
      Ok(views.html.combine_table(result))
    }
  }
}
