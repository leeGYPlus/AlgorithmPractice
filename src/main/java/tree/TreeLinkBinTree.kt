package tree

import com.sun.jmx.remote.internal.ArrayQueue
import java.util.*

class TreeLinkBinTree<E> {

    private var root: TreeNode? = null

    init {
        this.root = TreeNode()
    }

    constructor(data: E) {
        this.root = TreeNode(data!!)
    }

    fun addNode(parent: TreeNode?, data: E, isLeft: Boolean): TreeNode {
        if (parent == null) throw RuntimeException("parent 为空，无法添加子节点")
        if (isLeft && parent.left != null) throw RuntimeException("$parent 已经存在左节点，无法添加左节点")
        if (!isLeft && parent.right != null) throw RuntimeException("$parent 已经存在右节点，无法添加右节点")
        val newNode = TreeNode(data!!)
        when (isLeft) {
            true -> parent.left = newNode
            false -> parent.right = newNode
        }
        // 指定新节点的 parent
        newNode.parent = parent
        return newNode
    }

    fun empty(): Boolean = root?.data == null

    fun root(): TreeNode? {
        if (empty()) throw RuntimeException("树为空，无法访问根结点")
        return root
    }

    fun parnet(node: TreeNode): E {
        if (node == null) throw RuntimeException("节点为 null，无法访问其父节点")
        return node.parent?.data as E
    }

    fun leftChild(parent: TreeNode?): E? {
        if (parent == null) throw RuntimeException("节点为空，无法访问其子节点")
        return parent.left?.data as E ?: null
    }


    fun rightChild(parent: TreeNode?): E? {
        if (parent == null) throw RuntimeException("节点为空，无法访问其子节点")
        return parent.right?.data as E ?: null
    }

    fun deep(): Int = deep(root)

    private fun deep(node: TreeNode?): Int {
        if (node == null) return 0
        if (node.left == null && node.right == null) {
            return 1
        } else {
            val leftDeep = deep(node.left)
            val rightDeep = deep(node.right)
            val max = when {
                leftDeep > rightDeep -> leftDeep
                else -> rightDeep
            }
            return max + 1
        }
    }

    //先序遍历
    fun preIterator(): List<TreeNode> = preIterator(root)

    //中序遍历
    fun inIterator(): List<TreeNode> = inIterator(root)

    //后序遍历
    fun lastIterator(): List<TreeNode> = lastIterator(root)

    private fun preIterator(node: TreeNode?): List<TreeNode> {
        if (node == null) throw RuntimeException("节点为空，无法访问其子节点")
        val list = mutableListOf<TreeNode>()
        // 处理根结点
        list.add(node)
        // 递归处理左子树
        node.left?.let {
            list.addAll(preIterator(it))
        }
        // 递归处理右子树
        node.right?.let {
            list.addAll(preIterator(it))
        }
        return list
    }

    private fun inIterator(node: TreeNode?): List<TreeNode> {
        if (node == null) throw RuntimeException("节点为空，无法访问其子节点")
        val list = mutableListOf<TreeNode>()

        // 递归处理左子树
        node.left?.let {
            list.addAll(inIterator(it))
        }
        // 处理根结点
        list.add(node)
        // 递归处理右子树
        node.right?.let {
            list.addAll(inIterator(it))
        }
        return list
    }
    private fun lastIterator(node: TreeNode?): List<TreeNode> {
        if (node == null) throw RuntimeException("节点为空，无法访问其子节点")
        val list = mutableListOf<TreeNode>()

        // 递归处理左子树
        node.left?.let {
            list.addAll(lastIterator(it))
        }
        // 递归处理右子树
        node.right?.let {
            list.addAll(lastIterator(it))
        }
        // 处理根结点
        list.add(node)
        return list
    }

    // 广度优先遍历
    fun breadFirst():List<TreeNode>{
        val queue = ArrayDeque<TreeNode>()
        val list = mutableListOf<TreeNode>()
        if (root != null){
            // 将根元素添加到队列中
            queue.offer(root)
        }
        while (queue.isNotEmpty()){
            // 将队列尾部的数据添加到list 中，peek 获得但是不移除
            list.add(queue.peek())
            val treeNode = queue.poll()
            treeNode.left?.let {
                queue.offer(it)
            }
            treeNode.right?.let {
                queue.offer(it)
            }
        }
        return list
    }

    companion object {
        class TreeNode() {
            var data: Any? = null
            var parent: TreeNode? = null
            var left: TreeNode? = null
            var right: TreeNode? = null

            constructor(data: Any) : this() {
                this.data = data
            }

            constructor(data: Any, left: TreeNode, right: TreeNode, parent: TreeNode) : this(data) {
                this.left = left
                this.right = right
                this.parent = parent
            }

            override fun toString(): String {
                return "TreeNode(data=$data, parent=${parent?.data}, left=${left?.data}, right=${right?.data})\n"
            }


        }
    }
}


fun main(args: Array<String>) {
    val binTree = TreeLinkBinTree<String>("根结点")
    val treeNode1 = binTree.addNode(binTree.root(), "第二层左节点", true)
    val treeNode2 = binTree.addNode(binTree.root(), "第二层右节点", false)
    val treeNode3 = binTree.addNode(treeNode2, "第三层左节点", true)
    val treeNode4 = binTree.addNode(treeNode2, "第三层右节点", false)
    val treeNode5 = binTree.addNode(treeNode3, "第四层左节点", true)
    println("treeNode2 的左子节点：${binTree.leftChild(treeNode2)}")
    println("treeNode2 的右子节点：${binTree.rightChild(treeNode2)}")
    println("树的深度：${binTree.deep()}")
    println("广度优先遍历： ${binTree.breadFirst()}")
    println("先序遍历：${binTree.preIterator()}")
}