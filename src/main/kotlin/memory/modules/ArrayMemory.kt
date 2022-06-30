package memory.modules

import memory.Memory
import memory.Type
// Big TODO Figure out how to implement an array that is type static
class ArrayMemory: Memory<MutableList<Any>>() {
    override val type: Type = Type.ARRAY
    override val internal: MutableMap<String, MutableList<Any>> = mutableMapOf()
}