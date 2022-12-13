package http

import http.SpecificationHandlers.getSpecificationsHandler
import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.ext.web.Router

import scala.concurrent.Future

class HttpVerticle extends ScalaVerticle {
    override def startFuture(): Future[_] =
        vertx.createHttpServer().requestHandler(router().accept(_)).listenFuture(3000, "0.0.0.0").map {
            httpServer =>
                println(
                    s"""httpServer.isMetricsEnabled: ${httpServer.isMetricsEnabled()}
                       |httpServer connected on port: ${httpServer.actualPort()}
                       |""".stripMargin)
        }

    private def router(): Router = {
        val router = Router.router(vertx)
        router.get("/specifications").handler(getSpecificationsHandler)
        router
    }
}
