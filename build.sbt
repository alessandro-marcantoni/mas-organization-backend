import sbt.Package._
import sbt._

scalaVersion := "2.12.6"

libraryDependencies ++= Vector(
    Library.vertx_lang_scala,
    Library.vertx_web,
    Library.vertx_codegen,
    Library.mongodb,
    Library.scalaTest % "test",
)

packageOptions += ManifestAttributes(
    ("Main-Verticle", "scala:vertx.scala.myapp.HttpVerticle"))

