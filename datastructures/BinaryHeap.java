package datastructures;

import datastructures.interfaces.IPriorityQueue;

import java.util.Arrays;

public class BinaryHeap implements IPriorityQueue {

    private int[] heap;
    private int size, capacity;

    public BinaryHeap() {
        capacity = 10;
        heap = new int[capacity];
        size = 0;
    }

    /**
     * method for printing heap during testing
     * @return
     */
    public int[] getHeap(){
        return Arrays.copyOf(heap,size);
    }

    /**
     * Returns the size of the heap
     * @return
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Inserts a new element into binary heap.
     * After inserting we perform heapifyUp operation to maintain min-heap property.
     * @param value
     * Time complexity: O(logn) for heapify method
     * Space complexity: O(1)
     */
    @Override
    public void insert(int value) {
        ensureExtraCapacity();
        heap[size] = value;
        size++;
        heapifyUp();
    }

    /**
     * returns the minimum value in heap
     * @return
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int getMin() {
        if(size==0)
            throw new IllegalStateException();
        return heap[0];
    }

    /**
     * Extracts the minimum value from the heap and returns it.
     * After removing the minimum value, we copy the last value of heap to the root.
     * We require heapify down method to restore min-heap property.
     * @return
     * Time Complexity: O(logn)
     * Space Complexity: O(1)
     */
    @Override
    public int extractMin() {
        if(size == 0)
            throw new IllegalStateException();
        int min = heap[0];
        heap[0] = heap[size-1];
        size--;
        heapifyDown();
        return min;
    }

    /*Helper method*/
    private void heapifyDown(){
        int index = 0;
        while(hasLeftChild(index)){
            int smallestValueIndex = heap[index] < getLeftChild(index) ? index : getLeftChildIndex(index);
            if(hasRightChild(index))
                smallestValueIndex = heap[smallestValueIndex] < getRightChild(index) ? smallestValueIndex : getRightChildIndex(index);
            if(smallestValueIndex!=index){
                swap(index, smallestValueIndex);
                index = smallestValueIndex;
            }
            else return;
        }
    }

    private void heapifyUp(){
        int index = size-1;
        while(hasParent(index) && getParent(index)>heap[index]){
            swap(index,getParentIndex(index));
            index = getParentIndex(index);
        }
    }


    private int getParentIndex(int index){
        return (index-1)/2;
    }

    private int getLeftChildIndex(int index){
        return 2*index+1;
    }

    private int getRightChildIndex(int index){
        return 2*index+2;
    }

    private boolean hasParent(int index){
        return getParentIndex(index) >= 0;
    }

    private boolean hasLeftChild(int index){
        return getLeftChildIndex(index)<size;
    }

    private boolean hasRightChild(int index){
        return getRightChildIndex(index)<size;
    }

    private int getParent(int index){
        return heap[getParentIndex(index)];
    }

    private int getLeftChild(int index){
        return heap[getLeftChildIndex(index)];
    }

    private int getRightChild(int index){
        return heap[getRightChildIndex(index)];
    }

    private void ensureExtraCapacity(){
        if(size == capacity){
            capacity = capacity*2;
            heap = Arrays.copyOf(heap, capacity);
        }
    }

    private void swap(int index1, int index2){
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

}
