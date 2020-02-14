package leetcode;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * Example:
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 *
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {

    /**
     * Approach 1: DP with memoization
     * 200/202 cases passed. Time limit exceeds for larger arrays.
     * The brute force idea was to calculate the sum of all subarrays and return the maximum sum.
     * I have also memoized it.
     *
     */
    public int maxSubArray(int[] nums) {
        int length = nums.length;
        if(length == 0)
            return Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i< length; i++)
            sum += nums[i];
        Integer[][] cache = new Integer[length][length];
        return helper(nums, 0,length-1, sum, cache);
    }

    private int helper(int[] nums, int start, int end, int sum, Integer[][] cache){
        //base case
        if(start == end)
            return nums[start];
        if(cache[start][end]!= null)
            return cache[start][end];
        //recurrence
        int subArrSum1 = helper(nums, start+1, end, sum-nums[start], cache);
        int subArrSum2 = helper(nums, start, end-1, sum-nums[end], cache);

        //return
        cache[start][end] =  Math.max(sum, Math.max(subArrSum1, subArrSum2));
        return cache[start][end];
    }
}
