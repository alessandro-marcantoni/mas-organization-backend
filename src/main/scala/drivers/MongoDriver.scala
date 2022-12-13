package drivers

import entities.Entities.Specification
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.bson.codecs.configuration.{CodecProvider, CodecRegistry}
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.codecs.Macros.createCodecProvider
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

import scala.concurrent.Future

object MongoDriver {
    import VertxDriver.executionContext

    val mongoClient: MongoClient = MongoClient()
    val db: MongoDatabase = mongoClient.getDatabase("mas-web-ide").withCodecRegistry(Codec.codecRegistry)
    implicit val specifications: MongoCollection[Specification] = db.getCollection("specifications")

    implicit val codecProvider: CodecProvider = createCodecProvider[Specification]()

    object Codec {
        val codecRegistry: CodecRegistry = fromRegistries(fromProviders(
            classOf[Specification],
        ), DEFAULT_CODEC_REGISTRY)
    }

    def getSpecifications(specifications: MongoCollection[Specification] = MongoDriver.specifications): Future[List[Specification]] =
        specifications.find().toFuture().map(_.toList)
}
