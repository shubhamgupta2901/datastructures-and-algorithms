package datastructures;

import datastructures.interfaces.IStack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Stacks are linear data structure which follows LIFO (Last In First Out) order
 * for performing operations.
 * Stack ADT can be implemented using several data structures most commonly using dynamic arrays
 * and linked lists. This is array implementation of stacks.
 *
 * In terms of performance Array and LinkedList implementation provide similar performance.
 * They take O(1) time for push() and pop() operations.
 * However for array implementation, the time complexity is amortized O(1).
 * This is because automatic resize of dynamic array when it is full may slow down push operations a bit.
 */
public class ArrayStack implements IStack {

    private static final int INITIAL_CAPACITY = 5;
    private int capacity, size;
    private int[] arr;

    public ArrayStack(){
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        this.arr = new int[capacity];
    }

    /**
     * Pushes an item onto the top of this stack.
     * @param element
     * @return
     * Time Complexity: amortized O(1)
     * When autoResize() is called , a new array is created with double capacity
     * and all the values are copied to it. This takes O(n)
     * But the Amortized Time Complexity is O(1)
     */
    @Override
    public boolean push(int element) {
        autoResize();
        arr[size++] = element;
        return true;
    }

    /**
     * Removes the object at the top of this stack and returns that object as the value of this function.
     * @return The object at the top of this stack
     * Time Complexity: O(1)
     */
    @Override
    public int pop() {
        if (size == 0)
            throw new EmptyStackException();
        int element = arr[size-1];
        size--;
        return element;
    }

    /**
     * Looks at the object at the top of this stack without removing it from the stack
     * @return The object at the top of this stack
     * Time Complexity: O(1)
     */
    @Override
    public int peek() {
        if(size == 0)
            throw new EmptyStackException();
        return arr[size-1];
    }

    /**
     * Tests if this stack is empty.
     * @return true if stack is empty.
     * Time Complexity: O(1)
     */
    @Override
    public boolean empty() {
        return size == 0;
    }

    /**
     * Returns the size of stack.
     * @return
     * Time Complexity : O(1)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the 1-based position where an object is on this stack.
     * If the element e occurs in this stack, this method returns the distance
     * of the element e from top of the stack.
     * the topmost item on the stack is considered to be at distance 1.
     * In case there are multiple occurrences of element e in stack,
     * the top most occurrence of e will be considered.
     * If the element is not on the stack, return -1.
     * @param element
     * @return
     * Time Complexity: O(n)
     */
    @Override
    public int search(int element) {
        for(int i = size-1; i>=0; i--){
            if(arr[i] == element)
                return size-i;
        }
        return -1;
    }

    private void autoResize(){
        if(size == capacity){
            capacity = capacity*2;
            arr = Arrays.copyOf(arr,capacity);
        }
    }
}
