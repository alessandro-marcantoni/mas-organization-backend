package entities

object Entities {
    case class Role(name: String, extend: Option[Role], min: Int, max: Int)
    case class Group(name: String, min: Int, max: Int, subgroups: List[Group], roles: List[Role], constraints: List[Constraint])

    trait Constraint
    case class Cardinality(min: Int, max: Int, subjectType: String, subject: String) extends Constraint
    case class Compatibility(from: String, to: String, scope: String, extendsSubgroups: Boolean, biDir: Boolean) extends Constraint

    case class Structural(roles: List[Role], groups: List[Group])

    case class Specification(structural: Structural)

    /*object Role {
        def apply(name: String, extend: Option[Role] = None, min: Int, max: Int): Role =
            Role(new ObjectId(), name, extend, min, max)
    }*/

    /*object Cardinality {
        def apply(min: Int, max: Int, subjectType: String, subject: String): Cardinality =
            Cardinality(new ObjectId(), min, max, subjectType, subject)
    }*/

    /*object Compatibility {
        def apply(from: String, to: String, scope: String, extendsSubgroups: Boolean, biDir: Boolean): Compatibility =
            Compatibility(new ObjectId(), from, to, scope, extendsSubgroups, biDir)
    }*/
}
