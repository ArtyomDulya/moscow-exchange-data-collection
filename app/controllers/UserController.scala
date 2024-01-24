package controllers

import repositories.UserRepository
import models.UserModel
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}


/**
 * Контроллер, отвечающий за регистрацию и аутентификацию
 * @param cc
 * @param userRepository
 * @param ec
 */
@Singleton
class UserController @Inject()(cc: ControllerComponents,
                               userRepository: UserRepository)
                              (ec: ExecutionContext) extends AbstractController(cc) {


  def loginPage() = Action { implicit request =>
    Ok(views.html.login())
  }

  def signIn = Action.async { implicit request =>
    val formData = request.body.asFormUrlEncoded
    val usernameValues = formData.get("username").head
    val passwordValues = formData.get("password").head

    val username = usernameValues.mkString
    val password = passwordValues.mkString

    if (username.nonEmpty) username.mkString else "None"
    if (password.nonEmpty) password.mkString else "None"

    userRepository.validateUser(username, password).map {
      case Some(userId) =>
        Redirect(routes.HomeController.homePage()).withSession("userId" -> userId.toString)
      case None =>
        Unauthorized(Json.obj("message" -> "Invalid credentials"))
    }


  }

  def signUp = Action.async { implicit request =>
    val formData = request.body.asFormUrlEncoded
    val username = formData.get("username").head
    val password = formData.get("password").head

    val newUser = UserModel(0,
      if (username.nonEmpty) username.mkString else "None",
      if (password.nonEmpty) password.mkString else "None"
    )

//    userRepository.addUser(newUser).map { request =>
//      Redirect(routes.HomeController.homePage()).withSession("username" -> username)
//    }.recover {
//      case ex: Exception =>
//        InternalServerError(s"An error occurred while adding user: ${ex.getMessage}")
//    }
    userRepository.addUser(newUser).flatMap { userId =>
      Future.successful(
        Redirect(routes.HomeController.homePage())
          .withSession("userId" -> userId.toString)
      )
    }.recover {
      case ex: Exception =>
        InternalServerError(s"An error occurred while adding user: ${ex.getMessage}")
    }
  }

  def logout = Action {
    Redirect(routes.UserController.loginPage()).withNewSession
  }
}
