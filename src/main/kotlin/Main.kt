import drivers.MongoDriver
import io.vertx.core.Vertx

val db: MongoDriver = MongoDriver()
fun main() {
    Vertx.vertx().deployVerticle("http.HttpVerticle")
}
