package models

import slick.jdbc.PostgresProfile.api._



class SecurityTable(tag: Tag) extends Table[SecurityModel](tag, "security") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

  def secId = column[String]("sec_id")

  def regNumber= column[String]("reg_number")

  def name = column[String]("name")

  def emitentTitle = column[String]("emitent_title")

 def * = (id, secId, regNumber, name, emitentTitle) <> (SecurityModel.tupled, SecurityModel.unapply)


}

