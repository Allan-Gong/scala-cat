name := "scala-cat"

version := "0.1"

scalaVersion := "2.12.6"

scalacOptions += "-Ypartial-unification"

libraryDependencies += "org.typelevel" %% "cats-core" % "1.0.1"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.7"
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.5"
libraryDependencies += "com.lihaoyi" %% "upickle" % "0.6.6"

