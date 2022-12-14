package drivers

import entities.Entities.Cardinality
import entities.Entities.Compatibility
import entities.Entities.Constraint
import entities.Entities.Group
import entities.Entities.Role
import entities.Entities.Structural
import entities.Entities.Specification
import io.vertx.core.json.JsonObject
import org.bson.types.ObjectId
import org.litote.kmongo.id.toId

fun JsonObject.toSpecification(): Specification = Specification(
    when(this.containsKey("_id")) {
        true -> ObjectId(this.getString("_id")).toId()
        false -> ObjectId().toId()
    },
    this.getJsonObject("structural").toStructural()
)

fun JsonObject.toStructural(): Structural = Structural(
    this.getJsonArray("roles").map { (it as JsonObject).toRole() },
    this.getJsonArray("groups").map { (it as JsonObject).toGroup() }
)

fun JsonObject.toRole(): Role = Role(
    this.getString("name"),
    when (val e = this.getValue("extend")) {
        is JsonObject -> e.toRole()
        else -> null
    },
    this.getInteger("min"),
    this.getInteger("max")
)

fun JsonObject.toGroup(): Group = Group(
    this.getString("name"),
    this.getInteger("min"),
    this.getInteger("max"),
    this.getJsonArray("subgroups").map { (it as JsonObject).toGroup() },
    this.getJsonArray("roles").map { (it as JsonObject).toRole() },
    this.getJsonArray("constraints").map { (it as JsonObject).toConstraint() }
)

fun JsonObject.toConstraint(): Constraint = when(this.containsKey("min")) {
    true -> this.toCardinality()
    else -> this.toCompatibility()
}

fun JsonObject.toCardinality(): Cardinality = Cardinality(
    this.getInteger("min"),
    this.getInteger("max"),
    this.getString("subjectType"),
    this.getString("subject")
)

fun JsonObject.toCompatibility(): Compatibility = Compatibility(
    this.getString("from"),
    this.getString("to"),
    this.getString("scope"),
    this.getBoolean("extendsSubgroups"),
    this.getBoolean("biDir")
)
