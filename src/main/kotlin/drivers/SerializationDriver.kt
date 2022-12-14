package drivers

import entities.Entities.Cardinality
import entities.Entities.Compatibility
import entities.Entities.Group
import entities.Entities.Role
import entities.Entities.Specification
import entities.Entities.Structural
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.core.json.array
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj

fun List<Specification>.toJson(): JsonArray = json {
    array {
        this@toJson.forEach { add(it.toJson()) }
    }
}

fun Specification.toJson(): JsonObject = json {
    obj(
        "_id" to this@toJson.id.toString(),
        "structural" to this@toJson.structural.toJson(),
    )
}

fun Structural.toJson(): JsonObject = json {
    obj(
        "roles" to array {
            this@toJson.roles.forEach { add(it.toJson()) }
        },
        "groups" to array {
            this@toJson.groups.forEach { add(it.toJson()) }
        },
    )
}

fun Role.toJson(): JsonObject = json {
    obj(
        "name" to this@toJson.name,
        "extends" to when (val e = this@toJson.extend) {
            null -> null
            else -> e.toJson()
        },
        "min" to this@toJson.min,
        "max" to this@toJson.max,
    )
}

fun Group.toJson(): JsonObject = json {
    obj(
        "name" to this@toJson.name,
        "min" to this@toJson.min,
        "max" to this@toJson.max,
        "subgroups" to array {
            this@toJson.subgroups.forEach { add(it.toJson()) }
        },
        "roles" to array {
            this@toJson.roles.forEach { add(it.toJson()) }
        },
        "cardinalities" to array {
            this@toJson.cardinalities.forEach { add(it.toJson()) }
        },
        "compatibilities" to array {
            this@toJson.compatibilities.forEach { add(it.toJson()) }
        },
    )
}

fun Cardinality.toJson(): JsonObject = json {
    obj(
        "min" to this@toJson.min,
        "max" to this@toJson.max,
        "subjectType" to this@toJson.subjectType,
        "subject" to this@toJson.subject
    )
}

fun Compatibility.toJson(): JsonObject = json {
    obj(
        "from" to this@toJson.from,
        "to" to this@toJson.to,
        "scope" to this@toJson.scope,
        "extendsSubgroups" to this@toJson.extendsSubgroups,
        "biDir" to this@toJson.biDir
    )
}
