package datastructures;

import datastructures.interfaces.IStack;

import java.util.EmptyStackException;

/**
 * Stacks are linear data structure which follows LIFO (Last In First Out) order
 * for performing operations.
 * Stack ADT can be implemented using several data structures most commonly using dynamic arrays
 * and linked lists. This is Linked List implementation of stacks.
 *
 * The idea is to maintain a linked list and insert element at beginning on push operations.
 * and remove element from beginning on pop operations. This way we get to maintain LIFO order.
 */
public class Stack implements IStack {

    private class StackNode{
        int data;
        StackNode next;

        StackNode(int data){
            this.data = data;
        }
    }

    private int size;
    private StackNode head;

    public Stack(){
        this.size = 0;
        this.head = null;
    }

    /**
     * Pushes an item onto the top of this stack.
     * @param element
     * @return
     * Time Complexity:  O(1)
     */
    @Override
    public boolean push(int element) {
        addFirst(element);
        return true;
    }
    /**
     * Removes the element at the top of this stack and returns that element as the value of this function.
     * @return The element at the top of this stack
     * Time Complexity: O(1)
     */
    @Override
    public int pop() {
        if(size == 0)
            throw new EmptyStackException();
        return removeFirst();
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
        return head.data;
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
        int i = 1;
        StackNode curr = head;
        while (curr!=null){
            if(curr.data == element)
                return i;
            i++;
            curr = curr.next;
        }
        return -1;
    }

    private void addFirst(int element){
        StackNode node = new StackNode(element);
        if(head == null){
            head = node;
        }else{
            node.next = head;
            head = node;
        }
        size++;
    }

    private int removeFirst(){
        int el = head.data;
        StackNode curr = head;
        head = curr.next;
        curr.next = null;
        size--;
        return el;
    }
}
