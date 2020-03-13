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
     *
     * Time Complexity: O(nlogk)
     * Space Complexity: O(k)
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

    /**
     * Approach 2: Creating a Min Heap is taking extra O(k) space.
     * So we can heapify our input array for first k elements.
     * This way the time complexity is remains same and we use constant space.
     * Note: In above approach, When traversing through array to add them in heap, when i >= k and nums[i] > heap.peek(),
     * we first remove the top element and then add the new numbers.
     * However understanding the heapify methods allow us to make one small optimisation: We do not delete the top element
     * then heapifyDown the heap and then add the new number and heapifyUp.
     * We simply swap the new number with heap[0], and perform heapifyDown.
     *
     * Time Complexity: O(nlogk)
     * Space Complexity: O(1)
     */
    public int findKthLargest2(int[] nums, int k) {

        for(int i = 0; i<k; i++)
            heapifyUp(nums, i+1);

        for(int i = k; i<nums.length; i++){
            if(nums[i] > nums[0]){
                nums[0] = nums[i];
                heapifyDown(nums,k);
            }
        }
        return nums[0];
    }

    private void heapifyUp(int[]heap, int size){
        int index = size-1;
        while(index>0){
            int parentIndex = parentIndex(index);
            if(heap[parentIndex] <= heap[index])
                return;
            swap(heap,index,parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int[] heap, int size){
        int index = 0;
        int leftChildIndex = leftChildIndex(index);
        while(leftChildIndex < size){
            int smallerIndex = heap[index] < heap[leftChildIndex] ? index : leftChildIndex;
            int rightChildIndex = rightChildIndex(index);
            if(rightChildIndex<size && heap[rightChildIndex]<heap[smallerIndex])
                smallerIndex = rightChildIndex;
            if(smallerIndex == index)
                return;
            swap(heap,index, smallerIndex);
            index = smallerIndex;
            leftChildIndex = leftChildIndex(index);
        }
    }

    private void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    private int leftChildIndex (int index){
        return 2*index + 1;
    }

    private int rightChildIndex (int index){
        return 2*index + 2;
    }

    private int parentIndex(int index){
        return (index-1)/2;
    }
}
