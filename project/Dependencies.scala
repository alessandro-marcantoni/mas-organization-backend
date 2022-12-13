import sbt._

object Version {
    final val Scala = "2.12.8"
    final val ScalaTest = "3.0.1"
    final val Vertx = "3.8.0"
}

object Library {
    val vertx_codegen = "io.vertx" % "vertx-codegen" % Version.Vertx % "provided"
    val vertx_lang_scala = "io.vertx" %% "vertx-lang-scala" % Version.Vertx
    val vertx_hazelcast = "io.vertx" % "vertx-hazelcast" % Version.Vertx
    val vertx_web = "io.vertx" %% "vertx-web-scala" % Version.Vertx

    //non-vert.x deps
    val scalaTest = "org.scalatest" %% "scalatest" % Version.ScalaTest
}
