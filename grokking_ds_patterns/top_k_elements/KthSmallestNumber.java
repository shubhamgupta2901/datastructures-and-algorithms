package grokking_ds_patterns.top_k_elements;

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
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2-o1);
        for(int i = 0; i<nums.length; i++){
            if(queue.size()<k)
                queue.add(nums[i]);
            else if(nums[i]<queue.peek()){
                queue.poll();
                queue.add(nums[i]);
            }
        }
        return queue.peek();
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
    public int findKthSmallestNumber2(int[] nums, int k) {

        for(int i = 0; i<k; i++)
            heapifyUp(nums, i+1);

        for(int i = k; i<nums.length; i++){
            if(nums[i] < nums[0]){
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
            if(heap[parentIndex] >= heap[index])
                return;
            swap(heap,index,parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int[] heap, int size){
        int index = 0;
        int leftChildIndex = leftChildIndex(index);
        while(leftChildIndex < size){
            int largerIndex = heap[index] > heap[leftChildIndex] ? index : leftChildIndex;
            int rightChildIndex = rightChildIndex(index);
            if(rightChildIndex<size && heap[rightChildIndex]>heap[largerIndex])
                largerIndex = rightChildIndex;
            if(largerIndex == index)
                return;
            swap(heap,index, largerIndex);
            index = largerIndex;
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
