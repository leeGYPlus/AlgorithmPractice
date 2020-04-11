package tree;


import java.util.ArrayList;
import java.util.List;

/**
 * 子节点链表示法：每个节点记住它所有的子节点，在本例中标记为子节点链
 *
 * 此时的数非二叉树，父节点可能有多个子节点
 *
 * 使用子节点链表示法来表示一棵树
 *
 * 这个数据结构大致如下：
 *
 * Node-——Node———Node——-Node
 *   |      |      |      |
 * SonNode  SN     SN
 * / | \     |      |      |
 *SN  SN  SN          SN
 *
 * 整个树为一个 Node 数组，但是每个 Node 为一个链表
 *
 */
public class ChildTree<E> {

    // 节点的子链元素
    private static class SonNode {
        // 记录当前节点对应的 Node 在数组中的位置
        private int nodeIndex;
        // next 节点和自己拥有相同的父节点
        private SonNode next;

        public  SonNode(int pos, SonNode next) {
            this.nodeIndex = pos;
            this.next = next;
        }
    }

    // 树的节点元素
    public static class Node<T> {
        T data;
        // 记录第一个子节点
        SonNode firstNode;

        public Node(T data) {
            this.data = data;
            this.firstNode = null;
        }

        @Override
        public String toString() {

            if (firstNode != null) {
                return "TreeChild$Node{" +
                        "data=" + data +
                        ", 第一个子节点的索引为： first=" + firstNode.nodeIndex +
                        '}';
            } else {
                return "TreeChild$Node{" +
                        "data=" + data +
                        ", 没有子节点 }";
            }
        }
    }

    private final int DEFAULT_TREE_SIZE = 100;
    private int treeSize = 0;
    private Node<E>[] nodes;
    // 记录节点数
    private int nodeNum;

    // 以指定根结点创建树
    public ChildTree(E data) {
        treeSize = DEFAULT_TREE_SIZE;
        nodes = new Node[treeSize];
        nodes[0] = new Node<>(data);
        nodeNum++;
    }

    public ChildTree(E data, int treeSize) {
        this.treeSize = treeSize;
        nodes = new Node[treeSize];
        nodes[0] = new Node<>(data);
        nodeNum++;
    }

    // 为指定节点添加子节点

    /**
     * 一共有两步操作：
     *
     * 1. 将数据包装成节点（Node），添加到数组中
     * 2. 判断父节点是否包含子节点，将数据包装成 SonNode 添加到父节点的子链中，其中 SonNode 包含了该节点在数组中的索引（i）
     * @param data
     * @param node
     */
    public void addNode(E data, Node node) {
        for (int i = 0; i < treeSize; i++) {
            // 找到数组中第一个为 null 的元素，该元素保存新节点
            if (nodes[i] == null) {
                // 创建新的节点，并用指定数组元素来保存它
                nodes[i] = new Node<>(data);
                // 子节点链为空时
                if (node.firstNode == null) {
                    node.firstNode = new SonNode(i, null);
                } else {
                    SonNode next = node.firstNode;
                    // 子节点链不为空时，循环至子节点链的最后一个节点
                    while (next.next != null) {
                        next = next.next;
                    }
                    next.next = new SonNode(i, null);
                }
                nodeNum++;
                return;
            }
        }
        throw new RuntimeException("该树已满，无法添加新的节点");
    }

    public boolean empty() {
        return nodes[0] == null;
    }

    public Node<E> root() {
        return nodes[0];
    }

    // 获得指定节点的所有子节点
    public List<Node<E>> children(Node parent) {
        List<Node<E>> list = new ArrayList<>();
        SonNode next = parent.firstNode;
        while (next != null) {
            list.add(nodes[next.nodeIndex]);
            next = next.next;
        }
        return list;
    }

    // 获得指定子节点的第 index 个子节点
    public Node<E> child(Node parent, int index) {
        SonNode sonNode = parent.firstNode;
        for (int i = 0; sonNode != null; i++) {
            if (index == i) {
                return nodes[sonNode.nodeIndex];
            }
            sonNode = sonNode.next;
        }
        return null;
    }

    // 获得树的深度
    public int deep() {
        return deep(root());
    }

    // 递归方法：每棵树的深度为其子树的最大深度 + 1
    private int deep(Node node) {
        if (node.firstNode == null) {
            return 1;
        } else {
            int max = 0;
            SonNode sonNode = node.firstNode;
            while (sonNode != null) {
                int tmp = deep(nodes[sonNode.nodeIndex]);
                if (tmp > max) {
                    max = tmp;
                }
                sonNode = sonNode.next;
            }
            return max + 1;
        }
    }


    public int pos(Node node) {
        for (int i = 0; i < treeSize; i++) {
            if (nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }
}
