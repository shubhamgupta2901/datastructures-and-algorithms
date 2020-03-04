package leetcode;

/**
 * <a href="https://leetcode.com/problems/maximum-average-subarray-i/"/>
 * Given an array consisting of n integers, find the contiguous subarray of given length k that has the
 * maximum average value.
 * And you need to output the maximum average value.
 *
 * Example 1:
 * Input: [1,12,-5,-6,50,3], k = 4
 * Output: 12.75
 * Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 *
 * Note:
 * 1 <= k <= n <= 30,000.
 * Elements of the given array will be in the range [-10,000, 10,000].
 */
public class MaximumAverageSubarray {

    /**
     * Approach 1: Slightly Better than Brute force.
     * A brute-force algorithm will be to calculate the sum of every k-element contiguous subarray of the given array
     * and divide the sum by ‘k’ to find the average. And finally we return the maximum average.
     * The time complexity would have been: There could be possible (n-k+1) possible k-sized contiguous subarrays,
     * and for each the subarray we would take O(k) time. So Time complexity ~O(nk)
     *
     * This approach is possibly known as sliding window approach. Here I find the sum of first k numbers.
     * Then for every subsequent contiguous subarray, I would remove the first number from sum, and add the last number
     * of new "window" to the sum. So sum doesn't need to be calculated for every possible k-sized contiguous subarrays.
     * Time Complexity: O(n)
     */
    public double findMaxAverage(int[] nums, int k) {
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
        return maxSum/(double)k;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,12,-5,-6,50,3};
        int k = 4;
        System.out.println(new MaximumAverageSubarray().findMaxAverage(arr, k));
    }
}
