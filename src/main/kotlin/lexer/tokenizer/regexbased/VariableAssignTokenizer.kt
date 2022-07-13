package lexer.tokenizer.regexbased

import lexer.Word
import lexer.Words
import lexer.tokenizer.enums.Identity

val VARIABLE_ASSIGNMENT: Regex = Regex(
    "(?<Assign>let)\\s+" +
            "(?<Alias>[a-zA-Z][a-zA-Z0-9]+|[a-zA-Z])\\s?+" +
            "(?<Type>:\\s?+[a-zA-Z][a-zA-Z0-9]+)\\s?+" +
            "(?<Assignment>=.+)")

val TYPE: Regex = Regex(":\\s?+(?<Type>[a-zA-Z][a-zA-Z0-9]+|[a-zA-Z])")

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
    val let: Word = Word(matches[0], Identity.RESERVED)
    val alias: Word = Word(matches[1], Identity.Function)
    val typeOp: Word = Word(":", Identity.Operation)
    val type: Word = Word(GET_TYPE(matches[2]), Identity.Type)
    words.add(let)
    words.add(alias)
    words.add(typeOp)
    words.add(type)
    val operatorSection: String = matches[3]
    println(VALIDATE_OPERATORS(operatorSection))
    words.addAll(split(matches[3]))
    words
}
