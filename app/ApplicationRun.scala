import configuration.DataBaseInitializer


class ApplicationRun extends App {

  val dbRun = new DataBaseInitializer()

  dbRun.createTableIfNotExists()
}
