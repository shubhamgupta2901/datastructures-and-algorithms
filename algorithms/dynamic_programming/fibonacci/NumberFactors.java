package algorithms.dynamic_programming.fibonacci;

/**
 * Given a number ‘n’, implement a method to count how many possible ways
 * there are to express ‘n’ as the sum of 1, 3, or 4.
 *
 * Example 1:
 * n : 4
 * Number of ways = 4
 * Explanation: Following are the four ways we can express 'n' : {1,1,1,1}, {1,3}, {3,1}, {4}
 *
 * Example 2:
 * n : 5
 * Number of ways = 6
 * Explanation: Following are the six ways we can express 'n' : {1,1,1,1,1}, {1,1,3}, {1,3,1}, {3,1,1},
 * {1,4}, {4,1}
 */
public class NumberFactors {
    /**
     * Approach 1 - Rejected.
     * My approach was to create an array with numbers 1,3,4 , and the idea was
     *
     * for each factor 'c' in numbers
     *   create a new set which includes one quantity of numbers 'c' if it does not exceed 'n', and
     *      recursively call to process all factors with reduced n.
     *   create a new set without number 'c', and recursively call to process the remaining numbers
     * return the count of sets who have a sum equal to 'n'
     *
     * Time Complexity: O(2^(n+3)) ~ O(2^n) where 3 is the size of array numbers.
     * Space Complexity: O(n+3) ~ O(n)
     *
     * The problem with this approach is
     * for input : 4
     * This algorithm would print {1,3} once, but the answer expects both {1,3} and {3,1}
     * For input : 5
     * This algorithm returns {1,1,3} once but the expected output has {1,1,3}, {1,3,1}, {3,1,1}
     */
    private int countWays(int n){
        int[] numbers = new int[]{1,3,4};
        return helper(numbers, n, 0);
    }

    private int helper(int[] numbers, int n, int index){
        if(n == 0)
            return 1;
        if(index == numbers.length)
            return 0;
        int ways1 = helper(numbers, n, index+1);
        int ways2 = 0;
        if(n>=numbers[index])
            ways2 = helper(numbers, n-numbers[index], index);
        return ways1 + ways2;
    }

    public static void main(String[] args) {
        NumberFactors en = new NumberFactors();
        System.out.println(en.countWays(4));
        System.out.println(en.countWays(5));
        System.out.println(en.countWays(6));
    }
}
