import io.vertx.lang.scala.ScalaVerticle.nameForVerticle
import io.vertx.lang.scala.{ScalaVerticle, VertxExecutionContext}
import io.vertx.scala.core.Vertx
import io.vertx.scala.ext.web.Router

import scala.concurrent.Future
import scala.util.{Failure, Success}

object Main extends App {
    private val vertx = Vertx.vertx()
    vertx.deployVerticleFuture(nameForVerticle[HttpVerticle]).onComplete {
        case Success(value) => println(s"Successfully deployed verticle $value")
        case Failure(exception) => println(s"Failed to deploy verticle $exception")
    }(VertxExecutionContext(vertx.getOrCreateContext))
}

private class HttpVerticle extends ScalaVerticle {
    override def startFuture(): Future[_] = {
        val router = Router.router(vertx)
        val route = router.get("/").handler(_.response().end("hello world"))

        vertx.createHttpServer().requestHandler(router.accept(_)).listenFuture(3000, "0.0.0.0").map {
            httpServer =>
                println(
                    s"""httpServer.isMetricsEnabled: ${httpServer.isMetricsEnabled()}
                       |httpServer connected on port: ${httpServer.actualPort()}
                       |""".stripMargin)
        }
    }
}
