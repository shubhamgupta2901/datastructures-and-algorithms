package leetcode;

import java.util.Arrays;
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

    /**
     * Sort the array, look for the number whose next is not the same
     * @param nums
     * @return
     * Time Complexity: O(nlogn) for sorting the array
     * Space Complexity: O(1)
     */
    public int singleNumberApproach2(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        while(i < nums.length-1){
            if(nums[i] == nums[i+1])
                i=i+2;
            else return nums[i];
        }
        return nums[nums.length-1];
    }


    /**
     * Best approach
     * Concept
     * If we take XOR of zero and some bit, it will return that bit
     * a ^ 0 = a
     * If we take XOR of two same bits, it will return 0
     * a ^ a=0
     * a^b^a=(a^a)^b=0^b=b
     * So we can XOR all bits together to find the unique number.
     * @param nums
     * @return
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int singleNumberApproach3(int[] nums) {
        int a = 0;
        for(int i = 0; i<nums.length; i++)
            a = a^nums[i];
        return a;
    }
}
