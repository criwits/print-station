ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"
ThisBuild / organization := "top.criwits"

lazy val root = (project in file("."))
  .settings(
    name := "PrintStation",
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % "1.2.10",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
      "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
      "org.apache.pdfbox" % "pdfbox" % "2.0.22",
      "org.jline" % "jline" % "3.21.0",
      "net.samuelcampos" % "usbdrivedetector" % "2.2.1"
    )
  )
