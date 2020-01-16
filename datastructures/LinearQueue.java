package datastructures;


import datastructures.interfaces.IQueue;

/**
 * This implementation of queue uses array.
 * The biggest issue with its implementation is its quite inefficient, because when deque operations are performed and
 * starting indices of array are emptied, it would not insert element there.
 * For efficient implementation see {@link CircularQueue}
 */

public class LinearQueue implements IQueue {
    private int[] arr;
    private int front , rear, size;
    private int capacity;

    public LinearQueue() {
        this.capacity = 5;
        this.arr = new int[this.capacity];
        for(int i =0; i< this.capacity; i++)
            this.arr[i] = Integer.MIN_VALUE;
        this.front = this.rear = -1;
    }

    public LinearQueue(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        for(int i =0; i< capacity; i++)
            this.arr[i] = Integer.MIN_VALUE;
        this.front = this.rear = -1;
    }

    /**
     * Insert element at the end
     * @param value
     * @return if element was enqueued
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */

    public boolean enqueue(int value) {
        if(isFull())
            return false;
        if(isEmpty())
            front++;
        arr[++rear] = value;
        return true;
    }

    /**
     * remove the element from front
     * @return dequeued element
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */

    public int dequeue() {
        if(isEmpty())
            return Integer.MIN_VALUE;
        return arr[front++];
    }

    @Override
    public boolean add(int value) {
        return false;
    }

    @Override
    public int remove() {
        return 0;
    }

    /**
     * Return the element at front without dequeue
     * @return
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int peek() {
        if(isEmpty())
            return Integer.MIN_VALUE;
        return arr[front];
    }

    /**
     * Check if the queue is empty
     * @return true if empty else false
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public boolean isEmpty() {
        if((front ==-1 && rear == -1) || front > rear)
            return true;
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * check if the queue is full
     * @return true if queue is empty, else false
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isFull(){
        return (rear == capacity-1);
    }
}
