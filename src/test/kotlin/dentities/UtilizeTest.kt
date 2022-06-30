package dentities

import lexer.tokenizer.enums.Key
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import lexer.tree.ActionTree
import lexer.tree.buildActionTree

internal class UtilizeTest {
    @TestFactory
    fun letTest() = listOf(
        "let test: Int"
    ).map { input: String ->
        DynamicTest.dynamicTest("Token Line value is $input expected") {
            val actionTree: ActionTree = buildActionTree(input)
            print(actionTree)
            Key.LET.Utilize(actionTree.head)
        }
    }
}
