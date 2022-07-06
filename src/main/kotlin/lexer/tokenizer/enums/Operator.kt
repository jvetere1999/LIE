package lexer.tokenizer.enums

import exceptions.InvalidOperationException
import lexer.Word
import lexer.tree.TreeNode
import java.lang.Integer.parseInt

val OP_PATTERN = Regex("[+ - = ! @ \\ $ % ^ & * ( ) \"  / + , :]")
enum class Operator {
    Equals {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            TODO("Not yet implemented")
        }
    },
    Plus {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            var RIGHT = right
            println(RIGHT)
            var LEFT = left

            if (LEFT.isA(Identity.Variable) )
            if (RIGHT.isA(Identity.Operation))
                 RIGHT = RIGHT.operate()
            println(RIGHT)
            if (isNumericalLiteral(LEFT)) {
                val leftInt = parseInt(LEFT.getText())
                val rightInt = parseInt(RIGHT.getText())
                val sum = leftInt + rightInt
                head.changeText(sum.toString())
                head.changeIdentity(Identity.Literal)
                head.integerValue = sum
                return head

            }
            return head
        }
    },
    Minus {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            TODO("Not yet implemented")
        }
    },
    Exclamation {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            TODO("Not yet implemented")
        }
    },
    Ampersand {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            TODO("Not yet implemented")
        }
    },
    FSlash {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            TODO("Not yet implemented")
        }
    },
    BSlash {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            TODO("Not yet implemented")
        }
    },
    OpenParenthesis {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            TODO("Not yet implemented")
        }
    },
    CloseParenthesis {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            TODO("Not yet implemented")
        }
    },
    Quote {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            TODO("Not yet implemented")
        }
    },
    Multiply {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            TODO("Not yet implemented")
        }
    },
    MONEY {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            TODO("Not yet implemented")
        }
    },
    AT {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            TODO("Not yet implemented")
        }
    },
    Percent {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            TODO("Not yet implemented")
        }
    },
    Exponent {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            TODO("Not yet implemented")
        }
    },
    Sep {
        override fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode {
            TODO("Not yet implemented")
        }
    };

    abstract fun Utilize(head: TreeNode, left: TreeNode, right: TreeNode): TreeNode
}
val isNumericalLiteral: (TreeNode) -> Boolean = { LEFT: TreeNode ->
    LEFT.isA(Identity.Literal) && LEFT.getText().matches(Regex("[\\d+]")) }

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