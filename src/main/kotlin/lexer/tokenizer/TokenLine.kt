package lexer.tokenizer

import lexer.tokenizer.token.Token


// Only like this because I cannot spell Queue
// Nothing can change during its time in this Queue
// Only the end is visible

data class TokenLine(var first: Token? = null)  {
    private val internal: ArrayList<Token?> = arrayListOf()

    init {
        if (first != null) {
            internal.add(first)
        }
    }
    fun enter(token: Token) {
        internal.add(0, token)
    }

    fun cut(token: Token, index: Int) {
        internal.add(index, token)
    }

    fun leave(): Token {
        return internal.removeAt(internal.size - 1) ?: Token(err = true)
    }

    fun isNotEmpty(): Boolean {
        return internal.size != 0
    }

    override fun toString(): String {
        val sb: StringBuilder = StringBuilder()
        for (word: Token? in internal)
            sb.append(word)
        return sb.toString()
    }

}

fun tokenLinetoProperString(members: TokenLine): String {
    val sb: StringBuilder = StringBuilder()
    while (members.isNotEmpty())
        sb.append(members.leave())
    return sb.toString()
}

fun clean(tokens: MutableList<Token>): MutableList<Token> {
    val currentToken: Token = Token(err = false)
    while (tokens.size > 0 && !currentToken.isASpace)
        tokens.removeAt(0)
    return tokens
}