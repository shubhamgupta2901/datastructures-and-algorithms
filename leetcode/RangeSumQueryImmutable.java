package leetcode;

/**
 * <a href="https://leetcode.com/problems/range-sum-query-immutable/"/>
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 *
 * Note:
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 */
public class RangeSumQueryImmutable {
    /**
     * Approach 1: Tabulate all the results in a 2d array. - Memory Limit Exceeds
     * table[i][j] represents the sum of elements from index i to index j
     * Time Complexity: O(n^2) during constructor call and constant time of sumRange Query
     * Space Complexity: O(n^2)
      * */
    class NumArray1 {
        int[][] table;
        public NumArray1(int[] nums) {
            table = new int[nums.length][nums.length];
            for(int i = 0; i< nums.length; i++)
                table[i][i] = nums[i];
            for(int i = nums.length-2; i>=0; i--){
                for(int j = nums.length-1; j>i; j--){
                    table[i][j] = table[i+1][j] + nums[i];
                }
            }
        }

        public int sumRange(int i, int j) {
            return table[i][j];
        }
    }

    /**
     * Approach 2: Tabulate in 1d array - Accepted
     * Idea is to keep the sum of all elements from index 0 to i in nums[i]
     * Now rangeSumQuery(i,j) = nums[j] - nums[i-1]
     * Time Complexity: O(n) during constructor call and O(1) during rangeSum Query
     * Space Complexity: O(1) since reusing the nums array.
     */
    class NumArray2 {
        int[] nums;
        public NumArray2(int[] nums) {
            this.nums = nums;
            for(int i = 1; i< nums.length; i++)
                nums[i]+= nums[i-1];
        }

        public int sumRange(int i, int j) {
            if(i == 0)
                return nums[j];
            return nums[j]-nums[i-1];
        }
    }


}
