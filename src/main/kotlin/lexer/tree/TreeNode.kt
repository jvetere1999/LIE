package lexer.tree

import lexer.tokenizer.enums.Identity
import lexer.Word

data class TreeNode(val parent: TreeNode? = null, val word: Word) {
    val children: listOfChildren = mutableListOf()
    init {
        parent?.addChild(this)
    }
    val has: (Int) -> Boolean = { children.size == it }
    fun addChild(node: TreeNode) {
        children.add(node)
    }
    val isA: (Identity) -> Boolean = { word.isA(it)}
    fun getText(): String = word.text
    fun getLeft(): TreeNode = children[0]
    fun getRight(): TreeNode = children[1]
    fun getNextOp(): TreeNode = children[2]

    override fun toString(): String = if (children.isNotEmpty()) "$word \nChildren $children" else "$word"

}

val TreeNodeOf: (Word) -> TreeNode = { word: Word -> TreeNode(null, word) }

typealias listOfChildren = MutableList<TreeNode>