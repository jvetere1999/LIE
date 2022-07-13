val FUNCTION_ASSIGNMENT: Regex = Regex(
    "(?<Function>fun)\\s+" +
            "(?<Alias>[a-zA-Z][a-zA-Z0-9]+|[a-zA-Z])\\s?+" +
            "(?<Variable>\\(.+\\))" +
            "(?<Return>\\s?+->\\s?+[a-zA-Z][a-zA-Z0-9]+|[a-zA-Z]\\s?+|\\s?+\\{)"
)
val FUNCTION_CALL: Regex = Regex(
    "(?<Alias>[a-zA-Z][a-zA-Z0-9]+|[a-zA-Z])\\s?+" +
            "(?<Variable>\\(.+\\))"
)
val VARIABLE_ASSIGNMENT: Regex = Regex(
    "(?<Assign>let)\\s+" +
            "(?<Alias>[a-zA-Z][a-zA-Z0-9]+|[a-zA-Z])\\s?+" +
            "(?<Type>:\\s?+[a-zA-Z][a-zA-Z0-9]+)\\s?+" +
            "(?<Assignment>=.+)")

val TYPE: Regex = Regex(":\\s?+(?<Type>[a-zA-Z][a-zA-Z0-9]+|[a-zA-Z])")

val VARIABLE_REASSIGNMENT: Regex = Regex(
    "(?<Alias>[a-zA-Z][a-zA-Z0-9]+|[a-zA-Z])\\s?+" +
            "(?<Assignment>=.+)")

val OPERATION_VALIDATE: Regex = Regex("=(\\s*([0-9]+|[a-zA-Z][a-zA-Z0-9]*)" +
        "\\s*[+\\-*\\/^])*" +
        "\\s*([0-9]+|[a-zA-Z][a-zA-Z0-9]*)" +
        "|=\\s*\".*\"")

val VALIDATE_PARAMETER: Regex = Regex("\\((\\s*([a-zA-Z][a-zA-Z0-9]*)\\s*(:)\\s*([a-zA-Z][a-zA-Z0-9]*)(,?))*\\)")

val PARAMETER_SEQUENCE_CAPTURE: Regex = Regex("([a-zA-Z][a-zA-Z0-9]*)|(:)|([a-zA-Z][a-zA-Z0-9]*)|(,)")

val OPERATION_BREAKDOWN: Regex = Regex("(\\d+)|([^a-zA-Z0-9\\n\\t\\s])|([a-zA-Z][a-zA-Z0-9]*)")
val LITERAL: Regex = Regex("[0-9]+")
val OPERATOR: Regex = Regex("[^a-zA-Z0-9]")
val VARIABLE: Regex = Regex("[a-zA-Z][a-zA-Z0-9]*")