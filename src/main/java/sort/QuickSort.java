package sort;

public class QuickSort {
    private static void quickSort(int[] nums){
        subSort(nums,0,nums.length -1);
    }

    private static void subSort(int[] nums, int start, int end) {
        // 需要排序
        if(start < end){
            // 以第一个元素为分界值,这里需要注意的是是以第一个索引处的值为中间值，不是以第一个索引为中间索引
            int baseNum = nums[start];
            // i 从左边开始搜索，搜索大于分界值的元素的索引
            int i = start;
            // j 从右边开始搜索，搜索小于分界值的元素的索引
            int j = end + 1;
            while (true){
                //自左向右，找比中间值大的值
                while (i < end && baseNum >= nums[++i]);
                // 自右向左，找比中间值小的值
                while (j > start && baseNum <= nums[--j]);
                // 左侧的元素大于右侧的元素，则进行数据交换
                if(i < j){
                    swap(nums,i,j);
                }else {
                    System.out.println("i = " + i  + ",j = " + j);
                    System.out.println("break");
                    break;
                }
            }
            // 现在数组中元素的大小情况：start < m <=j 索引出的元素值均为 小于或等于 中间值的值，m > j 处的元素值均为 大于或等于 中间值的值，
            // 所以此时交换 start 与 j 处的元素后，可以以中间值为分水岭，将数组分为两个子序列，然后进行接下来的递归排序
            System.out.println("end: " + Utils.show(nums) );
            swap(nums,start,j);
            System.out.println("end: " + Utils.show(nums) );
            subSort(nums,start,j);
            subSort(nums,j + 1,end);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {7,3,9,10,4,7,8,9,12,1,54,1,4,5,7,9};
        System.out.println("start sort：" + Utils.show(nums));
        quickSort(nums);
        System.out.println("end sort" + Utils.show(nums));
    }
}
