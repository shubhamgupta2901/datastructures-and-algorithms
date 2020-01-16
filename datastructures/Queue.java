package datastructures;

import datastructures.interfaces.IQueue;

import java.util.NoSuchElementException;

/**
 * A queue stores items in a first-in, first-out (FIFO) order.
 * Implementing queue ADT with single linked list.
 * The idea is to implement a single list and have references to its tail and head.
 * For enqueue operations insert elements at tail, and for dequeue operations remove
 * elements from head.
 * This would ensure constant time complexity for enqueue, dequeue and peek operations.
 *
 */
public class Queue implements IQueue {

    private class QueueNode{
        int data;
        QueueNode next;

        QueueNode(int data){
            this.data = data;
        }
    }
    private QueueNode head, tail;
    private int size;

    public Queue(){
        this.size = 0;
        this.head = this.tail = null;
    }

    /**
     * Inserts the specified element into this queue.
     * Time complexity: O(1)
     */
    @Override
    public boolean add(int value) {
        QueueNode node = new QueueNode(value);
        if(size == 0){
            head = tail = node;
        }else {
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    /**
     * Retrieves and removes the head of this queue.
     * @throws NoSuchElementException - if this queue is empty
     * Time Complexity: O(1)
     */
    @Override
    public int remove() {
        if(size == 0)
            throw new NoSuchElementException("Empty Queue");
        int element = head.data;
        if(head == tail){
            head = tail = null;
        }
        else {
            QueueNode node = head;
            head = node.next;
            node.next = null;
        }
        size--;
        return element;
    }

    /**
     * Retrieves, but does not remove, the head of this queue.
     * @throws NoSuchElementException - if this queue is empty
     * Time Complexity: O(1)
     */
    @Override
    public int peek() {
        if(this.size == 0)
            throw new NoSuchElementException("Empty queue");
        return head.data;
    }

    /**
     * returns true if the queue is empty.
     * Time Complexity: O(1)
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the current size of queue
     * Time complexity: O(1)
     */
    @Override
    public int size() {
        return size;
    }
}
