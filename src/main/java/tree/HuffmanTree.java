package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class HuffmanTree {
    public static class Node<E> {
        E data;
        double weight;
        Node leftChild;
        Node rightChild;

        public Node(E data, double weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", weight=" + weight +
                    '}';
        }
    }

    public Node createTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            quickSort(nodes);
            //获取权值最小的两个节点
            Node left = nodes.get(nodes.size() - 1);
            Node right = nodes.get(nodes.size() - 2);
            Node parent = new Node(null, left.weight + right.weight);
            parent.leftChild = left;
            parent.rightChild = right;
            // 移除集合中权值最小的两个节点
            nodes.remove(nodes.size() - 1);
            nodes.remove(nodes.size() - 1);
            // 将新生成的节点添加到集合中
            nodes.add(parent);
        }
        // 返回树的根节点
        return nodes.get(0);
    }

    private void quickSort(List<Node> nodes) {
        subSort(nodes, 0, nodes.size() - 1);
    }

    // 广度优先遍历
    public List<Node> breathFirst(Node root) {
        Queue<Node> queue = new ArrayDeque<>();
        List<Node> list = new ArrayList<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            list.add(queue.peek());
            Node node = queue.poll();
            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.offer(node.rightChild);
            }
        }

        return list;
    }

    //  快速排序算法，对节点进行排序
    private void subSort(List<Node> nodes, int start, int end) {
        if (end > start) {
            // 以第一个元素为分界值
            Node base = nodes.get(start);
            int i = start;
            int j = end + 1;
            while (true) {
                // 找到大于分界值的元素的索引，或者 i 已经到了 end 处
                while (i < end && nodes.get(++i).weight >= base.weight) ;
                // 找到小于分界值的元素的索引，或者j 已经到了 start 处
                while (j > start && nodes.get(--j).weight <= base.weight) ;
                if (i < j) {
                    swap(nodes, i, j);
                } else {
                    break;
                }
            }
            swap(nodes, start, j);
            // 递归左边子序列
            subSort(nodes, start, j - 1);
            // 递归右边子序列
            subSort(nodes, j + 1, end);
        }
    }

    private void swap(List<Node> nodes, int i, int j) {
        Node tmp;
        tmp = nodes.get(i);
        nodes.set(i, nodes.get(j));
        nodes.set(j, tmp);
    }
}
