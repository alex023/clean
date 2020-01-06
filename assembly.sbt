test in assembly := {}

assemblyMergeStrategy in assembly := {
  case "reference.conf"                => MergeStrategy.concat
  case "application.conf"              => MergeStrategy.concat
  case "logback.xml"                   => MergeStrategy.first
  case "module-info.class"             => MergeStrategy.concat
  case PathList("org", "slf4j", _ @_*) => MergeStrategy.first
  case PathList("META-INF", "versions", "9", "module-info.class") =>
    MergeStrategy.concat
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
