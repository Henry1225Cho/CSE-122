import java.util.*;
//CSE 122 quiz debugging part 2
public class Multiply {
    public static void main(String[] args) {
        int[][] nums = multiplicationTable(3, 5); // Feel free to change this line to test your solution!
        
        // The below code prints out the 2D array as a table
        // You shouldn't need to change this
        System.out.println(Arrays.deepToString(nums).replace("], ", "]\n"));
    }

    public static int[][] multiplicationTable(int x, int y) {
        int[][] nums = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                nums[x - 1 - i][j] = (i + 1)*(j + 1);
            }
        }
        return nums;
    }
}