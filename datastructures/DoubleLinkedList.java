package datastructures;

import datastructures.interfaces.ILinkedList;

import java.util.NoSuchElementException;

public class DoubleLinkedList implements ILinkedList {

    private class ListNode{
        int data;
        ListNode prev;
        ListNode next;

        ListNode(int data){
            this.data = data;
            this.prev = this.next = null;
        }
    }
    private ListNode head, tail;
    private int size;

    public DoubleLinkedList(){
        this.size = 0;
        this.head = this.tail = null;
    }

    /**
     * Appends the specified element to the end of this list.
     * Time Complexity: O(1)
     */
    @Override
    public void add(int element) {
        ListNode node = new ListNode(element);
        if(head == null)
            head = tail = node;
        else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * @param index 0-based index
     * @throws  IndexOutOfBoundsException if index is not valid
     * Time Complexity: O(n)
     */
    @Override
    public void add(int index, int element) {
        if(index<0 || index> size)
            throw new IndexOutOfBoundsException();
        if(index == 0)
            addFirst(element);
        else if(index == size)
            addLast(element);
        else {
            ListNode node = new ListNode(element);
            ListNode ptr = head;
            for(int i = 0; i<index; i++)
                ptr = ptr.next;
            ListNode prvPtr = ptr.prev;
            prvPtr.next = node;
            node.prev = prvPtr;

            node.next = ptr;
            ptr.prev = node;
            size++;
        }
    }

    /**
     * Removes all of the elements from this list. The list will be empty after this call returns.
     * Time complexity: O(1)
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns true if this list contains the specified element.
     * Time Complexity: O(n)
     */
    @Override
    public boolean contains(int element) {
        ListNode ptr = head;
        while(ptr!=null){
            if(ptr.data == element)
                return true;
            ptr = ptr.next;
        }
        return false;
    }

    /**
     * Returns the element at the specified position in this list.
     * Note this operation takes constant time in {@link ArrayList}
     * but since memory locations of nodes are not contiguous in linked list
     * we need to traverse the entire linked list.
     *
     * Time Complexity: O(n)
     * @throws IndexOutOfBoundsException
     */
    @Override
    public int get(int index) {
        if(index<0 || index >= size)
            throw new IndexOutOfBoundsException(index +" is invalid index.");
        ListNode ptr;
        if(index <= size/2){
            ptr = head;
            for(int i = 0 ; i< index; i++)
                ptr = ptr.next;
            return ptr.data;
        }else {
            ptr = tail;
            for(int i = size-1; i>index; i--)
                ptr = ptr.prev;
            return ptr.data;
        }
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * @param element
     * @return
     * Time Complexity: O(n)
     */
    @Override
    public int indexOf(int element) {
        ListNode ptr = head;
        for(int i = 0; i<size; i++){
            if(ptr.data == element)
                return i;
            ptr = ptr.next;
        }
        return -1;
    }

    /**
     * Returns true if this list contains no elements.
     * Time Complexity: O(1)
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int removeAtIndex(int index) {
        return 0;
    }

    @Override
    public boolean removeElement(int element) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void addFirst(int element) {
        ListNode node = new ListNode(element);
        if(head == null){
            head = tail = node;
        }
        else{
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    @Override
    public void addLast(int element) {
        add(element);
    }

    /**
     * Returns the first element in this list.
     * Time Complexity: O(1)
     * @throws NoSuchElementException
     */
    @Override
    public int getFirst() {
        if(size == 0)
            throw new NoSuchElementException();
        return head.data;
    }

    /**
     * Returns the last element in this list.
     * Time Complexity: O(1)
     * @throws NoSuchElementException
     */
    @Override
    public int getLast() {
        if(size == 0)
            throw new NoSuchElementException();
        return tail.data;
    }

    /**
     * Removes the first element from this list.
     * Time complexity: O(1)
     * @throws NoSuchElementException
     */
    @Override
    public void removeFirst() {
        if(head == null)
            throw new NoSuchElementException();
        if(head == tail){
            head = tail = null;
        }else {
            ListNode ptr = head.next;
            head.next = null;
            ptr.prev = null;
            head = ptr;
        }
        size--;
    }

    /**
     * Removes the last element from this list.
     * Time Complexity: O(1)
     * Note this took O(n) in single linked list.
     * @throws NoSuchElementException
     */
    @Override
    public void removeLast() {
        if(head == null)
            throw new NoSuchElementException();

    }

}
