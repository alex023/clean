//resolvers += Resolver.url("bintray-sbt-plugins", url("http://dl.bintray.com/sbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = true, includeDependency = true)