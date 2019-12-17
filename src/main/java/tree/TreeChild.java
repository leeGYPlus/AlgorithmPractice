package tree;


import java.util.ArrayList;
import java.util.List;

/**
 * 子节点链表示法：每个父节点记住它所有的子节点，在本例中标记为子节点链
 * 使用子节点链表示法来表示一棵树
 */
public class TreeChild<E> {

    // 节点的子链元素
    private static class SonNode {
        // 记录当前节点的位置
        private int pos;
        private SonNode next;

        public  SonNode(int pos, SonNode next) {
            this.pos = pos;
            this.next = next;
        }
    }

    // 树的节点元素
    public static class Node<T> {
        T data;
        // 记录第一个子节点
        SonNode first;

        public Node(T data) {
            this.data = data;
            this.first = null;
        }

        @Override
        public String toString() {

            if (first != null) {
                return "TreeChild$Node{" +
                        "data=" + data +
                        ", first=" + first.pos +
                        '}';
            } else {
                return "TreeChild$Node{" +
                        "data=" + data +
                        ", first= -1" +
                        '}';
            }
        }
    }

    private final int DEFAULT_TREE_SIZE = 100;
    private int treeSize = 0;
    private Node<E>[] nodes;
    // 记录节点数
    private int nodeNum;

    // 以指定根结点创建树
    public TreeChild(E data) {
        treeSize = DEFAULT_TREE_SIZE;
        nodes = new Node[treeSize];
        nodes[0] = new Node<>(data);
        nodeNum++;
    }

    public TreeChild(E data, int treeSize) {
        this.treeSize = treeSize;
        nodes = new Node[treeSize];
        nodes[0] = new Node<>(data);
        nodeNum++;
    }

    // 为指定节点添加子节点
    public void addNode(E data, Node parent) {
        for (int i = 0; i < treeSize; i++) {
            // 找到数组中第一个为 null 的元素，该元素保存新节点
            if (nodes[i] == null) {
                // 创建新的节点，并用指定数组元素来保存它
                nodes[i] = new Node<>(data);
                // 子节点链为空时
                if (parent.first == null) {
                    parent.first = new SonNode(i, null);
                } else {
                    SonNode next = parent.first;
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
        SonNode next = parent.first;
        while (next != null) {
            list.add(nodes[next.pos]);
            next = next.next;
        }
        return list;
    }

    // 获得指定子节点的第 index 个子节点
    public Node<E> child(Node parent, int index) {
        SonNode next = parent.first;
        for (int i = 0; next != null; i++) {
            if (index == i) {
                return nodes[next.pos];
            }
            next = next.next;
        }
        return null;
    }

    // 获得树的深度
    public int deep() {
        return deep(root());
    }

    // 递归方法：每棵树的深度为其子树的最大深度 + 1
    private int deep(Node node) {
        if (node.first == null) {
            return 1;
        } else {
            int max = 0;
            SonNode next = node.first;
            while (next != null) {
                int tmp = deep(nodes[next.pos]);
                if (tmp > max) {
                    max = tmp;
                }
                next = next.next;
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
