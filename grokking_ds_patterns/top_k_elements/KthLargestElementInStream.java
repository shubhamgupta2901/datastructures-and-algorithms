package grokking_ds_patterns.top_k_elements;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 * Design a class to find the kth largest element in a stream.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Your KthLargest class will have a constructor which accepts an integer k and an integer array nums,
 * which contains initial elements from the stream. For each call to the method KthLargest.add, return the element
 * representing the kth largest element in the stream.
 *
 * Example:
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * Note:
 * You may assume that nums' length ≥ k-1 and k ≥ 1.
 */
public class KthLargestElementInStream {

    /**
     * Approach:
     * 1) Create a Min Heap which will store the maximum k elements in them.
     * 2) Keep adding numbers to till the size of heap is k.
     * 3) when a new number is encountered and the size of heap is already k, we check weather the number is greater
     *  than value at top position of heap. If so remove the top position element and add the new number.
     * 4) Kth largest element will always be the top of heap.
     *
     * Time Complexity: Constructor : (nlogk) add(): O(logk)
     * Space Complexity: O(k)
     */
    private PriorityQueue<Integer> minHeap;
    private int k;
    public KthLargestElementInStream(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<Integer>();
        for(int i = 0; i<nums.length ; i++){
            if(minHeap.size()<k)
                minHeap.add(nums[i]);
            else if(nums[i]>minHeap.peek()){
                minHeap.remove();
                minHeap.add(nums[i]);
            }
        }
    }

    public int add(int val) {
        if(minHeap.size()<k)
            minHeap.add(val);
        else if(val>minHeap.peek()){
            minHeap.remove();
            minHeap.add(val);
        }
        return minHeap.peek();
    }
}
