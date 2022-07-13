package lexer

import lexer.tokenizer.regexbased.STRING_TO_TOKEN_FUNCTION_ASSIGNMENT
import lexer.tokenizer.regexbased.STRING_TO_TOKEN_PULL_OPERATOR
import lexer.tokenizer.regexbased.STRING_TO_TOKEN_VARIABLE_ASSIGNMENT
import lexer.tokenizer.regexbased.VALIDATE_PARAMETER
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestFactory

internal class RegexTokenizerKtTest {

    @TestFactory
    fun split() = listOf(

    ){
    }

    @TestFactory
    fun getPULL_VARIABLE_ASSIGNMENT() = listOf(
        "let x: Int = 1" to "Int",
        "let x: String = \"ads\"" to "String",
        "let x: Boolean = true" to "Boolean",
        "let x: Int = 2+x+ 4" to "Int",
    ).map{ (input, expected) ->
        println(STRING_TO_TOKEN_VARIABLE_ASSIGNMENT(input))
    }
    @TestFactory
    fun `Test Line to tokens for operators`() = listOf(
        "= \"ads\"" to "ads",
        "= true" to "true",
        "= 2+x+ 4" to "4",
        "= 1" to "1",
        "= 1 + x" to "x",
    ).map{ (input, expected) ->
        println(STRING_TO_TOKEN_PULL_OPERATOR(input))
    }

    @Test
    fun getPULL_VARIABLE_REASSIGNMENT() {
    }

    @Test
    fun getPULL_FUNCTION_CALL() {
    }

    @TestFactory
    fun getPULL_FUNCTION_ASSIGNMENT() = listOf(
        "fun assm(var1: Int, var2: Int) {" to "assm",
        "fun send(var1: Int, var2: Int) -> String {" to "String",
        "fun send(var1: Int, var2: Int) -> Int {" to "Int",
        "fun send(var1:Int,var2: Int)->Int{" to "Int",
        "fun send(var1: Int, var2: Int) {" to "send"
    ).map{ (input, expected) ->
        STRING_TO_TOKEN_FUNCTION_ASSIGNMENT(input)
    }
    @Test
    fun `Validate Parameter Sequence` () {
        assertTrue(VALIDATE_PARAMETER.matches("(var1:Int,var2:Int)"))
        assertFalse(VALIDATE_PARAMETER.matches("fun asm(var1:Int, var2: Int)"))
    }
}