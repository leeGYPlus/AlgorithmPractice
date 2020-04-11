package tree;


import java.util.Arrays;

/**
 * 二叉树的顺序
 */
public class ArrayBinTree<T> {
    // 使用数组来记录树的所有节点
    private Object[] datas;
    private int DEFAULT_DEEP = 8;
    // 记录该树的深度
    private int deep;
    private int arraySize;

    //使用默认深度构建二叉树
    public ArrayBinTree() {
        this.deep = DEFAULT_DEEP;
        initArrayBinTree(deep);
    }
    //使用指定深度创建二叉树
    public ArrayBinTree(int deep){
        this.deep = deep;
        initArrayBinTree(deep);
    }

    // 使用指定深度、指定根结点创建二叉树
    public ArrayBinTree(int deep,T data){
        this.deep = deep;
        initArrayBinTree(deep);
        datas[0] = data;
    }


    private void initArrayBinTree(int deep){
        // 性质：深度为 k 的二叉树，其节点数的总和最多为 2^k - 1
        this.arraySize = (int) (Math.pow(2, deep)) - 1;
        datas = new Object[arraySize];
    }

    /**
     * 为指定节点添加子节点
     * @param index 添加子节点的父节点的索引
     * @param data 新子节点的数据
     * @param left 是否为左子节点
     */
    public void add(int index,T data,boolean left){
        if(datas[index] == null){
            throw new RuntimeException(index + " 节点处为空，无法添加子节点");
        }
        // 这条判断是根据：在满二叉树中(编号为0~n)，最后一层的最后一个节点索引为该层的第一个节点的索引值的2倍，
        // 所以当2 * index + 1 >= arraySize，说明这个满二叉树的所有位置都已经填充了数据
        if(2 * index + 1 >= arraySize){
            throw new RuntimeException("树底层的数组已满，树越界异常");
        }
        if(left){
            datas[2* index + 1] = data;
        }else {
            datas[2* index + 2] = data;
        }
    }

    public boolean empty(){
        return datas[0] == null;
    }

    public T root(){
        return (T) datas[0];
    }

    // 返回指定节点（非根结点）的父节点
    public T parent(int index){
        return (T) datas[(index-1)/2];
    }

    // 返回指定节点的左子节点，当左子节点不存在时返回null
    public T left(int index){
        // 理由同上
        if(2 * index + 1 >= arraySize){
            throw new RuntimeException("该节点为叶子节点，无子节点");
        }
        return (T) datas[2 * index + 1];
    }

    // 返回指定节点的右子节点，当右子节点不存在时返回null
    public T right(int index){
        // 理由同上
        if(2 * index + 1 >= arraySize){
            throw new RuntimeException("该节点为叶子节点，无子节点");
        }
        return (T) datas[2 * index + 2];
    }

    public int deep(){
        return deep;
    }

    // 返回指定值的索引
    public int pos(T data){
        // 该循环实际上是按广度遍历来搜索每个节点
        for (int i = 0; i < arraySize; i++) {
            if (datas[i] == data)
                return i;
        }
        return -1;
    }

    public String toString(){
        return Arrays.toString(datas);
    }
}
