package lexer.tokenizer.regexbased

import lexer.Word
import lexer.Words
import lexer.tokenizer.enums.Identity

val FUNCTION_ASSIGNMENT: Regex = Regex(
    "(?<Function>fun)\\s+" +
            "(?<Alias>[a-zA-Z][a-zA-Z0-9]+|[a-zA-Z])\\s?+" +
            "(?<Variable>\\(.+\\))" +
            "(?<Return>\\s?+->\\s?+[a-zA-Z][a-zA-Z0-9]+|[a-zA-Z]\\s*\\{|\\s?+\\{)"
)

val PULL_FUNCTION_ASSIGNMENT: (String) -> List<String> = { line: String ->
    val matches: MatchResult? = FUNCTION_ASSIGNMENT.find(line)
    matches?.destructured?.toList()!!
}

val PARAMETER_SEQUENCE_CAPTURE: Regex = Regex("([a-zA-Z][a-zA-Z0-9]*)|(:)|([a-zA-Z][a-zA-Z0-9]*)|(,)")

val PULL_PARAMETER_ASSIGNMENT: (String) -> MutableList<String> = { line: String ->
    val list: MutableList<String> = mutableListOf()
    PARAMETER_SEQUENCE_CAPTURE.findAll(line).iterator().forEach {list.add(it.value)}
    list
}

val STRING_TO_TOKEN_PARAMETER_ASSIGNMENT: (String) -> Words = { line: String ->
    val matches: MutableList<String> = PULL_PARAMETER_ASSIGNMENT(line)
    val words: Words = mutableListOf()
    while (matches.isNotEmpty()) {
        words.add(Word(matches.removeAt(0), Identity.Variable))
        words.add(Word(matches.removeAt(0), Identity.Operation))
        words.add(Word(matches.removeAt(0), Identity.Type))
        if (matches.isNotEmpty()&& matches[0] == ",")
            words.add(Word(matches.removeAt(0), Identity.Operation))
    }

    words
}
val STRING_TO_TOKEN_FUNCTION_ASSIGNMENT: (String) -> Words = { line: String ->
    val matches: List<String> = PULL_FUNCTION_ASSIGNMENT(line)
    val words: Words = mutableListOf()

    val funDesignator: Word = Word(matches[0], Identity.RESERVED)
    words.add(funDesignator)
    val header: Word = Word(matches[1], Identity.Function)
    words.add(header)
    words.add(Word("(", Identity.Operation))
    val parameterSequence: String = matches[2]
    words.addAll(STRING_TO_TOKEN_PARAMETER_ASSIGNMENT(parameterSequence))
    words.add(Word(")", Identity.Operation))
    if (matches[3].matches(Regex(".*->.*"))) {
        val type: String = Regex("[a-zA-Z][a-zA-Z0-9]*").find(matches[3])!!.value
        words.add(Word("->", Identity.Return))
        words.add(Word(type, Identity.Type))
    }
    words.add(Word("{", Identity.Operation))
    words
}