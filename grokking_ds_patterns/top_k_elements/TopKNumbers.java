package grokking_ds_patterns.top_k_elements;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given an unsorted array of numbers, find the ‘K’ largest numbers in it.
 */
public class TopKNumbers {

    /**
     *
     * Approach:  One approach could be to sort the array and return the top k elements. This would take O(nlogn) time.
     * But in this approach I am creating a Min-Heap of the largest k numbers in array. This will only take (nlogk) time.
     * Time Complexity: O(nlogk)
     * Space Complexity: O(n) for the list to be returned and additional O(k) space for creating Min Heap.
     */
    public static List<Integer> findKLargestNumbers1(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        if(nums.length == 0 || k == 0)
            return list;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i<nums.length; i++){
            if(queue.size()<k)
                queue.add(nums[i]);
            else if(queue.peek()<nums[i]){
                queue.poll();
                queue.add(nums[i]);
            }
        }
        while(!queue.isEmpty()){
            list.add(queue.poll());
        }

        return list;
    }


    /**
     * Approach: Similar to above approach, except here I am creating a min heap in the list to be returned.
     * This way we do not need extra O(k) space for Min Heap and extra O(k) time for copying values from heap to list.
     * So no extra space will be used apart from the list that needs to be returned.
     * Time Complexity: O(nlogk)
     * Space Complexity: O(n) for the list to be returned.
     */
    public List<Integer> findKLargestNumbers2(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i<nums.length; i++){
            if(list.size()<k){
                heapifyUp(list,nums[i]);
            }
            else if(nums[i]>list.get(0)){
                heapifyDown(list);
                heapifyUp(list, nums[i]);
            }
        }
        return list;
    }

    private void heapifyDown(List<Integer> heap){
        int lastIndex = heap.size()-1;
        heap.set(0,heap.get(lastIndex));
        heap.remove(lastIndex);
        int index = 0;
        int leftChildIndex = leftChildIndex(index);
        while(leftChildIndex<heap.size()){
            int smallerIndex = heap.get(index) <= heap.get(leftChildIndex) ? index : leftChildIndex;
            int rightChildIndex = rightChildIndex(index);
            if(rightChildIndex<heap.size() && heap.get(rightChildIndex)<heap.get(smallerIndex))
                smallerIndex = rightChildIndex;
            if(smallerIndex== index)
                return;
            swap(heap, index, smallerIndex);
            index = smallerIndex;
            leftChildIndex = leftChildIndex(index);
        }
    }

    private void heapifyUp(List<Integer> heap, int num){
        heap.add(num);
        int index = heap.size()-1;
        int parentIndex = parentIndex(index);
        while(index>0 && heap.get(parentIndex)>heap.get(index)){
            swap(heap,index,parentIndex);
            index = parentIndex;
            parentIndex = parentIndex(index);
        }
    }

    private int leftChildIndex(int index){
        return 2*index + 1;
    }

    private int rightChildIndex(int index){
        return 2*index + 2;
    }

    private int parentIndex(int index){
        return (index-1)/2;
    }

    private void swap(List<Integer> list, int index1, int index2){
        int temp = list.get(index1);
        list.set(index1,list.get(index2));
        list.set(index2, temp);
    }



    public static void main(String[] args) {
        List<Integer> result = new TopKNumbers().findKLargestNumbers2(new int[] { 3, 1, 5, 12, 2, 11 }, 3);
        System.out.println("Here are the top K numbers: " + result);

        result = new TopKNumbers().findKLargestNumbers2(new int[] { 5, 12, 11, -1, 12 }, 3);
        System.out.println("Here are the top K numbers: " + result);
    }



}
