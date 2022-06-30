package memory.modules

import memory.Type
import memory.Memory

class BooleanMemory: Memory<Boolean>() {
    override val type: Type = Type.BOOL
    companion object Storage { fun create(): BooleanMemory = BooleanMemory() }
    override val internal: MutableMap<String, Boolean> = mutableMapOf()
}
