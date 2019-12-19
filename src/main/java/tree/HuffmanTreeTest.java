package tree;

import java.util.ArrayList;
import java.util.List;
import tree.HuffmanTree.Node;

public class HuffmanTreeTest {

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("A",40.0));
        nodes.add(new Node("B",8.0));
        nodes.add(new Node("C",10.0));
        nodes.add(new Node("D",30.0));
        nodes.add(new Node("E",10.0));
        nodes.add(new Node("F",20.0));
        HuffmanTree huffmanTree = new HuffmanTree();
        Node root = huffmanTree.createTree(nodes);
        System.out.println(huffmanTree.breathFirst(root));
    }

}
