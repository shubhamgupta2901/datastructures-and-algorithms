package ds;

import ds.interfaces.IStack;

/**
 * Array implementation of stack
 * Pros: Easy to implement, push and pop operations can be performed in constant time
 * Con: Array will have fixed size.
 */
public class Stack implements IStack {

    private int[] arr;
    private int top;
    private int capacity;

    public Stack() {
        capacity = 10;
        top = -1;
        arr = new int[capacity];
        for(int i =0; i<capacity; i++)
            arr[i] = Integer.MIN_VALUE;
    }

    public Stack(int capacity){
        top = -1;
        arr = new int[capacity];
        for(int i =0; i<capacity; i++)
            arr[i] = Integer.MIN_VALUE;

    }

    /**
     * Push element value into stack
     * @param value
     * @return true if element is pushed, false if element is not pushed
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public boolean push(int value){
        //Overflow, can not insert element in stack
        if(isFull())
            return false;
        arr[++top]=value;
        return true;
    }

    /**
     * Removes the element on top of the stack
     * @return popped element
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int pop(){
        //Empty stack cannot pop
        if(isEmpty())
            return Integer.MIN_VALUE;
        int topElement = arr[top--];
        return topElement;
    }

    /**
     * Get the top element in stack without popping
     * @return top element
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int peek(){
        if(isEmpty())
            return Integer.MIN_VALUE;
        return arr[top];
    }

    /**
     * Return whether the stack is empty
     * @return true if stack is empty, else false
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * Return whether stack is full
     * @return true if stack is full, else false
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    private boolean isFull(){
        return top == capacity-1;
    }


}
