package http

import db
import drivers.toJson
import drivers.toSpecification
import entities.Entities
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

val getSpecificationsHandler: Handler<RoutingContext> = Handler { ctx ->
    ctx.response().end(db.getSpecifications().toJson().toString())
}

val addSpecificationHandler: Handler<RoutingContext> = Handler { ctx ->
    ctx.request().body()
        .map { it.toJsonObject().toSpecification() }
        .map { db.addSpecification(it) }.onComplete {
            when (it.succeeded()) {
                true -> ctx.response().setStatusCode(201).end()
                else -> ctx.response().setStatusCode(400).end()
            }
        }
}

val updateSpecificationHandler: Handler<RoutingContext> = Handler { ctx ->
    ctx.request().body()
        .map { it.toJsonObject().toSpecification() }
        .map { db.updateSpecification(ctx.pathParam("id"), it) }.onSuccess {
            when (it) {
                true -> ctx.response().setStatusCode(200).end()
                else -> ctx.response().setStatusCode(400).end()
            }
        }.onFailure {
            ctx.response().setStatusCode(400).end()
        }
}

val deleteSpecificationHandler: Handler<RoutingContext> = Handler { ctx ->
    when (db.deleteSpecification(ctx.pathParam("id"))) {
        true -> ctx.response().setStatusCode(200).end()
        else -> ctx.response().setStatusCode(400).end()
    }
}

val addXmlSpecHandler: Handler<RoutingContext> = Handler { ctx ->
    ctx.request().body()
        .map { it.toString() }
        .map { db.addMoiseSpec(Entities.MoiseSpecification(ctx.pathParam("name"), it)) }
        .onComplete {
            when (it.succeeded()) {
                true -> ctx.response().setStatusCode(201).end()
                else -> ctx.response().setStatusCode(400).end()
            }
        }
}

val getXmlSpecHandler: Handler<RoutingContext> = Handler { ctx ->
    db.getMoiseSpec(ctx.pathParam("name")).also {
        ctx.response()
            .setStatusCode(it?.let { 200 } ?: 404)
            .end(it?.content ?: "")
    }
}
