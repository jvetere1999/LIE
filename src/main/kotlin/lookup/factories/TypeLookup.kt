package lookup.factories

import memory.Type
import lexer.Word

class TypeLookup {
    private val internal: Map<String, Type> = buildTypeLookup()

    companion object Factory {
        fun create(): TypeLookup = TypeLookup()
    }

    fun contains(key: String): Boolean = internal.contains(key)

    operator fun invoke(key: String): Boolean   = internal.containsKey(key)
    operator fun invoke(key: Word): Boolean     = internal.containsKey(key.text)

    operator fun get(key: String): Type = internal[key] ?: Type.Err
    operator fun get(key: Word): Type = internal[key.text] ?: Type.Err
}

fun buildTypeLookup(): Map<String, Type> {
    val internal: MutableMap<String, Type> = mutableMapOf()
    internal["Int"]         = Type.INT
    internal["String"]      = Type.STRING
    internal["Boolean"]     = Type.BOOL
    internal["Double"]      = Type.DOUBLE
    return internal
}

public val TYPE_LOOKUP: TypeLookup = TypeLookup.create()