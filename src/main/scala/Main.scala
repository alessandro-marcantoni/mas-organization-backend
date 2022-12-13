import drivers.VertxDriver.vertx
import http.HttpVerticle
import io.vertx.lang.scala.ScalaVerticle.nameForVerticle

import scala.util.{Failure, Success}

object Main extends App {
    import drivers.VertxDriver.executionContext

    vertx.deployVerticleFuture(nameForVerticle[HttpVerticle]).onComplete {
        case Success(value) => println(s"Successfully deployed verticle $value")
        case Failure(exception) => println(s"Failed to deploy verticle $exception")
    }
}
