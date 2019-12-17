package tree;

import java.util.List;

public class TreeParentTest {
    public static void main(String[] args) {
        TreeParent<String> treeParent = new TreeParent<>("root");
        TreeParent.Node root = treeParent.root();
        System.out.println(root);
        treeParent.addNode("节点1",root);
        System.out.println("此树的深度：" + treeParent.deep());
        treeParent.addNode("节点2",root);
        List<TreeParent.Node<String>> list = treeParent.children(root);
        System.out.println("根节点的第一个子节点：" + list.get(0));
        treeParent.addNode("节点3",list.get(0));
        System.out.println("此树的深度：" + treeParent.deep());
    }
}
