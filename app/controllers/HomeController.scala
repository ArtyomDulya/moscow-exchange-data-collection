package controllers

import javax.inject._
import play.api.mvc._

/**
 * Контроллер главной страницы
 *
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def homePage() = Action { implicit request =>
    Ok(views.html.home())
  }


}
