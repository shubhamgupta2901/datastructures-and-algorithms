package leetcode;

/**
 * <a href="https://leetcode.com/problems/climbing-stairs/"/>
 *
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Example 2:
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class ClimbStairs {

    /**
     * Approach 1: Brute force. - Time limit exceeds
     * Time complexity: O(2^n)
     * Space Complexity: O(n) because at any stage
     * there can be at max n numbers in recursive stack
     */
    public int climbStairs(int n) {
        if( n == 0)
            return 1;
        int ways = climbStairs(n-1);
        if(n-2>=0)
            ways+=climbStairs(n-2);
        return ways;
    }
}
