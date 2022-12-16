package http

import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.CorsHandler

class HttpVerticle : AbstractVerticle() {
    override fun start() {
        vertx.createHttpServer().requestHandler(router()).listen(3000, "0.0.0.0").map { server ->
            println("httpServer.isMetricsEnabled: ${server.isMetricsEnabled}")
            println("httpServer connected on port: ${server.actualPort()}")
        }
    }

    private fun router(): Router {
        val router: Router = Router.router(vertx)
        router.route().handler(
            CorsHandler.create()
                .allowedMethods(setOf(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE))
        )
        router.get("/specifications").handler(getSpecificationsHandler)
        router.post("/specifications").handler(addSpecificationHandler)
        router.put("/specifications/:id").handler(updateSpecificationHandler)
        router.delete("/specifications/:id").handler(deleteSpecificationHandler)
        return router
    }
}
