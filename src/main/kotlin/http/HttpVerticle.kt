package http

import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router

class HttpVerticle : AbstractVerticle() {
    override fun start() {
        vertx.createHttpServer().requestHandler(router()).listen(3000, "0.0.0.0").map { server ->
            println("httpServer.isMetricsEnabled: ${server.isMetricsEnabled}")
            println("httpServer connected on port: ${server.actualPort()}")
        }
    }

    private fun router(): Router {
        val router: Router = Router.router(vertx)
        router.get("/specifications").handler(getSpecificationsHandler)
        router.post("/specifications").handler(addSpecificationHandler)
        router.put("/specifications/:id").handler(updateSpecificationHandler)
        router.delete("/specifications/:id").handler(deleteSpecificationHandler)
        return router
    }
}
