package http

import drivers.MongoDriver
import drivers.toJson
import drivers.toSpecification
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

val getSpecificationsHandler: Handler<RoutingContext> = Handler { ctx ->
    ctx.response().end(MongoDriver().getSpecifications().toJson().toString())
}

val addSpecificationHandler: Handler<RoutingContext> = Handler { ctx ->
    ctx.request().body()
        .map { it.toJsonObject().toSpecification() }
        .map { MongoDriver().addSpecification(it) }.onComplete {
            when (it.succeeded()) {
                true -> ctx.response().setStatusCode(201).end()
                else -> ctx.response().setStatusCode(400).end()
            }
        }
}

val updateSpecificationHandler: Handler<RoutingContext> = Handler { ctx ->
    ctx.request().body()
        .map { it.toJsonObject().toSpecification() }
        .map { MongoDriver().updateSpecification(ctx.pathParam("id"), it) }.onSuccess {
            when (it) {
                true -> ctx.response().setStatusCode(200).end()
                else -> ctx.response().setStatusCode(400).end()
            }
        }.onFailure {
            ctx.response().setStatusCode(400).end()
        }
}

val deleteSpecificationHandler: Handler<RoutingContext> = Handler { ctx ->
    when (MongoDriver().deleteSpecification(ctx.pathParam("id"))) {
        true -> ctx.response().setStatusCode(200).end()
        else -> ctx.response().setStatusCode(400).end()
    }
}
