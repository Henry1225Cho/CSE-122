import java.util.*;
// CSE 122 Quiz part 1
public class ReverseRange {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();

        int[] nums = {0, 1, 2, 3, 4, 5}; // Feel free to modify these numbers to test your solution!
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }

        System.out.println("list before = " + list);
        reverseRange(list, 2, 4);
        System.out.println("list after  = " + list);
    }

    // TODO: write the reverseRange() method here
    public static void reverseRange(List<Integer> list, int low, int high) {
        if (low > high || low < 0 || high > list.size() - 1) {
            throw new IllegalArgumentException();
        }
        int lo = low;
        int hi = high;
        while (lo <= hi) {
            int temp = list.get(hi);
            list.set(hi, list.get(lo));
            list.set(lo, temp);
            lo++;
            hi--;
        }
    }
}