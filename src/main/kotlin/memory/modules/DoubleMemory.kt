package memory.modules

import memory.Type
import memory.Memory

class DoubleMemory: Memory<DoubleMemory>() {
    override val type: Type = Type.DOUBLE
    companion object Storage { fun create(): DoubleMemory = DoubleMemory() }
    override val internal: MutableMap<String, DoubleMemory> = mutableMapOf()
}