package lexer.tokenizer.regexbased

import exceptions.InvalidLineException
import lexer.Word
import lexer.Words
import lexer.tokenizer.enums.Identity

val OPERATION_VALIDATE: Regex = Regex("=(\\s*([0-9]+|[a-zA-Z][a-zA-Z0-9]*)" +
        "\\s*[+\\-*\\/^])*" +
        "\\s*([0-9]+|[a-zA-Z][a-zA-Z0-9]*)" +
        "|=\\s*\".*\"")

val OPERATION_BREAKDOWN: Regex = Regex("(\\d+)|([^a-zA-Z0-9\\n\\t\\s])|([a-zA-Z][a-zA-Z0-9]*)")
val LITERAL: Regex = Regex("[0-9]+")
val OPERATOR: Regex = Regex("[^a-zA-Z0-9]")
val VARIABLE: Regex = Regex("[a-zA-Z][a-zA-Z0-9]*")

val GET_OPERATORS: (String) -> List<String> = { line: String ->
    val list: MutableList<String> = mutableListOf()
    OPERATION_BREAKDOWN.findAll(line).iterator().forEach { list.add(it.value) }
    list
}
val VALIDATE_OPERATORS: (String) -> Boolean = {
        line: String -> OPERATION_VALIDATE.matches(line)
}


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