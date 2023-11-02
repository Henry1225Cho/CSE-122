import java.util.*;
// QUIZ CSE 122 debugging part 1
public class IsSorted {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();

        int[] nums = new int[]{5, 6, 7, 2, 4, 4, 9, 1}; // Feel free to edit these numbers to test your solution!
        for (int i = 0; i < nums.length; i++) {
            s.push(nums[i]);
        }

        System.out.println(s);
        System.out.println(isSorted(s));
        System.out.println(s);
    }

    /*
        This method returns true if the elements within each
        triplet in s are sorted in nondecreasing order from bottom to top, 
        and false otherwise. Elements at the top that do not form
        a complete triplet are ignored.
    */
    public static boolean isSorted(Stack<Integer> s) {
        Stack<Integer> aux = new Stack<>();

        sToS(s, aux);

        boolean sorted = true;
        int size = aux.size();
        for (int i = 0; i < size/3; i++) {
            int first = aux.pop();
            int second = aux.pop();
            int third = aux.pop();
            if (first > second || second > third) {
                sorted = false;
            }

            s.push(first);
            s.push(second);
            s.push(third);
        }

        sToS(aux, s);

        return sorted;
    }

    // Moves all elements from s1 to s2
    public static void sToS(Stack<Integer> s1, Stack<Integer> s2) {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }
}