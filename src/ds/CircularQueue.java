package ds;

import ds.interfaces.IQueue;

/**
 * Efficient implementation of Queue using array, when elements are dequeued,
 * it can enqueue elements at starting indices of array again till the queue is full.
 */
public class CircularQueue implements IQueue {

    private int [] arr;
    private int capacity;
    private int front,rear, size;

    public CircularQueue() {
        this.capacity = 5;
        this.arr = new int[this.capacity];
        this.front = this.rear = -1;
        this.size = 0;
    }

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.arr = new int[this.capacity];
        this.front = this.rear = -1;
        this.size = 0;
    }


    /**
     * Insert element at the end
     * @param value
     * @return if element was enqueued
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public boolean enqueue(int value) {
        if(isFull())
            return false;
        if(front == -1&& rear == -1){
            front++;
        }
        rear = ++rear%capacity;
        arr[rear] = value;
        size++;
        return true;
    }

    /**
     * remove the element from front
     * @return dequeued element
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int dequeue() {
        if(isEmpty())
            return Integer.MIN_VALUE;
        int element = arr[front];
        front = ++front%capacity;
        size--;
        return element;
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
        return (size==0);
    }

    /**
     * check if the queue is full
     * @return true if queue is empty, else false
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isFull(){
        return (size==capacity);
    }
}
