package lexer.tokenizer.regexbased

import lexer.Words

val VARIABLE_REASSIGNMENT: Regex = Regex(
    "(?<Alias>[a-zA-Z][a-zA-Z0-9]+|[a-zA-Z])\\s?+" +
            "(?<Assignment>=.+)")

val PULL_VARIABLE_REASSIGNMENT: (String) -> List<String> = { line: String ->
    val matches: MatchResult = VARIABLE_REASSIGNMENT.find(line)!!
    matches.destructured.toList()
}

val STRING_TO_TOKEN_VARIABLE_REASSIGNMENT: (String) -> Words = { line: String ->
    val matches: List<String> = PULL_VARIABLE_REASSIGNMENT(line)
    val words: Words = mutableListOf()

    words
}
