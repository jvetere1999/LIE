package lexer

import lexer.tokenizer.enums.Identity

data class Word(var text: String, var identifier: Identity) {

    fun isA(identity: Identity): Boolean {
        return identity == identifier
    }
    val isAnOperator: () -> Boolean = { this.isA(Identity.Operation) }

    override fun toString(): String {
        return "{$identifier: $text}"
    }
}

typealias Words = MutableList<Word>
