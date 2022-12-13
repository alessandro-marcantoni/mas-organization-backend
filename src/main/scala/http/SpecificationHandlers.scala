package http

import drivers.MongoDriver.getSpecifications
import io.vertx.core.Handler
import io.vertx.scala.ext.web.RoutingContext

import scala.util.Success

object SpecificationHandlers {
    import drivers.VertxDriver.executionContext

    val getSpecificationsHandler: Handler[RoutingContext] = (ctx: RoutingContext) =>
      getSpecifications().onComplete {
          case Success(specs) => ctx.response().setStatusCode(200).end(specs.toString())
          case _ => ctx.response().setStatusCode(500).end()
      }

}
