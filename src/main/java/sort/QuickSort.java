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
                //自左向右，找比中间值小的值
                while (i < end && baseNum >= nums[++i]);
                // 自右向左，找比中间值大的值
                while (j > start && baseNum <= nums[--j]);
                if(i < j){
                    swap(nums,i,j);
                }else {
                    break;
                }
            }
            System.out.println("中间one:" + Uitls.show(nums) + "，start =" + start + ",j = " + j);
            swap(nums,start,j);
            System.out.println("中间two:" + Uitls.show(nums));
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
        int[] nums = {7,9,3,4,7,8};
        System.out.println("start sort：" + Uitls.show(nums));
        quickSort(nums);
        System.out.println("end sort" + Uitls.show(nums));
    }
}
