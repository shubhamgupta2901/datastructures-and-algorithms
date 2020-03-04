package leetcode;

/**
 * <a href="https://leetcode.com/problems/minimum-size-subarray-sum/"/>
 * Given an array of n positive integers and a positive integer s, find the minimal length of a
 * contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 *
 * Example:
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 *
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */
public class MinimumSizeSubarraySum {
    /**
     * Approach 1: Brute Force approach - Time limit exceeds
     * Check all the possible contigious subarrays, for which the sum sum >= s.
     * Time Complexity: O(2^n)
     * Space Complexity: O(1)
     */
    public int minSubArrayLen1(int s, int[] nums) {
        int length = nums.length;
        int sum = 0;
        for(int i = 0; i<length; i++)
            sum+= nums[i];
        return helper(nums, 0, length-1, s, sum);
    }

    private int helper(int[] nums, int start, int end, int target, int sum){
        //base case:
        if(start > end || sum < target)
            return Integer.MAX_VALUE;
        //recursion
        int size1 = helper(nums,start+1,end,target, sum-nums[start]);
        int size2 = helper(nums, start, end-1, target, sum-nums[end]);
        int size3 = end-start+1;

        //return statement
        return Math.min(Math.min(size1, size2),size3);
    }

    /**
     * Approach 2: Dynamic Programming Memoization - Memory Limit Exceeds
     * Memoizing the solution using a 2D array to save the results of subproblems.
     * Time Complexity: O(n*n)
     * Space Complexity: O(n*n)
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int length = nums.length;
        int sum = 0;
        for(int i = 0; i<length; i++)
            sum+= nums[i];
        Integer[][] cache = new Integer[length][length];
        int minSize =  helper(nums, 0, length-1, s, sum, cache);
        return minSize == Integer.MAX_VALUE ? 0 : minSize;
    }
    private int helper(int[] nums, int start, int end, int target, int sum, Integer[][] cache){
        //base case:
        if(start > end || sum < target)
            return Integer.MAX_VALUE;
        //checking value via memoization
        if(cache[start][end]!=null)
            return cache[start][end];
        //recursion
        int size1 = helper(nums,start+1,end,target, sum-nums[start], cache);
        int size2 = helper(nums, start, end-1, target, sum-nums[end], cache);
        int size3 = end-start+1;

        //memoization
        cache[start][end] = Math.min(Math.min(size1, size2),size3);
        //return statement
        return cache[start][end];
    }


}
