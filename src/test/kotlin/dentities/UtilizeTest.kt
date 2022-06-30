package dentities

import lexer.tokenizer.enums.Key
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import lexer.tree.ActionTree
import lexer.tree.buildActionTree
import memory.INT_MEMORY_MAP
import kotlin.test.assertEquals

internal class UtilizeTest {
    @TestFactory
    fun letTest() = listOf(
        "let test: Int = 1" to 1
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Token Line value is $input expected") {
            val actionTree: ActionTree = buildActionTree(input)

            Key.LET.Utilize(actionTree.head)
            assertEquals(INT_MEMORY_MAP[input], expected)
        }
    }
}
