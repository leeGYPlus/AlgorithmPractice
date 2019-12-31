package sort;

public class BubbleSort {

    public static void bubbleSort(int[] nums){
        System.out.println("遍历开始：" + Utils.show(nums));
        int length = nums.length;
        for (int i = 0;i < length -1;i++){
            for (int j = 0; j < length - i - 1; j++) {
                if(nums[j] > nums[j + 1]){
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+ 1] = tmp;
                }
            }
            System.out.println("第" + (i+1) + "次遍历后的结果：" + Utils.show(nums));
        }
        System.out.println("遍历结束：" + Utils.show(nums));
    }

    public static void betterBubbleSort(int[] nums){
        System.out.println("遍历开始：" + Utils.show(nums));
        int length = nums.length;
        for (int i = 0;i < length -1;i++){
            boolean flag = false;
            for (int j = 0; j < length - i - 1; j++) {
                if(nums[j] > nums[j + 1]){
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+ 1] = tmp;
                    flag = true;
                }
            }
            System.out.println("第" + (i+1) + "次遍历后的结果：" + Utils.show(nums));

            // 如果某次遍历没有发生交换动作，说明已经处于有序状态，如果 4，1，3，5，6，7 在第3次遍历后，
            // 数组为 1，3，4，5，6，7，那么在第 4 次遍历就不会发生数据交换，可以此时已为有序序列，不用接下来的循环
            if(!flag){
                break;
            }
        }
        System.out.println("遍历结束：" + Utils.show(nums));
    }

    public static void main(String[] args) {
        int[] nums = {5,4,1,3,5,6,7};
        bubbleSort(nums);
        System.out.println("------");
        int[] nums2 = {5,4,1,3,5,6,7};
        betterBubbleSort(nums2);
    }
}
