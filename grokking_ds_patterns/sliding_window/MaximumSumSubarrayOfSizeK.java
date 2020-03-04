package grokking_ds_patterns.sliding_window;

/**
 * Given an array of positive numbers and a positive number ‘k’,
 * find the maximum sum of any contiguous subarray of size ‘k’.
 *
 * Example 1:
 * Input: [2, 1, 5, 1, 3, 2], k=3
 * Output: 9
 * Explanation: Subarray with maximum sum is [5, 1, 3].
 *
 * Example 2:
 * Input: [2, 3, 4, 1, 5], k=2
 * Output: 7
 * Explanation: Subarray with maximum sum is [3, 4].
 */
public class MaximumSumSubarrayOfSizeK {

    /**
     * Approach : Sliding Window
     * Consider each subarray as a Sliding Window of size ‘k’.
     * To calculate the sum of the next subarray, we need to slide the window ahead by one element.
     * So to slide the window forward and calculate the sum of the new position of the sliding window,
     * we need to do two things:
     *  1.Subtract the element going out of the sliding window i.e., subtract the first element of the window.
     *  2.Add the new element getting included in the sliding window i.e.,
     *  the element coming right after the end of the window.
     * This approach will save us from re-calculating the sum of the overlapping part of the sliding window.
     * Time complexity: O(n)
     */
    public int findMaxSum(int[] nums, int k) {
        //get initial sum of k numbers
        int sum = 0;
        for(int i = 0; i<k; i++)
            sum+=nums[i];
        int maxSum = sum;
        int i = 1;
        while(i<= nums.length-k){
            sum = sum - nums[i-1] + nums[i-1+k];
            if(sum>maxSum)
                maxSum = sum;
            i++;
        }
        return maxSum;
    }
}
