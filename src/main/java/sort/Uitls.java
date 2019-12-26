package sort;

public class Uitls {
    public static String show(int[] nums) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int num : nums) {
            stringBuilder.append(num).append(",");
        }
        return stringBuilder.toString();
    }

}
