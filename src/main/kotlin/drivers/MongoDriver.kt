package drivers

import com.mongodb.ConnectionString
import entities.Entities.MoiseSpecification
import entities.Entities.Specification
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.id.toId
import org.litote.kmongo.reactivestreams.KMongo

class MongoDriver {
    companion object {
        val CONNECTION_STRING: String = System.getenv("MONGO_CONNECTION_STRING") ?: "mongodb://localhost:27017"
    }

    private val client = KMongo.createClient(ConnectionString(CONNECTION_STRING)).coroutine
    private val database = client.getDatabase("mas-organizations")
    private val specifications = database.getCollection<Specification>("specifications")
    private val moiseSpecs = database.getCollection<MoiseSpecification>("moiseSpecifications")

    fun getSpecifications(): List<Specification> = runBlocking {
        specifications.find().toList()
    }

    fun addSpecification(s: Specification): Boolean = runBlocking {
        specifications.save(s)?.wasAcknowledged() ?: false
    }

    fun updateSpecification(id: String, s: Specification): Boolean = runBlocking {
        specifications.updateOneById(ObjectId(id).toId<Specification>(), s).wasAcknowledged()
    }

    fun deleteSpecification(id: String): Boolean = runBlocking {
        specifications.deleteOneById(ObjectId(id).toId<Specification>()).wasAcknowledged()
    }

    fun addMoiseSpec(s: MoiseSpecification): Boolean = runBlocking {
        moiseSpecs.save(s)?.wasAcknowledged() ?: false
    }

    fun getMoiseSpec(name: String): MoiseSpecification? = runBlocking {
        moiseSpecs.findOne(MoiseSpecification::name eq name)
    }

    fun updateMoiseSpec(s: MoiseSpecification) = runBlocking {
        moiseSpecs.updateOne(MoiseSpecification::name eq s.name, s)
    }
}
