package sort;

public class Utils {
    public static String show(int[] nums) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int num : nums) {
            stringBuilder.append(num).append(",");
        }
        return stringBuilder.toString();
    }

}
