val scala3Version = "3.4.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "aoc2019",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % "test"
  )
