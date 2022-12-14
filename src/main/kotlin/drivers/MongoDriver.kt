package drivers

import com.mongodb.ConnectionString
import entities.Entities.Specification
import io.github.cdimascio.dotenv.dotenv
import org.bson.types.ObjectId
import org.litote.kmongo.*
import org.litote.kmongo.id.toId

class MongoDriver {
    companion object {
        val CONNECTION_STRING: String = dotenv()["MONGO_CONNECTION_STRING"]
    }

    private val client = KMongo.createClient(ConnectionString(CONNECTION_STRING))
    private val database = client.getDatabase("mas-organizations")
    private val specifications = database.getCollection<Specification>("specifications")

    fun getSpecifications(): List<Specification> = specifications.find().toList()

    fun addSpecification(s: Specification): Result<Unit> = kotlin.runCatching { specifications.save(s) }

    fun updateSpecification(id: String, s: Specification): Boolean =
        specifications.updateOneById(ObjectId(id).toId<Specification>(), s).wasAcknowledged()

    fun deleteSpecification(id: String): Boolean =
        specifications.deleteOneById(ObjectId(id).toId<Specification>()).wasAcknowledged()
}
