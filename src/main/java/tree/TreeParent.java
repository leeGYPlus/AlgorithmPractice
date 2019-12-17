package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 父节点表示法：节点中维护了父节点的信息，本类中为父节点的索引值 parent
 * 用父节点表示法实现一棵树
 *
 */
public class TreeParent<E> {
    public static class Node<T> {
        T data;
        // 记录父节点位置
        int parent;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, int parent) {
            this.data = data;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "TreeParent$Node{" +
                    "data=" + data +
                    ", parent=" + parent +
                    '}';
        }
    }

    private final int DEFAULT_TREE_SIZE = 100;
    private int treeSize = 0;
    private Node<E>[] nodes;
    private int nodeNum;

    // 指定根结点创建树
    public TreeParent(E data) {
        treeSize = DEFAULT_TREE_SIZE;
        nodes = new Node[treeSize];
        nodes[0] = new Node<E>(data, -1);
        nodeNum++;
    }

    // 指定根结点和 treesize 创建树
    public TreeParent(E data, int treeSize) {
        this.treeSize = treeSize;
        nodes = new Node[treeSize];
        nodes[0] = new Node<E>(data, -1);
        nodeNum++;
    }

    // 为指定节点添加子节点
    public void addNode(E data, Node parent) {
        for (int i = 0; i < treeSize; i++) {
            // 找到数组中第一个为 null 的元素，该元素保存新节点
            if (nodes[i] == null) {
                nodes[i] = new Node(data, pos(parent));
                nodeNum++;
                return;
            }
        }
    }

    public boolean empty() {
        return nodes[0] == null;
    }

    public Node<E> root() {
        return nodes[0];
    }

    // 返回指定节点（非父节点）的父节点
    public Node<E> parent(Node node) {
        return nodes[node.parent];
    }

    // 返回指定节点的所有子节点
    public List<Node<E>> children(Node parent) {
        List<Node<E>> list = new ArrayList<>();
        for (int i = 0; i < treeSize; i++) {
            Node node = nodes[i];
            if (node != null && node.parent == pos(parent)) {
                list.add(node);
            }
        }
        return list;
    }

    // 返回树的深度
    public int deep() {
        int max = 0;
        for (int i = 0; i < treeSize && nodes[i] != null; i++) {
            int def = 1;
            int m = nodes[i].parent;
            // 如果父节点存在，则继续向上搜索父节点
            while (m != -1 && nodes[m] != null) {
                m = nodes[m].parent;
                def++;
            }
            if (max < def) {
                max = def;
            }
        }
        return max;
    }


    private int pos(Node node) {
        for (int i = 0; i < treeSize; i++) {
            if(nodes[i] == node){
                return i;
            }
        }
        return -1;
    }
}
