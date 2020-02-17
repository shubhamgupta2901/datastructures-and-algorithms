package algorithms.dynamic_programming.longest_common_substring;

/**
 * Given a number sequence, find the increasing subsequence with the highest sum. Write a method that
 * returns the highest sum.
 *
 * Example 1:
 * Input: {4,1,2,6,10,1,12}
 * Output: 32
 * Explanation: The increaseing sequence is {4,6,10,12}.
 * Please note the difference, as the LIS is {1,2,6,10,12} which has a sum of '31'.
 *
 * Example 2:
 * Input: {-4,10,3,7,15}
 * Output: 25
 * Explanation: The increasing sequences are {10, 15} and {3,7,15}.
 */
public class MaximumSumIncreasingSubsequence {

    /**
     * Approach 1: Brute force
     * Time Complexity: O(2^n)
     * Space Complexity: O(n)
     */
    private int maxIncreasingSubsequence(int []nums){
        return helper(nums,-1, 0 );
    }

    private int helper(int[] nums, int prevIndex, int currentIndex){
        if(currentIndex == nums.length)
            return 0;
        int subsequence1 = 0;
        if(prevIndex == -1 || nums[prevIndex]<nums[currentIndex])
            subsequence1 = nums[currentIndex] + helper(nums,currentIndex, currentIndex+1);
        int subsequence2 = helper(nums, prevIndex, currentIndex+1);
        return Math.max(subsequence1, subsequence2);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-4,10,3,7,15};
        int[] nums2 = new int[]{4,1,2,6,10,1,12};
        int max = new MaximumSumIncreasingSubsequence().maxIncreasingSubsequence(nums2);
        System.out.println(max);
    }

}
