package lexer.tokenizer.enums

import exceptions.InvalidOperationException
import lexer.Word
val OP_PATTERN = Regex("[+ - = ! @ \\ $ % ^ & * ( ) \"  / + , :]")
enum class Operator {
    Equals,
    Plus,
    Minus,
    Exclamation,
    Ampersand,
    FSlash,
    BSlash,
    OpenParenthesis,
    CloseParenthesis,
    Quote,
    Multiply,
    MONEY,
    AT,
    Percent,
    Exponent,
    Sep
}

val getOperator: (Word) -> Operator = { word: Word ->
    when (word.text) {
        "=" -> Operator.Equals
        "+" -> Operator.Plus
        "-" -> Operator.Minus
        "!" -> Operator.Exclamation
        "&" -> Operator.Ampersand
        "/" -> Operator.FSlash
        "\\"-> Operator.BSlash
        "(" -> Operator.OpenParenthesis
        ")" -> Operator.CloseParenthesis
        "\""-> Operator.Quote
        "*" -> Operator.Multiply
        "$" -> Operator.MONEY
        "@" -> Operator.AT
        "%" -> Operator.Percent
        "^" -> Operator.Exponent
        ":" -> Operator.Sep
        else -> {throw InvalidOperationException()
        }
    }
}