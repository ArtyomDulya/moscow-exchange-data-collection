package configuration

import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import play.api.Play
import slick.jdbc.JdbcBackend.Database

import javax.inject.Inject

class DataBaseMigrationConfig @Inject()(db: Database) {

  def runMigrations(): Unit = {
    val connection = db.createSession().conn

    try {
      val changelogPath = "db/changelog/changelog-master.yaml"

      val liquibase = new Liquibase(changelogPath, new ClassLoaderResourceAccessor(getClass.getClassLoader), new JdbcConnection(connection))
      liquibase.update("")
    } finally {
      connection.close()
    }
  }

}
