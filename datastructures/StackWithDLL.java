package datastructures;

import datastructures.interfaces.IStack;

/**
 * Implementing stack with a linked list.
 * One advantage over its implementation in array is we can have a dynamic sized stack.
 * NOTE: The implementation has used a double linked list with tail pointer citing efficiency reasons during push and pop operations.
 * Although there is nothing wrong with this implementation, I could have simply used a Single Linked List which inserts an element at start(push)
 * and delete an element from start(pop). There too push and pop can be performed in constant time.
 * See {@link StackWithLL}
 */
public class StackWithDLL implements IStack {

    private LinkedList list;

    public StackWithDLL() {
        list = new LinkedList();
    }

    /**
     * Push element value into stack
     * @param value
     * @return true if element is pushed, false if element is not pushed
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public boolean push(int value) {
        return list.insertElementAtEnd(value);
    }

    /**
     * Removes the element on top of the stack
     * @return popped element
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int pop() {
        return list.deleteElementFromEnd();
    }

    /**
     * Get the top element in stack without popping
     * @return top element
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int peek() {
        return list.peekTailElement();
    }

    @Override
    public boolean empty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int search(int element) {
        return 0;
    }

    /**
     * Return whether the stack is empty
     * @return true if stack is empty, else false
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Attempting to create a LinkedList which can efficiently perform insert and delete element at the end.
     * Remember one advantage of using array to implement Stack is we are able to perform push and pop operations in constant time.
     * With using a Double LinkedList which maintains a tail pointer in addition to a head pointer,
     * we can perform stack's push and pop operation in constant time as well
     */
    static class LinkedList{

        class Node{
            public int data;
            public Node prev;
            public Node next;

            public Node(int data) {
                this.data = data;
                this.prev = null;
                this.next = null;
            }
        }

        private Node head, tail;

        public LinkedList() {
            head = null;
            tail = null;
        }

        /**
         * Check if the linked list is empty
         * @return
         */
        public boolean isEmpty(){
            return head == null;
        }

        /**
         * Delete the last node of linked list and return its data
         * @return data of deleted node or Integer.MIN_VALUE if list is empty
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public int deleteElementFromEnd(){
            if(isEmpty())
                return Integer.MIN_VALUE;

            //Only element in list
            if(head == tail){
                int value = head.data;
                head = tail = null;
                return value;
            }

            Node currNode = tail, prevNode = tail.prev;
            prevNode.next = null;
            currNode.prev = null;
            tail = prevNode;
            return currNode.data;
        }

        /**
         * Insert an element at the end of list
         * @param data value of node to be inserted
         * @return true if the node is inserted
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public boolean insertElementAtEnd(int data){
            Node node = new Node(data);
            if(isEmpty()){
                head = tail = node;
                return true;
            }
            tail.next = node;
            node.prev = tail;
            tail = node;
            return true;
        }

        /**
         * peek the data of tail element.
         * @return data of tail if not empty
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public int peekTailElement(){
            if(isEmpty())
                return Integer.MIN_VALUE;
            return tail.data;
        }

    }

}
