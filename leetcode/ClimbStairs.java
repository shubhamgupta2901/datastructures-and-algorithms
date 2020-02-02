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
    public int climbStairsApproach1(int n) {
        if( n == 0)
            return 1;
        int ways = climbStairsApproach1(n-1);
        if(n-2>=0)
            ways+=climbStairsApproach1(n-2);
        return ways;
    }

    /**
     * Approach 2: Top down memoization - Accepted
     * Time Complexity: ? Certainly not O(2^n)
     * because a lot of overlapping subproblems are cached in array.
     * Runtime percentile is 100%
     * I am inclined to say O(n) because if we look into the recursion tree,
     * once we go till the height of tree, or till we encounter our first base case,
     * we have solved all the subproblems and saved the results in the array.
     * After that every subproblem we encounter, are overlapping and their
     * results are already present in array
     * But need to check what exactly is asymptotic time complexity.
     * Space Complexity: O(n) cache array and recursive stack.
     * Memory usage is ~5 percentile.
     */
    public int climbStairsApproach2(int n) {
        Integer[] cache = new Integer[n+1];
        return helper(n, cache);
    }

    private int helper(int n, Integer[] cache){
        if(n == 0)
            return 1;
        if(cache[n]!=null)
            return cache[n];
        int ways = helper(n-1, cache);
        if(n-2>=0)
            ways+= helper(n-2, cache);
        cache[n] = ways;
        return ways;
    }

    /**
     * Approach 3: Bottom up Tabulation.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int climbStairsApproach3(int n) {
        int[] table = new int[n+1];
        table[0] = table[1] = 1;
        for(int i = 2;i<=n ;i++)
            table[i] = table[i-1]+table[i-2];
        return table[n];
    }
}
