package grokking_ds_patterns.top_k_elements;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an unsorted array of numbers, find Kth smallest number in it.
 *
 * Please note that it is the Kth smallest number in the sorted order, not the Kth distinct element.
 */
public class KthSmallestNumber {

    /**
     * Approach : We can use a max-heap. As we know, the root is the biggest element in the max heap.
     * So, since we want to keep track of the ‘K’ smallest numbers,
     * we can compare every number with the root while iterating through all numbers,
     * and if it is smaller than the root, we’ll take the root out and insert the smaller number.
     * Time Complexity: O(nlogk)
     * Space Complexity: O(k) for heap
     */
    public static int findKthSmallestNumber(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o1-o2);
        for(int i = 0; i<nums.length; i++){
            if(queue.size()<k)
                queue.add(nums[i]);
            else if(nums[i]>queue.peek()){
                queue.poll();
                queue.add(nums[i]);
            }
        }
        return queue.peek();
    }
}
