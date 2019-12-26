name := "database_clean"

version := "0.1"

scalaVersion := "2.13.1"
lazy val akka_stream = Seq("com.typesafe.akka" %% "akka-stream" % "2.6.1")
lazy val log4j = Seq(
  "org.apache.logging.log4j" % "log4j-core" % "2.13.0",
  "org.apache.logging.log4j" % "log4j-api" % "2.13.0"
)

libraryDependencies ++= akka_stream ++ log4j

//lazy val `tableclean` = (project in file("."))
//  .enablePlugins(JavaAppPackaging)
//  .settings(
//    mainClass in Compile := Some("clean.Main")
//  )
