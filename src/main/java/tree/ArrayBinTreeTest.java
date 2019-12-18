package tree;

public class ArrayBinTreeTest {
    public static void main(String[] args) {
        ArrayBinTree binTree = new ArrayBinTree(4,"根");
        binTree.add(0,"第二层右结点",false);
        binTree.add(2,"第三层右结点",false);
        binTree.add(6,"第四层右结点",false);
        System.out.println(binTree);

    }
}
