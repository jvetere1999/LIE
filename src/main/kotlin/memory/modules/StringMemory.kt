package memory.modules

import memory.Memory
import memory.Type

class StringMemory: Memory<String>() {
    override val type: Type = Type.BOOL
    companion object Storage { fun create(): StringMemory = StringMemory() }
    override val internal: MutableMap<String, String> = mutableMapOf()
}