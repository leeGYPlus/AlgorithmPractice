package sort;

public class InsertSort {
    private static void insertSort(int[] data) {
        System.out.println("start sort:" + Utils.show(data));
        int length = data.length;
        for (int i = 1; i < length; i++) {
            int tmp = data[i];
            // 当 i 索引处的值大于之前的元素，那么此时不需要排序
            // 插入排序法，i-1 索引前的数据已经是有序的，
            if (data[i] < data[i - 1]) {
                int j = i - 1;
                //当 data[j] > tmp 时，该索引元素值向后平移一个单位
                for (; j >= 0 &&  data[j] > tmp; j--) {
                    data[j + 1]  = data[j];
                }
                // 在不满足添加处，插入 tmp
                data[j+1] = tmp;
            }
        }
        System.out.println("end sort:" + Utils.show(data));
    }

    public static void main(String[] args) {
        int[] nums = {1,3,9,3,76,9,34,234,5,67};
        insertSort(nums);
    }
}
