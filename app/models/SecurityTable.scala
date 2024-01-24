package models

import slick.jdbc.PostgresProfile.api._



class SecurityTable(tag: Tag) extends Table[SecurityModel](tag, "securities") {

  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  def idSecurity = column[String]("id_security")

  def secId = column[String]("sec_id")

  def regNumber= column[String]("reg_number")

  def name = column[String]("name")

  def emitentTitle = column[String]("emitent_title")

 def * = (id, idSecurity, secId, regNumber, name, emitentTitle) <> (SecurityModel.tupled, SecurityModel.unapply)


}

