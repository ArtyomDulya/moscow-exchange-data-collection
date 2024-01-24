package configuration

/**
 * Это будущая генерация таблицы при запуске приложения, если данная таблица отсутствует
 */

import com.sun.org.slf4j.internal.LoggerFactory
import slick.jdbc.JdbcBackend.Database
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.meta.MTable

import javax.inject.{Inject, Singleton}
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}
import models._
import play.api.inject.ApplicationLifecycle

@Singleton
class DataBaseInitializer @Inject()() {

  val securityTable = TableQuery[SecurityTable]

  val db = Database.forConfig("postgres")

  def createTableIfNotExists(): Future[Unit] = {
    db.run(MTable.getTables).flatMap { existingTables =>
      if (!existingTables.map(_.name.name).contains("securities")) {
        println("Creating table: securities")

        val creationAction = securityTable.schema.create
        db.run(creationAction).map(_ => ())
      } else {
        println("Table already exists: securities")
        Future.successful(())
      }
    }
  }

//  // Добавляем функцию в жизненный цикл приложения
//  lifecycle.addStopHook(() => Future.successful(() => {
//    // Действия при остановке приложения
//    println("Application stopped")
//  }))
//
//  // Вызываем функцию при старте
//  createTableIfNotExists()

}
