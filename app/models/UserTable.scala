package models


import slick.jdbc.PostgresProfile.api._



class UserTable(tag: Tag) extends Table[UserModel](tag, "users") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

  def username = column[String]("username")

  def password = column[String]("password")

  def * = (id, username, password) <> (UserModel.tupled, UserModel.unapply)
}
