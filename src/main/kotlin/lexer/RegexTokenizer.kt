package lexer

import exceptions.InvalidLineException
import lexer.tokenizer.enums.Identity

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


fun split(line: String): Words {
    return when {
        FUNCTION_ASSIGNMENT.matches(line)   -> STRING_TO_TOKEN_FUNCTION_ASSIGNMENT(line)
        FUNCTION_CALL.matches(line)         -> STRING_TO_TOKEN_FUNCTION_CALL(line)
        VARIABLE_ASSIGNMENT.matches(line)   -> STRING_TO_TOKEN_VARIABLE_ASSIGNMENT(line)
        VARIABLE_REASSIGNMENT.matches(line) -> STRING_TO_TOKEN_VARIABLE_REASSIGNMENT(line)
        OPERATION_VALIDATE.matches(line)    -> STRING_TO_TOKEN_PULL_OPERATOR(line)
        else -> throw InvalidLineException(line)
    }
}

val PULL_FUNCTION_ASSIGNMENT: (String) -> List<String> = { line: String ->
    val matches: MatchResult? = FUNCTION_ASSIGNMENT.find(line)
    matches?.destructured?.toList()!!
}
//Eliminates () so hard code
val PARAMETER_SEQUENCE_CAPTURE: Regex = Regex("\\s*([a-zA-Z][a-zA-Z0-9]*)\\s*(:)\\s*([a-zA-Z][a-zA-Z0-9]*)(,?)")
val PULL_PARAMETER_ASSIGNMENT: (String) -> List<String> = { line: String ->
    PARAMETER_SEQUENCE_CAPTURE.findAll(line).iterator().forEach {}
}
val STRING_TO_TOKEN_FUNCTION_ASSIGNMENT: (String) -> Words = { line: String ->
    val matches: List<String> = PULL_FUNCTION_ASSIGNMENT(line)
    val words: Words = mutableListOf()

    val funDesignator: Word = Word(matches[0], Identity.RESERVED)
    val header: Word = Word(matches[1], Identity.Function)
    val parameterSequence: String = matches[2]

    words
}

val PULL_FUNCTION_CALL: (String) -> List<String> = { line: String ->
    val matches: MatchResult = FUNCTION_CALL.find(line)!!
    println(matches.groups)
    matches.destructured.toList()
}

val STRING_TO_TOKEN_FUNCTION_CALL: (String) -> Words = { line: String ->
    val matches: List<String> = PULL_FUNCTION_CALL(line)
    val words: Words = mutableListOf()
    println("Here $matches")
    words
}

val PULL_VARIABLE_ASSIGNMENT: (String) -> List<String> = { line: String ->
    val matches: MatchResult = VARIABLE_ASSIGNMENT.find(line)!!
    matches.destructured.toList()
}

val GET_TYPE: (String) -> String = { line: String ->
    val matches: MatchResult = TYPE.find(line)!!
    matches.destructured.toList()[0]
}

val STRING_TO_TOKEN_VARIABLE_ASSIGNMENT: (String) -> Words = { line: String ->
    val matches: List<String> = PULL_VARIABLE_ASSIGNMENT(line)
    val words: Words = mutableListOf()
    val let:        Word = Word(matches[0], Identity.RESERVED)
    val alias:      Word = Word(matches[1], Identity.Function)
    val typeOp:     Word = Word(":", Identity.Operation)
    val type:       Word = Word(GET_TYPE(matches[2]), Identity.Type)
    words.add(let)
    words.add(alias)
    words.add(typeOp)
    words.add(type)
    val operatorSection: String = matches[3]
    println(VALIDATE_OPERATORS(operatorSection))
    words.addAll(split(matches[3]))
    words
}

val GET_OPERATORS: (String) -> List<String> = { line: String ->
    val list: MutableList<String> = mutableListOf()
    OPERATION_BREAKDOWN.findAll(line).iterator().forEach { list.add(it.value) }
    list
}
val VALIDATE_OPERATORS: (String) -> Boolean = {
        line: String -> OPERATION_VALIDATE.matches(line)
}

val OPERATION_BREAKDOWN: Regex = Regex("(\\d+)|([^a-zA-Z0-9\\n\\t\\s])|([a-zA-Z][a-zA-Z0-9]*)")
val LITERAL: Regex = Regex("[0-9]+")
val OPERATOR: Regex = Regex("[^a-zA-Z0-9]")
val VARIABLE: Regex = Regex("[a-zA-Z][a-zA-Z0-9]*")

val STRING_TO_TOKEN_PULL_OPERATOR: (String) -> Words = { line: String ->
    val words: Words = mutableListOf()
    GET_OPERATORS(line).forEach {
        val word: Word = Word(it, when {
            LITERAL.matches(it) -> Identity.Literal
            OPERATOR.matches(it) -> Identity.Operation
            VARIABLE.matches(it) -> Identity.Variable
            else -> throw InvalidLineException(line)
        })
        words.add(word)
    }
    words
}

val PULL_VARIABLE_REASSIGNMENT: (String) -> List<String> = { line: String ->
    val matches: MatchResult = VARIABLE_REASSIGNMENT.find(line)!!
    matches.destructured.toList()
}

val STRING_TO_TOKEN_VARIABLE_REASSIGNMENT: (String) -> Words = { line: String ->
    val matches: List<String> = PULL_VARIABLE_REASSIGNMENT(line)
    val words: Words = mutableListOf()

    words
}
