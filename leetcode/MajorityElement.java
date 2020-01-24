package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href = "https://leetcode.com/problems/majority-element/"/>
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Example 1:
 * Input: [3,2,3]
 * Output: 3
 *
 * Example 2:
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 */
public class MajorityElement {

    /**
     * Approach 1: Brute force
     * Traverse the array and store the frequecy of elements in a map.
     * return the number from map which has frequency > nums.length/2
     * Time Complexity: O(n)
     * Space Complexity: O(n) for storing ~n/2 elements (at max)
     */
    public int majorityElement1(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i<nums.length; i++){
            int value = map.getOrDefault(nums[i],0);
            map.put(nums[i],value+1);
        }

        int halfLength = (int)Math.floor((double)nums.length/2);
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            if(entry.getValue() > halfLength)
                return entry.getKey();
        }
        return -1;
    }

    /**
     * Approach 2: Sort the array.
     * then the majority element can be found at n/2 index.
     * Time Complexity: O(nlogn)
     * Space Complexity: O(1)
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }


}
