package leetcode;

import java.util.PriorityQueue;

/**
 * See <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/submissions/"/>
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElementInArray {

    /**
     * Approach: One way would be to sort the array in O(nlogn) time and return the kth index. This would take O(1) space.
     * Here my approach is to create a MinHeap of k elements, This would have k largest elements of the array.
     * Time Complexity would O(nlogk) as we and delete n numbers in heap of size k. Space Complexity would be O(k) for Min heap.
     * So if k is going to be considerably smaller than n, then this approach works better.
     * Other wise sorting the array would work better.
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        for(int i = 0; i<nums.length; i++){
            if(heap.size()<k)
                heap.add(nums[i]);
            else if(nums[i]>heap.peek()){
                heap.poll();
                heap.add(nums[i]);
            }
        }
        return heap.peek();
    }

}
