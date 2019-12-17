package tree;

import java.util.List;

public class TreeChildTest {
    public static void main(String[] args) {
        TreeChild<String> treeChild = new TreeChild<>("root");
        TreeChild.Node root = treeChild.root();
        System.out.println("根结点为：" + root);
        treeChild.addNode("节点1",root);
        treeChild.addNode("节点2",root);
        treeChild.addNode("节点3",root);
        System.out.println("添加子节点后的根结点：" + root);
        System.out.println("此树的深度：" + treeChild.deep());
        List<TreeChild.Node<String>> nodes = treeChild.children(root);
        System.out.println("根结点的第一个子节点：" + nodes.get(0));
        // 为根结点的第一个子节点新增一个子节点
        treeChild.addNode("节点4",nodes.get(0));
        System.out.println("根结点的第一个子节点为：" + nodes.get(0));
        System.out.println("此树的深度：" + treeChild.deep());

    }
}
