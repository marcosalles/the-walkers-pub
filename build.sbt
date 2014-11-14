name := "the-walkers-pub"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.webjars" %% "webjars-play" % "2.2.1",
  "org.webjars" % "jquery" % "1.11.1",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)     

play.Project.playJavaSettings
