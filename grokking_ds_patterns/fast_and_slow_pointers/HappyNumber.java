package grokking_ds_patterns.fast_and_slow_pointers;

import java.util.HashSet;

/**
 * Any number will be called a happy number if, after repeatedly replacing it with a number equal to the sum of the square of all of its digits, leads us to number ‘1’. All other (not-happy) numbers will never reach ‘1’. Instead, they will be stuck in a cycle of numbers which does not include ‘1’.
 *
 * Example 1:
 * Input: 23
 * Output: true (23 is a happy number)
 * Explanations: Here are the steps to find out that 23 is a happy number:
 * 1. 2^2 + 3 ^2 = 13
 * 2. 1^2 + 3^2 = 10
 * 3. 1^2 + 0^2 = 1
 *
 * Example 2:
 * Input: 12
 * Output: false (12 is not a happy number)
 * Explanations: Here are the steps to find out that 12 is not a happy number:
 * 1.  1^2 + 2 ^2 = 5
 * 2.  5^2 = 25
 * 3.  2^2 + 5^2= 29
 * 4.  2^2 + 9^2 = 85
 * 5.  8^2 + 5^2= 89
 * 6.  8^2 + 9^2= 145
 * 7.  1^2 + 4^2 + 5^2 = 42
 * 8.  4^2 + 2^2 = 20
 * 9.  2^2 + 0^2 = 4
 * 10. 4^2 = 16
 * 11. 1^2 + 6^2= 37
 * 12. 3^2 + 7^2 = 58
 * 13. 5^2 + 8^2 = 89
 * Step ‘13’ leads us back to step ‘5’ as the number becomes equal to ‘89’, this means that we can never reach ‘1’,
 * therefore, ‘12’ is not a happy number.
 */
public class HappyNumber {
    /**
     * Approach 1: Using hashset to determine cycle
     * Time Complexity: Difficult to determine
     * Space complexity: O(n)
     */
    public boolean isHappy(int n) {
        HashSet<Integer> hashSet = new HashSet<Integer>();
        int sum = n;
        while(true){
            if(sum == 1)
                return true;
            if(hashSet.contains(sum))
                return false;
            hashSet.add(sum);
            sum = sumOfSquares(sum);
        }
    }

    private int sumOfSquares(int num){
        int sum = 0;
        while(num>0){
            sum += Math.pow(num % 10,2);
            num = num/10;
        }
        return sum;
    }

    /**
     * Approach 2: Slow and fast pointer approach
     * Time Complexity: Difficult to determine
     * Space Complexity: O(1)
     */
    private boolean isHappyApproach2(int n){
        int slow = n, fast = n;
        while(sumOfSquares(fast) != 1 && sumOfSquares(sumOfSquares(fast))!=1){
            slow = sumOfSquares(slow);
            fast = sumOfSquares(sumOfSquares(fast));

            if(slow == fast)
                return true;
        }

        return false;
    }

    /**
     * Approach 3: Optimising sumofSquares call in Approach2
     * Time Complexity: Difficult to determine
     * Space Complexity: O(1)
     */
    private boolean isHappyApproach3(int n){
     int slow = n , fast = n;
     while (true){
         slow = sumOfSquares(slow);
         fast = sumOfSquares(sumOfSquares(fast));

         if(slow == 1 || fast == 1)
             return true;

         if(slow == fast)
             return false;
     }
    }

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();
        System.out.println(hn.isHappy(19));
        System.out.println(hn.isHappy(12));
    }

}
