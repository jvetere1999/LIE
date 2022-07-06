package dentities

import lexer.tokenizer.enums.Identity
import lexer.tokenizer.enums.Key
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import lexer.tree.ActionTree
import lexer.tree.buildActionTree
import memory.INT_MEMORY_MAP
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class UtilizeTest {
    @TestFactory
    fun plusLiteralTest() = listOf(
        "1 + 2" to 3,
        "1 +4" to 5,
        "1+1+1" to 3
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Token Line value is $input expected") {
            val actionTree: ActionTree = buildActionTree(input)
            println(actionTree.head)
            actionTree.head.operate()
            assertTrue(actionTree.head.isA(Identity.Literal))
            assertEquals(actionTree.head.integerValue, expected)
        }
    }
}
