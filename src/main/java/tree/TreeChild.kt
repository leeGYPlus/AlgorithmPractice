package tree

class TreeChild<E>(data: E) {
    private val DEFAULT_TREE_SIZE = 100
    private var treeSize = 0
    private var nodes: Array<Node<E>?>
    private var nodeNums = 0

    init {
        treeSize = DEFAULT_TREE_SIZE
        nodes = arrayOfNulls(this.treeSize)
        nodes[0] = Node(data)
        nodeNums++
    }

    constructor(data: E, treeSize: Int) : this(data) {
        this.treeSize = treeSize
    }

    fun addNode(data: E, parent: Node<E>) {
        for ((index, value) in nodes.withIndex()) {
            // 找对数组中第一个为 null 的元素，该元素保存新的节点
            if (value == null) {
                // 将数组保存到数组中，在接下来的操作中，建立节点之间的关系
                nodes[index] = Node(data)
                if (parent.first == null) {
                    parent.first = SonNode(index, null)
                } else {
                    // 遍历子链
                    var next = parent.first
                    while (next?.next != null) {
                        next = next.next
                    }
                    next?.next = SonNode(index, null)
                }
                nodeNums++
                return
            }
        }
        throw RuntimeException("该树已满，无法添加新的节点")
    }

    fun empty(): Boolean = nodes[0] == null

    fun root(): Node<E> = nodes[0]!!

    /**
     * 返回指定节点（非叶子节点）的所有子节点
     */
    fun children(parent: Node<E>): List<Node<E>> {
        val list = mutableListOf<Node<E>>()
        var next = parent.first
        while (next != null) {
            list.add(nodes[next.pos]!!)
            next = next.next
        }
        return list
    }

    /**
     * 返回指定节点的第 index 个子节点
     */
    fun child(parent: Node<E>, index: Int): Node<E>? {
        var next = parent.first
        var i:Int = 0
        while (next != null){
            if (i == index) {
                return nodes[next.pos]
            }
            next = next.next
            i++
        }
        return null
    }

    fun deep(): Int = deep(root())

    /**
     * 获得数的深度
     */
    private fun deep(node: Node<E>): Int {
        if (node.first == null) {
            return 1
        } else {
            var max = 0
            var next = node.first
            while (next != null) {
                var tmp = deep(nodes[next.pos]!!)
                if (tmp > max) {
                    max = tmp
                }
                next = next.next

            }
            return max + 1
        }
    }

    fun pos(node: Node<E>): Int {
        for (index in nodes.indices) {
            if (nodes[index] == node) {
                return index
            }
        }
        return -1
    }

    companion object {
        // 节点 Node中如果拥有子链，那么 SonNode 通过 next 用来维护 子链的关系，以及子链元素在数组中的索引
        class SonNode(val pos: Int, var next: SonNode?)
        // 树中的节点，
        class Node<T>(private val data: T) {
            // 第一个子节点
            var first: SonNode? = null
            override fun toString(): String = if (first != null) {
                "SonNode(data=$data, first.pos=${first?.pos})"
            } else {
                "SonNode(data=$data, first.pos=-1)"
            }
        }

    }
}

fun main(args: Array<String>) {
    val tree = TreeChild("", 2)
}