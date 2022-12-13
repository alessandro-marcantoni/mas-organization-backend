package drivers

import io.vertx.lang.scala.VertxExecutionContext
import io.vertx.scala.core.Vertx

object VertxDriver {
    val vertx: Vertx = Vertx.vertx()
    implicit val executionContext: VertxExecutionContext = VertxExecutionContext(vertx.getOrCreateContext)

}
