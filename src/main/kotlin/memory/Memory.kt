package memory

import exceptions.MemoryErrorException
import exceptions.NameCollisionException
import memory.Type.*
import memory.modules.BooleanMemory
import memory.modules.DoubleMemory
import memory.modules.IntMemory
import memory.modules.StringMemory

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
        if (internal.containsKey(key)) throw NameCollisionException()
        internal[key] = value
        return true
    }

    fun contains(key: String): Boolean = internal.containsKey(key)
}
val LOG: TypeLog = TypeLog.Create()

abstract class Memory <T> {

    abstract val type: Type

    abstract val internal: MutableMap<String, T>

    operator fun get(key: String): T = safeGet(key)

    fun safeGet(key: String): T {
        val temp: T? = internal[key]
        if (temp != null) return temp
        throw IllegalStateException()
    }

    open operator fun set(key: String, value: T): Boolean{
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

val ANY_MEMORY_MAP:     AnyMemory       = AnyMemory.create()
val INT_MEMORY_MAP:     IntMemory       = IntMemory.create()
val STRING_MEMORY_MAP:  StringMemory    = StringMemory.create()
val BOOLEAN_MEMORY_MAP: BooleanMemory   = BooleanMemory.create()
val DOUBLE_MEMORY_MAP:  DoubleMemory    = DoubleMemory.create()

typealias Name = String


val SET_VALUE_IN_MEMORY: (Type, Name, Any) -> Unit = { type: Type, name: Name, value: Any ->
    LOG.set(name, type)
    when (type) {
        INT     -> INT_MEMORY_MAP[name] = value as Int
        STRING  -> TODO()
        BOOL    -> BOOLEAN_MEMORY_MAP[name] = value as Boolean
        DOUBLE  -> DOUBLE_MEMORY_MAP[name]  = value as Double
        ARRAY   -> TODO()
        NTUPLE  -> TODO()
        NONE -> TODO()
        Err -> throw MemoryErrorException()
        else -> ANY_MEMORY_MAP[name] = value
    }
}

val GET_KEY_TYPE: (Name) -> Type = { name: Name -> LOG[name] }


