package tree

import lexer.Word
import lexer.tokenizer.TokenizeString
import lexer.tokenizer.assertMeaning
import lexer.tokenizer.functionVerifier
import lexer.tokenizer.operatorVerifier
import lexer.tree.buildActionTree
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

internal class ActionTreeTest {
    @TestFactory
    fun testFunctionVerifier() = listOf(
        "print"         to true,
        "lizzard?"      to false,
        "shou9ld"       to true,
        "9print"        to false,
        "lizzard"       to true,
        "/shou9ld"      to false
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Token Line value is $input expected is $expected") {
            kotlin.test.assertEquals(expected, functionVerifier(input))
        }
    }
    @TestFactory
    fun testOperatorVerifier() = listOf(
        "+"         to true,
        "-?"        to false,
        "!"         to true,
        "\""        to true,
        "("         to true,
        "dasadc"    to false,
        "/"         to true
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Token Line value is $input expected is $expected") {
            kotlin.test.assertEquals(expected, operatorVerifier(input))
        }
    }
//    @TestFactory
//    fun testLookForFunction() = listOf(
//        "print"     to "print",
//        "if"        to "if",
//        "should"    to "should"
//    ).map { (input, expected) ->
//        DynamicTest.dynamicTest("Token Line value is $input expected is $expected") {
//            val tree: ActionTree = buildActionTree(input)
//            kotlin.test.assertEquals(expected, tree.getHeadText())
//        }
//    }
//
//    @TestFactory
//    fun opTree() = listOf(
//        "2 + 1"     to "+",
//        "(2+1)/2"        to "()",
//        "2 + 1 * 3"    to "+"
//    ).map { (input, expected) ->
//        DynamicTest.dynamicTest("Token Line value is $input expected $expected") {
//            val node: TreeNode = buildActionTreeForOperator(TokenizeString(input))
//            kotlin.test.assertEquals(expected, node.getText())
//        }
//    }


    @TestFactory
    fun meaningsTest() = listOf(
        "1 + 2" to "+",
        "1 + 2 + 3 * 4" to "+",
        "print(x+1,x*2)" to "print",
        "let x: Int = 1" to "let",
        "let x: Int = 1 + x" to "let",
        "x = 0" to "=",
        "a = \"sads\"" to "="

    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Token Line value is $input expected") {
            val test = buildActionTree(input)
            println(test)
            assertEquals(test.head.word.text, expected)
        }
    }

}