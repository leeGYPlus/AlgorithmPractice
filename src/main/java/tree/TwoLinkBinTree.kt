package tree


/**
 * 二叉链表存储方式
 */
class TwoLinkBinTree<E> {

    private lateinit var root: TreeNode

    init {
        root = TreeNode()
    }

    public constructor(data: E) {
        this.root = TreeNode(data!!)
    }

    /**
     * 为指定节点添加子节点
     * parent：父节点
     * data：新节点的数据
     * isLeft：是否是左节点
     * return：返回新节点的值
     */
    fun addNode(parent: TreeNode, data: E, isLeft: Boolean): TreeNode {
        if (parent == null) throw RuntimeException("节点为 null，无法添加子节点")
        if (isLeft && parent.left != null) throw RuntimeException("$parent 已经存在左节点，无法添加左节点")
        if (!isLeft && parent.right != null) throw RuntimeException("$parent 已经存在右节点，无法添加右节点")
        val treeNode = TreeNode(data!!)
        if (isLeft) {
            parent.left = treeNode
        } else {
            parent.right = treeNode
        }
        return treeNode
    }

    fun empty(): Boolean = root.data == null


    fun root(): TreeNode {
        if (empty()) throw RuntimeException("树为空，无法访问根结点")
        return root
    }

    //返回指定节点（非根节点）的父节点
    fun parent(node: TreeNode): TreeNode? {
        // 要想获得指定节点的父节点，需要遍历整棵树
        return null
    }

    // 获得指定节点的左子节点（非叶子节点），不存在时返回 null
    fun leftChild(parent: TreeNode?): E? {
        if (parent == null) throw RuntimeException("节点为null，无法获得子节点")

        return parent.left?.data as E ?: null
    }

    fun rightChild(parent: TreeNode?): E? {
        if (parent == null) throw RuntimeException("节点为null，无法获得子节点")

        return parent.right?.data as E ?: null
    }

    fun deep(): Int = deep(root)

    private fun deep(node: TreeNode?): Int {
        if (node == null) return 0
        if (node.left == null && node.right == null) return 1
        else {
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
            var left: TreeNode? = null
            var right: TreeNode? = null

            constructor(data: Any) : this() {
                this.data = data
            }

            constructor(data: Any, left: TreeNode, right: TreeNode) : this(data) {
                this.left = left
                this.right = right
            }

            override fun toString(): String {
                return "TreeNode(data=$data, left=$left, right=$right)"
            }


        }
    }
}

fun main(args: Array<String>) {
    val binTree = TwoLinkBinTree<String>("根结点")
    val treeNode1 = binTree.addNode(binTree.root(), "第二层左节点", true)
    val treeNode2 = binTree.addNode(binTree.root(), "第二层右节点", false)
    val treeNode3 = binTree.addNode(treeNode2, "第三层左节点", true)
    val treeNode4 = binTree.addNode(treeNode2, "第三层右节点", false)
    val treeNode5 = binTree.addNode(treeNode3, "第四层左节点", true)
    println("treeNode2 的左子节点：${binTree.leftChild(treeNode2)}")
    println("treeNode2 的右子节点：${binTree.rightChild(treeNode2)}")
    println("treeNode2 的父节点：${binTree.parent(treeNode2)}")
    println("树的深度：${binTree.deep()}")
}