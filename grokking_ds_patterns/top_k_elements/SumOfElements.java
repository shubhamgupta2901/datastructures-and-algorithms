package grokking_ds_patterns.top_k_elements;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array, find the sum of all numbers between the K1’th and K2’th smallest elements of that array.
 *
 * Example 1:
 * Input: [1, 3, 12, 5, 15, 11], and K1=3, K2=6
 * Output: 23
 * Explanation: The 3rd smallest number is 5 and 6th smallest number 15. The sum of numbers coming
 * between 5 and 15 is 23 (11+12).
 *
 * Example 2:
 * Input: [3, 5, 8, 7], and K1=1, K2=4
 * Output: 12
 * Explanation: The sum of the numbers between the 1st smallest number (3) and the 4th smallest
 * number (8) is 12 (5+7).
 *
 * Note k2>k1
 */
public class SumOfElements {


    /**
     * Approach: One simpler approach could be to sort the array and then add the numbers between k1 and k2 index.
     * That would take O(nlogn) time and O(1) space.
     *
     * In this approach:
     * 1. Create a Max Heap of k2 smallest elements of array.
     * 2. remove (k2-k1-1) elements from heap and add them, which is our sum.
     *
     * Time Complexity: O(nlog(k2))
     * Space Complexity: O(k2)
     */
    public static int findSumOfElements(int[] nums, int k1, int k2) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((i1,i2)-> (i2-i1));
        for(int i = 0; i<nums.length ; i++){
            if(maxHeap.size() < k2-1)
                maxHeap.add(nums[i]);
            else if(nums[i]<maxHeap.peek()){
                maxHeap.remove();
                maxHeap.add(nums[i]);
            }
        }
        int sum = 0;
        for(int i  = 1 ; i< k2-k1; i++)
            sum+= maxHeap.poll();

        return sum;
    }

    public static void main(String[] args) {
        int result = SumOfElements.findSumOfElements(new int[] { 1, 3, 12, 5, 15, 11 }, 3, 6);
        System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);

        result = SumOfElements.findSumOfElements(new int[] { 3, 5, 8, 7 }, 1, 4);
        System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);
    }

}
