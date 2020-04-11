package sort;


// 直接选择排序 时间复杂度为 O(n*n)，空间复杂度为 O(1)
public class SelectSort {
    // 直接选择排序
    public static void sort(int[] nums) {
        System.out.println("开始的排序：" + show(nums));
        System.out.println("start sort");
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (nums[i] > nums[j]) {
                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                }
            }
            System.out.println(show(nums));
        }
        System.out.println("结束排序后：" + show(nums));
    }

    /**
     *
     * 改进后的直接选择排序
     *
     * 既然是升序，那么当 3478321 时，第一次遍历会得到中间序列为 2478331、1478332，该过程经过了两次数字交互过程，
     * 如果在遍历过程中先记住最小值的索引，然后在遍历结束后，比较自己与除自己外最小值的索引是否相同，
     * 如果不同，则进行数据交互，得到了 1478332，这个过程仅经过了一次数据交换，性能更优。
     *
     * @param nums
     */

    public static void sortBetter(int[] nums) {
        System.out.println("开始的排序：" + show(nums));
        System.out.println("start sort");
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (nums[minIndex] > nums[j]) {
                    // 得到剩下的数据的中最小值的索引
                    minIndex = j;
                }
            }
            // 如果当前标准值的索引与剩余值中最小值的索引不同，那么交换两者
            if (minIndex != i) {
                int tmp = nums[minIndex];
                nums[minIndex] = nums[i];
                nums[i] = tmp;
            }
            System.out.println(show(nums));
        }
        System.out.println("结束排序后：" + show(nums));
    }


    public static void main(String[] args) {
        int[] nums = {12, 40, 1, 7, 34, 23, 0};
        sort(nums);
        System.out.println("----");
        int[] nums2 = {12, 40, 1, 7, 34, 23, 0};
        sortBetter(nums2);
    }

    private static String show(int[] nums) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int num : nums) {
            stringBuilder.append(num).append(",");
        }
        return stringBuilder.toString();
    }
}
