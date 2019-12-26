package tree;

public class SortedBinTree<T extends Comparable> {
    static class Node{
        Object data;
        Node parent;
        Node left;
        Node right;

        public Node(Object data, Node parent, Node left, Node right) {
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj){
                return true;
            }
            if(obj.getClass() == Node.class){
                Node target = (Node) obj;
                return data.equals(target.data) && left == target.left && right == target.right;
            }
            return false;
        }
    }

    private Node root;

    public SortedBinTree(Node root) {
        this.root = null;
    }


    public SortedBinTree(T object) {
        root = new Node(object,null,null,null);
    }

    public void add(T element){
        if (root == null){
            root = new Node(element,null,null,null);
        }else {
            Node current = root;
            Node parent = null;
            int cmp = 0;
            do{
                parent = current;
                cmp  = element.compareTo(current.data);
                if(cmp > 0){
                    current = current.right;
                }else {
                    current = current.left;
                }
            }while (current!=null);

            Node newNode = new Node(element,parent,null,null);
        }
    }
}
