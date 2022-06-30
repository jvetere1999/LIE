package lexer.tree

import lexer.tokenizer.enums.Identity
import lexer.tokenizer.enums.Operator
import lexer.tokenizer.enums.getOperator
import exceptions.InvalidDeclarationException
import exceptions.InvalidFunctionException
import lexer.Word
import lexer.Words
import lexer.tokenizer.TokenizeString
import lexer.tokenizer.assertMeaning

data class ActionTree (val text: String, val head: TreeNode) {


    fun getHeadText(): String = head.getText()

    override fun toString(): String {
        return head.toString()
    }
}

fun buildActionTree(line: String): ActionTree {
    val words: Words = assertMeaning(TokenizeString(line))
    val head: TreeNode = if (words[0].isA(Identity.Function)) findFun(words) else findOp(words)
    return ActionTree(line, head)
}

fun findFun(words: Words): TreeNode {
    val function: Word = words.removeAt(0)
    if (function.text == "let")
        return typeAssignment(function, words)
    if (!words[0].isA(Identity.Variable) && !words[0].isAnOperator() && getOperator(words[0]) != Operator.OpenParenthesis)
        throw InvalidFunctionException()
    val head: TreeNode = TreeNode(null, function)


    var     i = 0
    var     low = 0
    while (i < words.size && words[i].text != ")") {
        if (words[i].text == ",") {
            head.addChild(findOp(words.subList(low, i)))
            low = i
        }
        i += 1
    }
    if (i < words.size && words[i].text == ")")
        head.addChild(findOp(words.subList(low, i)))

    return head
}

fun typeAssignment(function: Word, words: Words): TreeNode {
    val head: TreeNode = TreeNode(null, function)
    if (!words[0].isA(Identity.Variable) || words[1].text != ":" || !words[2].isA(Identity.Type))
        throw InvalidDeclarationException()
    val typeAssign: TreeNode = TreeNode(head, words[1])

    TreeNode(typeAssign, words[0])
    TreeNode(typeAssign, words[2])
    if (words.size == 3) return head
    if (!words[4].isAnOperator() && words[3].text != "=")
        throw InvalidDeclarationException()
    val equals: TreeNode = TreeNode(typeAssign, words[3])
    equals.addChild(
        if (words[4].isA(Identity.Function))
            findFun(words.subList(4, words.size))
        else
            findOp(words.subList(4, words.size))
    )

    return head

}

fun findOp(words: Words): TreeNode {
    if (words.size == 1) return TreeNode(null, words[0])
    if (words[0].text == "(") { words.removeAt(0) }

    val head = TreeNode(word = words[1])
    TreeNode(head, words[0])

    var curr: TreeNode = head
    val rightTerminal: Word = words[2]

    var i = 3
    while (i < words.size - 1){
        if (words[i].isAnOperator()) {
            val curr: TreeNode = TreeNode(curr, words[i])
            val secondLeft = TreeNode(curr, words[i + 1])
            i += 2
        }
        else
            i += 1
    }
    TreeNode(curr, rightTerminal)

    return head
}




/**
 * If alphanumeric {AW Where A in [Aa-Zz]: W in [Aa-Zz 0-9]}
 *      Read until the next alphanumeric char Mark as function
 *
 * If Symbol {W: [+ - = ^ ( & $ # @ !]}
 *      Mark as operator Look for specfied operator symbols
 *      Make those children
 *
 * If Variable or Literal {x = 1 | [0-9] | "" | ''}
 *      Mark as Literal
 *
 * If Literal to left of operator make first child of operator then search for second operator
 *
 * Shared scope
 * No functions
 * Strongly typed
 * Easy to read
 *
 */
