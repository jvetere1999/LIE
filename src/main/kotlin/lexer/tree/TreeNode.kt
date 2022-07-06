package lexer.tree

import lexer.tokenizer.enums.Identity
import lexer.Word
import lexer.tokenizer.enums.Operator
import lexer.tokenizer.enums.getOperator

data class TreeNode(val parent: TreeNode? = null, val word: Word) {
    val children: listOfChildren = mutableListOf()

    var integerValue: Int = 0
    init {
        parent?.addChild(this)
    }
    val has: (Int) -> Boolean = { children.size == it }
    fun addChild(node: TreeNode) {
        children.add(node)
    }
    val isA: (Identity) -> Boolean = { word.isA(it)}

    fun operate(): TreeNode {
        if (!word.isAnOperator()) return this
        val op: Operator = getOperator(word)
        return op.Utilize(this, children[0], children[1])
    }

    fun getText(): String = word.text
    fun changeText(newText: String) {this.word.text = newText}
    fun changeIdentity(newIdentity: Identity) {this.word.identifier = newIdentity}
    fun getLeft(): TreeNode = children[0]
    fun getRight(): TreeNode = children[1]
    fun getNextOp(): TreeNode = children[2]

    override fun toString(): String = if (children.isNotEmpty()) "$word \nChildren $children" else "$word"

}

val TreeNodeOf: (Word) -> TreeNode = { word: Word -> TreeNode(null, word) }

typealias listOfChildren = MutableList<TreeNode>