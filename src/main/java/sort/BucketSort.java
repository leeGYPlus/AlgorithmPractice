package sort;

public class BucketSort {

    private static void bucketSort(int[] nums, int min, int max) {
        int length = nums.length;
        int[] tmpArray = new int[length];
        // bucket 数组相当于定义了 max-min 个桶,同时 bucket 数组用于记录待排序的元素信息
        int[] buckets = new int[max - min];
        for (int i = 0; i < length; i++) {
            // bucket记录了每个元素出现的次数
            buckets[nums[i] - min]++;
        }
        System.out.println("buckets：" + Utils.show(buckets));
        // 计算落入各个桶内的元素在有序序列中的位置
        for (int i = 1; i < max - min; i++) {
            buckets[i] = buckets[i] + buckets[i - 1];
        }
    }
}
