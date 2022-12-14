package entities

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

object Entities {
    data class Role(val name: String, val extend: Role?, val min: Int, val max: Int)
    data class Group(
        val name: String,
        val min: Int,
        val max: Int,
        val subgroups: List<Group>,
        val roles: List<Role>,
        val cardinalities: List<Cardinality>,
        val compatibilities: List<Compatibility>
    )

    sealed interface Constraint
    data class Cardinality(val min: Int, val max: Int, val subjectType: String, val subject: String) : Constraint
    data class Compatibility(val from: String, val to: String, val scope: String, val extendsSubgroups: Boolean, val biDir: Boolean) : Constraint

    data class Structural(val roles: List<Role>, val groups: List<Group>)
    data class Specification(@BsonId val id: Id<Specification>, val structural: Structural)
}
