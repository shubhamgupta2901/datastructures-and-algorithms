package algorithms.dynamic_programming.longest_common_substring;

/**
 * Given a number sequence, find the length of its Longest Increasing Subsequence (LIS). In an increasing subsequence,
 * all the elements are in increasing order (from lowest to highest).
 *
 * Example 1:
 * Input: {4,2,3,6,10,1,12}
 * Output: 5
 * Explanation: The LIS is {2,3,6,10,12}.
 *
 * Example 2:
 * Input: {-4,10,3,7,15}
 * Output: 4
 * Explanation: The LIS is {-4,3,7,15}.
 */
public class LongestIncreasingSubsequence {
    /**
     * Approach 1: Brute Force
     * A basic brute-force solution could be to try all the subsequences of the given number sequence.
     * We can process one number at a time, so we have two options at any step:
     *  1. If the current number is greater than the previous number that we included, we can increment our count and
     *  make a recursive call for the remaining array.
     *  2.We can skip the current number to make a recursive call for the remaining array.
     * The length of the longest increasing subsequence will be the maximum number returned by the two recurse calls
     * from the above two options.
     * Time Complexity: O(2^n)
     * Space Compelxity: O(n)
     */
    public int lengthOfLISApproach1(int[] nums) {
        return helper(nums, -1,0);
    }
    private int helper(int[] nums, int prevIndex, int currentIndex){
        if(currentIndex == nums.length)
            return 0;
        int lis1 = 0;
        if(prevIndex == -1 || nums[currentIndex] > nums[prevIndex])
            lis1 = 1 + helper(nums, currentIndex, currentIndex+1);
        int lis2 = helper(nums, prevIndex, currentIndex+1);
        return Math.max(lis1, lis2);
    }
}
