package algorithms.dynamic_programming.fibonacci;

/**
 * Given a stair with ‘n’ steps, implement a method to count how many possible ways are there to reach the top
 * of the staircase, given that, at every step you can either take 1 step, 2 steps, or 3 steps.
 *
 * Example 1:
 * Number of stairs (n) : 3
 * Number of ways = 4
 * Explanation: Following are the four ways we can climb : {1,1,1}, {1,2}, {2,1}, {3}
 *
 * Example 2:
 * Number of stairs (n) : 4
 * Number of ways = 7
 * Explanation: Following are the seven ways we can climb : {1,1,1,1}, {1,1,2}, {1,2,1}, {2,1,1},
 * {2,2}, {1,3}, {3,1}
 */
public class StairCase {

    /**
     * Approach 1: Brute force
     * Time Complexity: O(3^n)
     * Space Complexity: O(n)
     */
    private int countWaysApproach1(int n){
        return helper(n);
    }

    private int helper(int n){
        if(n < 0)
            return 0;
        if(n == 0)
            return 1;
        return helper(n-1) + helper(n-2) + helper(n-3);
    }

    /**
     * Approach 2: Memoization - Bottom up DP
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private int countWaysApproach2(int n){
        Integer[] cache = new Integer[n+1];
        return helper(n, cache);
    }

    private int helper(int n, Integer[] cache){
        if(n < 0)
            return 0;
        if(n == 0)
            return 1;
        if(cache[n]!=null)
            return cache[n];
        cache[n] =  helper(n-1, cache) + helper(n-2, cache) + helper(n-3, cache);
        return cache[n];
    }


    /**
     * Approach 3: Bottom up DP
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private int countWaysApproach3(int n){
        int[] table = new int[n+1];
        table[0] = 1;
        for(int i = 1; i<=n; i++){
            int ways = table[i-1];
            if(i>=2) ways+= table[i-2];
            if(i>=3) ways+= table[i-3];
            table[i] = ways;
        }
        return table[n];
    }

}
