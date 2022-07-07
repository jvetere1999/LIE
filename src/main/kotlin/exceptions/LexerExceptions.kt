package exceptions

class InvalidFunctionException(): Throwable() {
    override val message: String = "Invalid function found"
}
class InvalidOperationException(): Throwable() {
    override val message: String = "Invalid Operation found"
}
class InvalidDeclarationException(): Throwable() {
    override val message: String = "Invalid Declaration of type found"
}

class InvalidLineException(line: String): Throwable() {
    override val message: String = "Invalid Line found : $line"
}