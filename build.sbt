ThisBuild / scalaVersion := "2.13.12"

ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """moscow-exchange-data-collection""",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      "com.typesafe.slick" %% "slick" % "3.4.1",
      "org.postgresql" % "postgresql" % "42.5.4",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
      "com.typesafe.akka" %% "akka-http" % "10.2.10",
      "org.scala-lang.modules" %% "scala-xml" % "2.1.0",
      "org.liquibase" % "liquibase-core" % "4.20.0"
    )
  )

