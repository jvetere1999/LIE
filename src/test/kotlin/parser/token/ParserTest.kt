package parser.token

import lexer.tokenizer.TokenizeString
import lexer.tokenizer.token.Token
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals


class ParserTest {

    @TestFactory
    fun testTokens() = listOf(
        'c' to 'c',
        'b' to 'b',
        'd' to 'd').map { (input, expected) ->
            DynamicTest.dynamicTest("Tokens value is $input expected is $expected") {
                val temp: Token = Token(input)
                Assertions.assertEquals(expected, temp.char)
            }
    }



}