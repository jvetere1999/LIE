package memory

import memory.Type.*
import memory.modules.BooleanMemory
import memory.modules.IntMemory

class TypeLog() {
    private val internal: MutableMap<String, Type> = mutableMapOf()
    companion object Log{ fun Create(): TypeLog = TypeLog()}

    operator fun get(key: String): Type = safeGet(key)

    fun safeGet(key: String): Type {
        val temp: Type? = internal[key]
        if (temp != null) return temp
        throw IllegalStateException()
    }

    fun set(key: String, value: Type): Boolean {
        if
    }
}
val LOG: TypeLog = TypeLog.Create()

abstract class Memory <T> {
    abstract val type: Type
//    companion object Factory {
//        fun create(): Memory
//    }
    abstract val internal: MutableMap<String, T>

    operator fun get(key: String): T = safeGet(key)

    fun safeGet(key: String): T {
        val temp: T? = internal[key]
        if (temp != null) return temp
        throw IllegalStateException()
    }

    fun set(key: String, value: T): Boolean{
        if (internal.containsKey(key)) return false
        internal[key] = value

        return true
    }

    fun clear() = internal.clear()

}

class AnyMemory: Memory<Any>() {
    override val type: Type = ANY
    companion object Storage { fun create(): AnyMemory = AnyMemory() }
    override val internal: MutableMap<String, Any> = mutableMapOf()
}

val ANY_MEMORY_MAP:     AnyMemory = AnyMemory.create()
val INT_MEMORY_MAP:     IntMemory = IntMemory.create()
val BOOLEAN_MEMORY_MAP: BooleanMemory = BooleanMemory.create()

typealias Name = String

val contains: (name: Name) -> Boolean = { name: Name ->

}

val SET_VALUE_IN_MEMORY: (Type, Name, Any) -> Unit = { type: Type, name: Name, value: Any ->
    if ()
}

val GET_KEY_TYPE: (Name) -> Type = { name: Name -> LOG[name] }

val GET_MEMORY_TABLE: (Type, name: Name, value: Any) -> Memory = { type: Type, name: Name, value: Any ->
    when (type) {
        INT     -> TODO()
        STRING  -> TODO()
        BOOL    -> TODO()
        DOUBLE  -> TODO()
        ARRAY   -> TODO()
        NTUPLE  -> TODO()
        NONE    -> TODO()
        ANY     -> TODO()
        Err     -> TODO()
    }
}
