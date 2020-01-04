name := "database_clean"

version := "0.1"

scalaVersion := "2.13.1"
lazy val akka_stream = Seq(
  "com.typesafe.akka" %% "akka-stream" % "2.6.1",
)
lazy val logger = Seq(
  "org.slf4j" % "slf4j-api" % "2.0.0-alpha1",
  "ch.qos.logback" % "logback-classic" % "1.3.0-alpha5"
)
libraryDependencies ++= akka_stream ++ logger

//lazy val `tableclean` = (project in file("."))
//  .enablePlugins(JavaAppPackaging)
//  .settings(
//    mainClass in Compile := Some("clean.Main")
//  )
