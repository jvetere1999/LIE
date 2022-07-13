package lexer.tokenizer.regexbased

import lexer.Words

val FUNCTION_CALL: Regex = Regex(
    "(?<Alias>[a-zA-Z][a-zA-Z0-9]+|[a-zA-Z])\\s?+" +
            "(?<Variable>\\(.+\\))"
)

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