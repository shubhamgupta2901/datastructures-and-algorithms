package ds;

import ds.interfaces.IQueue;

public class QueueLL implements IQueue {

    LinkedList list;

    public QueueLL() {
        list =  new LinkedList();
    }

    @Override
    public boolean enqueue(int value) {
        return list.insertElementAtEnd(value);
    }

    @Override
    public int dequeue() {
        return list.deleteElementFromStart();
    }

    @Override
    public int peek() {
        return list.peekHead();
    }

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
