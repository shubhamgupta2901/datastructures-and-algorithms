package datastructures;


import datastructures.interfaces.IStack;

/**
 * Implementing stack with a linked list.
 * One advantage over its implementation in array is we can have a dynamic sized stack.
 */
public class StackWithLL implements IStack {

    private LinkedList list;

    public StackWithLL() {
        this.list = new LinkedList();
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
        return list.insertElementAtStart(value);
    }

    /**
     * Removes the element on top of the stack
     * @return popped element
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int pop() {
        return list.deleteElementFromStart();
    }

    /**
     * Get the top element in stack without popping
     * @return top element
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int peek() {
        return list.peekHeadElement();
    }

    /**
     * Return whether the stack is empty
     * @return true if stack is empty, else false
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    static class LinkedList {
        class Node {
            public int data;
            public Node next;

            public Node(int data) {
                this.data = data;
                this.next = null;
            }
        }

        private Node head;

        public LinkedList() {
            this.head = null;
        }

        public boolean isEmpty(){
            return head == null;
        }

        public int deleteElementFromStart(){
            if(isEmpty())
                return Integer.MIN_VALUE;

            Node curr = head;
            int value = curr.data;
            head = curr.next;
            curr.next = null;
            return value;

        }

        public boolean insertElementAtStart(int data){
            Node node = new Node(data);
            if(isEmpty()){
                head = node;
                return true;
            }

            node.next = head;
            head = node;
            return true;
        }

        public int peekHeadElement(){
            if(isEmpty())
                return Integer.MIN_VALUE;
            return head.data;
        }

    }
}
