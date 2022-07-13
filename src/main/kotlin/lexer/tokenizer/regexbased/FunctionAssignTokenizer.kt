package lexer.tokenizer.regexbased

import lexer.Word
import lexer.Words
import lexer.tokenizer.enums.Identity

val FUNCTION_ASSIGNMENT: Regex = Regex(
    "(?<Function>fun)\\s+" +
            "(?<Alias>[a-zA-Z][a-zA-Z0-9]+|[a-zA-Z])\\s?+" +
            "(?<Variable>\\(.+\\))" +
            "(?<Return>\\s?+->\\s?+[a-zA-Z][a-zA-Z0-9]+|[a-zA-Z]\\s?+|\\s?+\\{)"
)

val PULL_FUNCTION_ASSIGNMENT: (String) -> List<String> = { line: String ->
    val matches: MatchResult? = FUNCTION_ASSIGNMENT.find(line)
    matches?.destructured?.toList()!!
}

val PARAMETER_SEQUENCE_CAPTURE: Regex = Regex("([a-zA-Z][a-zA-Z0-9]*)|(:)|([a-zA-Z][a-zA-Z0-9]*)|(,)")

val PULL_PARAMETER_ASSIGNMENT: (String) -> List<String> = { line: String ->
    val list: MutableList<String> = mutableListOf()
    PARAMETER_SEQUENCE_CAPTURE.findAll(line).iterator().forEach {list.add(it.value)}
    list
}

val STRING_TO_TOKEN_PARAMETER_ASSIGNMENT: (String) -> Words = { line: String ->
    val matches: List<String> = PULL_PARAMETER_ASSIGNMENT(line)
    val words: Words = mutableListOf()


    words
}
val STRING_TO_TOKEN_FUNCTION_ASSIGNMENT: (String) -> Words = { line: String ->
    val matches: List<String> = PULL_FUNCTION_ASSIGNMENT(line)
    val words: Words = mutableListOf()

    val funDesignator: Word = Word(matches[0], Identity.RESERVED)
    val header: Word = Word(matches[1], Identity.Function)
    val parameterSequence: String = matches[2]
    println(PULL_PARAMETER_ASSIGNMENT(parameterSequence))

    words
}