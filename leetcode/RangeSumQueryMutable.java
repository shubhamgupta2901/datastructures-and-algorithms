package leetcode;

/**
 * <a href="https://leetcode.com/problems/range-sum-query-mutable/"/>
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * The update(i, val) function modifies nums by updating the element at index i to val.
 *
 * Example:
 *
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * Note:
 *
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is distributed evenly.
 * TODO: Check Segment tree solution  which takes O(n) in constructor and O(logn) for rangeSum and update
 */
public class RangeSumQueryMutable {
    /**
     * Approach 1: Accepted but very slow.
     * Use 1-d array, and at nums[i] store sum of numbers from index 0 to i.
     * Now rangeQuery(i,j) = nums[j]-nums[i-1]
     * For update(i) query update the change in value to all indices bigger or equal to i
     * Time Complexity: Constructor O(n) rangeSum O(1) update O(n)
     * Space Complexity: O(1)
     */
    class NumArray {
        int[] nums;
        public NumArray(int[] nums) {
            this.nums = nums;
            for(int i = 1; i<nums.length; i++)
                nums[i]+= nums[i-1];
        }

        public void update(int i, int val) {
            int change = val - this.sumRange(i, i);
            for(int k = i; k<nums.length; k++)
                nums[k] += change;
        }

        public int sumRange(int i, int j) {
            if(i == 0) return nums[j];
            return nums[j]- nums[i-1];
        }
    }
}
