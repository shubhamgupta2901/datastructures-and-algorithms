package leetcode;

import java.util.HashSet;

/**
 * <a href="https://leetcode.com/problems/single-number/"/>
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: [4,1,2,1,2]
 * Output: 4
 */
public class SingleNumber {
    /**
     * Approach 1: Accepted.
     * Run a loop through array, and add it to a hashset if its not there,
     * if it already exists remove it
     * the only element in hashset is the unique number.
     * @param nums
     * @return
     * Time Complexity: O(n)
     * Space Compelxity: O(n)
     */
    public int singleNumber(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for(int i = 0; i< nums.length; i++){
            if(hashSet.contains(nums[i]))
                hashSet.remove(nums[i]);
            else hashSet.add(nums[i]);
        }

        for(Integer unique : hashSet)
            return unique;
        return -1;
    }
}
