package algorithms.dynamic_programming.longest_common_substring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    /**
     * Approach 2: Top Down Memoization
     * The two changing values for our recursive function are the current and the previous index.
     * Therefore, we can store the results of all subproblems in a two-dimensional array.
     *
     * Time Compelxity: O(N^2).
     * Since our memoization array cache[nums.length()][nums.length()] stores the results for all the subproblems,
     * we can conclude that we will not have more than N*N subproblems (where ‘N’ is the length of the input sequence).
     * This means that our time complexity will be O(N^2).
     *
     * Space Complexity: O(N^2)
     * The above algorithm will be using O(N^2) space for the memoization array.
     * Other than that we will use O(N) space for the recursion call-stack.
     */
    public int lengthOfLISApproach2(int[] nums) {
        Integer[][] cache = new Integer[nums.length+1][nums.length];
        return helper(nums, -1,0,cache);
    }
    private int helper(int[] nums, int prevIndex, int currentIndex, Integer[][] cache){
        if(currentIndex == nums.length)
            return 0;
        if(cache[prevIndex+1][currentIndex] !=null)
            return cache[prevIndex+1][currentIndex];
        int lis1 = 0;
        if(prevIndex == -1 || nums[currentIndex] > nums[prevIndex])
            lis1 = 1 + helper(nums, currentIndex, currentIndex+1, cache);
        int lis2 = helper(nums, prevIndex, currentIndex+1, cache);
        cache[prevIndex+1][currentIndex] =  Math.max(lis1, lis2);
        return cache[prevIndex+1][currentIndex];
    }

    /**
     * Approach 3: Bottom up Approach - Wrong Answer.
     * TODO: Fix it.
     */
    public int lengthOfLISApproach3(int[] nums) {
        int length = nums.length;
        if(length == 0 || length == 1)
            return length;
        int[][] table = new int[length+1][length];

        //base case: table[p][c] == 0 if p-1 >= c

        //base case
        for(int p = -1; p < length-1; p++){
            if(p == -1 || nums[p]<nums[length-1])
                table[p+1][length-1] = 1;
        }

        //fill the table bottom up
        for(int p= length-2; p>=0; p--){
            for(int c = length-2; c>=p; c--){
                int subsequence1 = 0;
                if(p == 0 || nums[p-1]< nums[c])
                    subsequence1 = 1 + table[c][c+1];
                int subsequence2 = table[p][c+1];
                table[p][c] = Math.max(subsequence1, subsequence2);
            }
        }

        return table[0][0];
    }

    /**
     * Approach 4: Bottom up approach with a hashmap instead of 2-d array - Wrong Answer
     * This is to avoid the confusion of previousIndex being -1.
     * @param nums
     * @return
     */
    public int lengthOfLISApproach4(int[] nums) {
        int length = nums.length;
        if(length == 0 || length == 1)
            return length;
        HashMap<Integer, int[]> table = new HashMap<>();

        for(int p = -1; p<length; p++)
            table.put(p,new int[length]);

        //base case: table[p][c] == 0 if p >= c
        for(int p = -1; p<length; p++){
            for(int c = p; c>=0; c--){
                table.get(p)[c] = 0;
            }
        }

        //base case: table[p][length-1] = 1
        for(int p =-1; p <length-1; p++)
            table.get(p)[length-1] = 1;

        //fill the table bottom up
        for(int p = length-2; p>=-1; p--){
            for(int c = length-2; c>p; c--){
                int subsequence1 = 0;
                if(p == -1 || nums[p]<nums[c])
                    subsequence1 = 1 + table.get(c)[c+1];
                int subsequence2 = table.get(p)[c+1];
                table.get(p)[c] = Math.max(subsequence1, subsequence2);
            }
        }
        return table.get(-1)[0];
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{10,9,2,5,3,7,101,18};
        int[] nums2 = new int[]{-4,10,3,7,15};
        int lis = new LongestIncreasingSubsequence().lengthOfLISApproach4(nums1);
        System.out.println(lis);
    }
}
