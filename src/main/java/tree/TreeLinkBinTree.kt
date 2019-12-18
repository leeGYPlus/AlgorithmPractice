package tree

class TreeLinkBinTree<E> {

    private var root: TreeNode? = null

    init {
        this.root = TreeNode()
    }

    constructor(data: E) {
        this.root = TreeNode(data!!)
    }

    fun addNode(parent:TreeNode,data:E,isLeft:Boolean):TreeNode{
        if (parent == null) throw RuntimeException("parent 为空，无法添加子节点")
        if (isLeft && parent.left != null) throw RuntimeException("$parent 已经存在左节点，无法添加左节点")
        if (!isLeft && parent.right != null) throw RuntimeException("$parent 已经存在右节点，无法添加右节点")
        val newNode = TreeNode(data!!)
        when(isLeft){
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

    fun parnet(node:TreeNode):E{
        if(node == null) throw RuntimeException("节点为 null，无法访问其父节点")
        return node.parent?.data as E
    }

    fun leftChild(parent:TreeNode?):E?{
        if (parent == null) throw RuntimeException("节点为空，无法访问其子节点")
        return parent.left?.data as E ?: null
    }


    fun rightChild(parent:TreeNode?):E?{
        if (parent == null) throw RuntimeException("节点为空，无法访问其子节点")
        return parent.right?.data as E ?: null
    }

    fun deep():Int= deep(root)

    private fun deep(node: TreeNode?): Int {
        if (node == null) return  0
        if(node.left == null && node.right == null){
            return 1
        }else{
            val leftDeep = deep(node.left)
            val rightDeep = deep(node.right)
            val max = when {
                leftDeep > rightDeep -> leftDeep
                else -> rightDeep
            }
            return max + 1
        }
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
        }
    }
}