package exceptions

class NameCollisionException : Exception() {
    override val message: String = "Name collision detected\nA name can only be assigned to one entity at a time"
}
class MemoryErrorException(): Exception() {
    override val message: String = "Memory error detected"
}
