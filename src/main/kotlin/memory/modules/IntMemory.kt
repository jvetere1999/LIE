package memory.modules

import memory.Type
import memory.Memory

class IntMemory: Memory<Int>() {
    override val type: Type = Type.INT
    companion object Storage { fun create(): IntMemory = IntMemory() }
    override val internal: MutableMap<String, Int> = mutableMapOf()
    override fun set(key: String, value: Int): Boolean = super.set(key, value)
}