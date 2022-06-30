package lookup.factories

import lexer.tokenizer.enums.Key
import lexer.Word

val FUNCTION_PATTERN = Regex("[A-Z a-z][A-Z a-z \\d]")
class FunctionLookup(){
    private val internal: Map<String, Key> = buildFunLookup()

    companion object Factory {
        fun create(): FunctionLookup = FunctionLookup()
    }

    fun contains(key: String): Boolean = internal.contains(key)

    operator fun invoke(key: String): Boolean   = internal.containsKey(key)
    operator fun invoke(key: Word): Boolean     = internal.containsKey(key.text)

    operator fun get(key: String): Key = internal[key] ?: Key.ERROR
    operator fun get(key: Word): Key = internal[key.text] ?: Key.ERROR

}

fun buildFunLookup(): Map<String, Key> {
    val internal: MutableMap<String, Key> = mutableMapOf()
    internal["print"]   = Key.PRINT
    internal["should"]  = Key.SHOULD
    internal["if"]      = Key.IF
    internal["not"]     = Key.NOT
    internal["dir"]     = Key.DIR
    internal["return"]  = Key.RETURN
    internal["let"]     = Key.LET

    return internal
}

public val FUNCTION_LOOKUP: FunctionLookup = FunctionLookup.create()

