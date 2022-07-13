package possiblydeprecated

import lexer.tokenizer.enums.Identity
import lexer.tokenizer.enums.OP_PATTERN
import lexer.Word
import lexer.Words
import lexer.tokenizer.token.Token
import lookup.factories.FUNCTION_PATTERN
import lookup.factories.FUNCTION_LOOKUP
import lookup.factories.TYPE_LOOKUP

data class TokenList(val tokens: Array<Token>) {

    operator fun get(index: Int): Token {
        return tokens[index]
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TokenList

        if (!tokens.contentEquals(other.tokens)) return false

        return true
    }

    override fun hashCode(): Int {
        return tokens.contentHashCode()
    }
}

val tokenizeStringAndAssertMeaning: (String) -> Words = {text: String ->
    assertMeaning(TokenizeString(text))
}


val TokenizeString: (String) -> Tokens = { text: String ->
    val tokens: MutableList<Token> = arrayListOf()
    for (c: Char in text)
        tokens.add(Token(c))
    tokens
}



fun functionVerifier(tokenString: String): Boolean = FUNCTION_PATTERN.matches(tokenString)

fun operatorVerifier(tokenString: String): Boolean = OP_PATTERN.matches(tokenString)

fun literalVerifier(tokenString: String): Boolean {
    val pattern: Regex = Regex("\\d")
    return pattern.matches(tokenString)
}

fun assertMeaning(tokens: Tokens): Words {
    val words: Words = mutableListOf()
    var currentToken: Token = Token(err = true)

    while (tokens.isNotEmpty()) {
        currentToken = tokens.removeAt(0)
        if (currentToken.isASpace) continue

        if (currentToken.toString() == "\"") {
            var input: TokenLine = TokenLine()
            currentToken = tokens.removeAt(0)
            while (currentToken.toString() != "\"") {
                input.enter(currentToken)
                currentToken = tokens.removeAt(0)
            }
            words.add(Word(tokenLinetoProperString(input), Identity.Literal))
            continue
        }

        if (operatorVerifier(currentToken.toString())) {
            // clean(tokens)
            words.add(Word(currentToken.toString(), Identity.Operation))
            continue
        }

        var input: TokenLine = TokenLine()
        if (literalVerifier(currentToken.toString()) || currentToken.toString() == "\"") {
            input = readToEnd(tokens, currentToken)

            words.add(Word(tokenLinetoProperString(input), Identity.Literal))
            continue
        }

        input = readToEnd(tokens, currentToken)
        val segment: String = tokenLinetoProperString(input)
        if (FUNCTION_LOOKUP.contains(segment))
            words.add(Word(segment, Identity.Function))
        else if (TYPE_LOOKUP.contains(segment))
            words.add(Word(segment, Identity.Type))
        else
            words.add(Word(segment, Identity.Variable))
    }
    return words
}

fun readToEnd(tokens: Tokens, token: Token): TokenLine {
    if (tokens.size <= 0) return TokenLine(token)

    val line: TokenLine = TokenLine()
    var currentToken: Token = token
    
    while (tokens.isNotEmpty() && !currentToken.isASpace && !operatorVerifier(currentToken.toString()) && token.toString() != "\"") {
        line.enter(currentToken)
        currentToken = if (!operatorVerifier(tokens[0].toString())) tokens.removeAt(0) else tokens[0]
    }

    if (tokens.isEmpty() && !currentToken.isASpace)
        line.enter(currentToken)

    return line
}



typealias Tokens = MutableList<Token>
