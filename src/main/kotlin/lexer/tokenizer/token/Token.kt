package lexer.tokenizer.token

data class Token(val char: Char = '!', val index: Int = -1, val err: Boolean = false) {
    var isASpace: Boolean = char.isWhitespace();

    fun isAnError(): Boolean {
        return err
    }

    override fun toString(): String {
        return char.toString()
    }
}







