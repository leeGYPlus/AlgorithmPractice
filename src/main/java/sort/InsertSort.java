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
                //前 i-1 个元素，当 data[j] > tmp 时，该索引元素值向后平移一个单位
                for (; j >= 0 && data[j] > tmp; j--) {
                    data[j + 1] = data[j];
                }
                // 在不满足添加处，插入 tmp
                data[j + 1] = tmp;
            }
        }
        System.out.println("end sort:" + Utils.show(data));
    }


    private static void binaryInsertSort(int[] data) {
        System.out.println("start sort:" + Utils.show(data));
        int length = data.length;
        // 将第i 个元素插入到前 i -1 个有序序列中
        for (int i = 1; i < length; i++) {
            int tmp = data[i];
            int low = 0;
            // 此时，前 i-1 个值是有序的
            int high = i - 1;
            // 找到与第 i 个元素大小最相近的值的索引
            while (low <= high) {
                int mid = (high + low) / 2;
                // tmp 大于前 i-1 个序列的中间值
                if (tmp > data[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            // 将 low 到 i 处的所有元素都向后整体平移一位
            for (int j = i; j > low; j--) {
                data[j] = data[j - 1];
            }
            data[low] = tmp;
        }
    }

    /**
     * 直接插入排序时，当最右侧的元素最小时，需要进行 N 次复制的情况，从而导致排序效率低。
     * Shell 排序，通过增大排序间隔，避免了以上问题。
     * 间隔值为 h ，通过 h 的不断减小，
     */
    private static void shellSort(int[] nums) {
        System.out.println("Shell Sort Start：" + Utils.show(nums));
        int length = nums.length;
        // h 变量保存可变增量
        int h = 1;
        // 按 h * 3 + 1 的规则得到增长序列的最大值
        while (h <= length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            System.out.println("h 的值是：" + h);
            for (int i = h; i < length; i++) {
                int tmp = nums[i];
                // tmp 小于 datas[i-h]个元素，那么现在需要做的是将 tmp 插入到之前的有序序列中
                if (tmp < nums[i - h]) {
                    int j = i - h;
                    // 整体向后平移 h 个格
                    for (; j >= 0 && nums[j] > tmp; j -= h) {
                        nums[j + h] = nums[j];
                    }
                    nums[j + h] = tmp;
                }
                System.out.println("此时的序列为：" + Utils.show(nums));
            }
            h = (h - 1) / 3;
        }
        System.out.println("Shell Sort End:" + Utils.show(nums));
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 9, 3, 76, 9, 34, 234, 5, 67,0};
//        insertSort(nums);
//        binaryInsertSort(nums);
        shellSort(nums);
    }
}
