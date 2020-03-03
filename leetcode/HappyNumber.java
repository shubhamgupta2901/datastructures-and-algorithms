package leetcode;


import java.util.HashSet;

/**
 * <a href="https://leetcode.com/problems/happy-number/"/>
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process: Starting with any positive integer,
 * replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1
 * (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy numbers.
 *
 * Example:
 *
 * Input: 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
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
}

