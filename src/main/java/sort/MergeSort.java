package sort;

public class MergeSort {
    private static void mergeSort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            sort(nums, left, center);
            sort(nums, center + 1, right);
            merge(nums, left, center, right);
        }
    }

    /**
     * 将两个数组进行合并，归并前两个数组已经有序，归并后依旧有序
     * 最小的两个子数组为：两个相邻的元素
     */
    private static void merge(int[] nums, int left, int center, int right) {
        System.out.println("left=" + left + ",center=" + center + ",right=" + right);
        int[] tmpNums = new int[nums.length];
        int mid = center + 1;
        // third 记录中间数组的索引
        int third = left;
        // 记录子数组开始的索引
        int tmp = left;
        while (left <= center && mid <= right) {
            // 从两数组中取出较小的值放入中间数组中
            if (nums[left] < nums[mid]) {
                tmpNums[third++] = nums[left++];
            } else {
                tmpNums[third++] = nums[mid++];
            }
        }
        // 将剩余部分依次放入中间数组中
        while (mid <= right) {
            tmpNums[third++] = nums[mid++];
        }
        while (left <= center) {
            tmpNums[third++] = nums[left++];
        }
        // 将中间数组中的内容拷贝到原数组中
        while (tmp <= right) {
            nums[tmp] = tmpNums[tmp++];
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 9, 3, 76, 9, 34, 234, 5, 67, 0};
        System.out.println("start sort:" + Utils.show(nums));
        mergeSort(nums);
        System.out.println("end sort:" + Utils.show(nums));

    }
}
