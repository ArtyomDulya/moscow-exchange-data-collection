package configuration

import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext, Logger}
import slick.jdbc.JdbcBackend.Database
import configuration.DataBaseInitializer
import play.api.mvc.EssentialFilter
import play.api.routing.Router
import slick.jdbc.PostgresProfile.api._


//class MyApplicationLoader extends ApplicationLoader {
//  def load(context: ApplicationLoader.Context) = {
//
//    val database = Database.forConfig("postgres")
//
//    runMigrations(database)
//
//    new BuiltInComponentsFromContext(context) {
//      override val applicationLifecycle = context.lifecycle
//
//      override def router: Router = ???
//
//      override def httpFilters: Seq[EssentialFilter] = ???
//    }.application
//  }
//
//  def runMigrations(db: Database): Unit = {
//    val dataBaseMigrationConfig = new DataBaseInitializer(db)
//    dataBaseMigrationConfig.runMigrations()
//  }
//
//}
