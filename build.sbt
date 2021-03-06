import Dependencies._

cancelable in Global := true

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.7",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Advent2018",
    libraryDependencies += scalaTest % Test
  )
