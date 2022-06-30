package lexer.tokenizer.enums

import exceptions.InvalidDeclarationException
import lexer.Word
import lexer.tree.TreeNode
// Receive branch at function
enum class Key {
    PRINT {
        override fun Utilize(branch: TreeNode) {
            TODO("Not yet implemented")
        }
    },
    SHOULD {
        override fun Utilize(branch: TreeNode) {
            TODO("Not yet implemented")
        }
    },
    IF {
        override fun Utilize(branch: TreeNode) {
            TODO("Not yet implemented")
        }
    },
    NOT {
        override fun Utilize(branch: TreeNode) {
            TODO("Not yet implemented")
        }
    },
    DIR {
        override fun Utilize(branch: TreeNode) {
            TODO("Not yet implemented")
        }
    },
    RETURN {
        override fun Utilize(branch: TreeNode) {
            TODO("Not yet implemented")
        }
    },
    ERROR {
        override fun Utilize(branch: TreeNode) {
            TODO("Not yet implemented")
        }
    },
    LET {
        override fun Utilize(branch: TreeNode) {
            if (!branch.has(1))
                throw InvalidDeclarationException()
            var operatorNode = branch.getLeft()
            if (!operatorNode.isA(Identity.Operation))
                throw InvalidDeclarationException()

            val nameNode: TreeNode = operatorNode.getLeft()

            val typeNode: TreeNode = operatorNode.getRight()

           println("$nameNode: $typeNode: $operatorNode")

        }
    };

    abstract fun Utilize(branch: TreeNode)
}
val GENERAL_MEM: MutableMap<String, Any> = mutableMapOf()
// Tempoary Solution going to be implemented with a hashmap per type
fun checkDeclare(name: Word): Boolean {
    if (!name.isA(Identity.Variable)) return false
    return GENERAL_MEM.containsKey(name.text)
}