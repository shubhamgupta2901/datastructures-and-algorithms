package ds;

import ds.interfaces.IQueue;

/**
 * LinkedList implementation of queue.
 * One advantage of LinkedList implementation of Queue is we can have a dynamically sized Queue.
 * Using a standard LinkedList would mean that enqueue operation (inserting an element at end in a linked list) would take O(n) time.
 * To overcome this problem, we can have  a LinkedList that maintains a tail pointer in addition to head pointer.
 * This would help us in inserting the element at end in constant time O(1)
 */
public class QueueLL implements IQueue {

    LinkedList list;

    public QueueLL() {
        list =  new LinkedList();
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
        return list.insertElementAtEnd(value);
    }

    /**
     * remove the element from front
     * @return dequeued element
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int dequeue() {
        return list.deleteElementFromStart();
    }

    /**
     * Return the element at front without dequeue
     * @return
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int peek() {
        return list.peekHead();
    }

    /**
     * Check if the queue is empty
     * @return true if empty else false
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    static class LinkedList{
        class ListNode {
             int data;
             ListNode next;

            public ListNode(int data) {
                this.data = data;
                this.next = null;
            }
        }

        private ListNode head, tail;

         LinkedList() {
            this.head = null;
            this.tail = null;
        }

        boolean isEmpty(){
             return head == null;
        }

        int peekHead(){
             if(isEmpty())
                 return Integer.MIN_VALUE;
             return head.data;
        }

        int deleteElementFromStart(){
             if(isEmpty())
                 return Integer.MIN_VALUE;
             //Only element in list
             if(head == tail){
                 int element = head.data;
                 head = tail = null;
                 return element;
             }
             ListNode curr = head;
             head = curr.next;
             curr.next = null;
             return curr.data;
        }

        boolean insertElementAtEnd(int element){
             ListNode node = new ListNode(element);
             if(isEmpty()){
                 head = tail = node;
                 return true;
             }
             tail.next = node;
             tail = node;
             return true;
        }

    }
}
