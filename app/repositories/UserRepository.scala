package repositories



import models.{UserModel, UserTable}
import javax.inject.{Inject, Singleton}
import scala.concurrent.{Future}
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.JdbcBackend.Database

/**
 * Репозиторий юзера
 */

@Singleton
class UserRepository @Inject()(){

  val db = Database.forConfig("postgres")

  private lazy val users = TableQuery[UserTable]

  def addUser(user: UserModel): Future[Int] =
    db.run(users += user)

  def validateUser(username: String, password: String): Future[Option[Long]] = {
    val query = users.filter(userRow => userRow.username === username && userRow.password === password)
    db.run(query.result.headOption.map(i => i.map(i => i.id)))
  }

  def getUserByUsername(username: String): Future[Option[UserModel]] =
    db.run(users.filter(_.username === username).result.headOption)

  def getUserById(id: Long): Future[Option[UserModel]] = {
    db.run(users.filter(user => user.id === id).result.headOption)
  }








}
