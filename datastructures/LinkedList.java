package datastructures;

import datastructures.interfaces.ILinkedList;


/**
 * Like arrays, LinkedList are also linear data structure.
 * However LinkedList elements are not stored in contiguous locations like arrays.
 * Each element of the LinkedList has the reference to the next element of the LinkedList.
 *
 * To facilitate dynamic resizing in arrays,we had to create a new array of double capacity and copy all the
 * elements in new array. This limitation does not arise in linked list, because everytime we need to add an element,
 * we dynamically allocate memory for that element at run time.
 *
 * This is a Single Linked List, and we keep track of first and last element of LinkedList.
 * This allows us to add, get and remove elements from beginning or end at constant time.
 */
public class LinkedList implements ILinkedList {

    private class ListNode{
        int data;
        ListNode next;

        ListNode(int data){
            this.data = data;
        }
    }

    private ListNode head, tail;
    private int size;

    /**
     * Appends the specified element to the end of this list.
     * @param element to be inserted
     * Time Complexity: O(1)
     */
    @Override
    public void add(int element) {
        ListNode node = new ListNode(element);
        if(head == null){
            head = tail = node;
        }
        else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     * @param index 0-based index
     * @param element
     * Time Complexity: O(n)
     */
    @Override
    public void add(int index, int element) {
        if(index<0 || index>size)
            throw new IndexOutOfBoundsException(index +" is invalid index.");
        if(index == 0)
            addFirst(element);
        else if(index == size)
            add(element);
        else {
            ListNode node = new ListNode(element);
            ListNode ptr = head;
            for(int i = 0; i<index-1;i++){
                ptr = ptr.next;
            }
            node.next = ptr.next;
            ptr.next = node;
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
     * @param element
     * @return
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
     * @param index
     * @return
     * Note this operation takes constant time in {@link ArrayList}
     * but since memory locations of nodes are not contiguous in linked list
     * we need to traverse the entire linked list.
     * Time Complexity: O(n)
     */
    @Override
    public int get(int index) {
        if(index<0 || index >= size)
            throw new IndexOutOfBoundsException(index +" is invalid index.");

        ListNode ptr = head;
        for(int i = 0; i<index; i++)
            ptr = ptr.next;
        return ptr.data;
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
     * @return
     * Time Complexity: O(1)
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes the element at the specified position in this list.
     * Returns the element that was removed from the list.
     * @param index
     * @return
     * Time Complexity: O(n)
     */
    @Override
    public int removeAtIndex(int index) {
        if(index<0 || index>= size)
            throw new IndexOutOfBoundsException(index +" is invalid.");
        int element;
        if(index == 0){
            element = head.data;
            removeFirst();
        }
        else if(index == size-1){
            element = tail.data;
            removeLast();
        }
        else{
            ListNode prev = null, curr = head;
            for(int i = 0; i<index; i++){
                prev = curr;
                curr = curr.next;
            }
            element = curr.data;
            prev.next = curr.next;
            curr.next = null;
            size--;
        }

        return element;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * If this list does not contain the element, it is unchanged.
     * returns true if this list contained the specified element
     * Time Complexity: O(n)
     */
    @Override
    public boolean removeElement(int element) {
        if(head == null)
            return false;
        //first node
        if(head.data == element){
            removeFirst();
            return true;
        }

        //Note we can not check if the element is in tail node yet, because
        // we need to delete the first occurrence of element.
        ListNode prev = null, curr = head;
        while(curr!=null){
            if(curr.data == element){
                //last node
                if(curr == tail){
                    tail = prev;
                    prev.next = null;
                    size--;
                    return true;
                }
                //prev will not be null because in that would mean it is the first node
                prev.next = curr.next;
                curr.next = null;
                size--;
                return true;
            }
            else {
                prev = curr;
                curr = curr.next;
            }
        }

        return false;
    }

    /**
     * Returns the number of elements in this list.
     * @return
     * Time Complexity: O(1)
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * In addition to methods inherited by IList interface {@link datastructures.interfaces.IList},
     * LinkedList offers some extra functions to do in constant time.
     * These methods are inherited from ILinkedList interface {@link ILinkedList}
     */

    /**
     * Inserts the specified element at the beginning of this list.
     * @param element
     * Time complexity: O(1)
     */
    @Override
    public void addFirst(int element) {
        ListNode node = new ListNode(element);
        if(head == null){
            head = tail = node;
        }else {
            node.next = head;
            head = node;
        }
        size++;
    }

    /**
     * Appends the specified element to the end of this list.
     * Similar to {@link LinkedList#add(int)}
     * @param element
     * Time Complexity: O(1)
     */
    @Override
    public void addLast(int element) {
        add(element);
    }

    /**
     * Returns the first element in this list.
     * Time Complexity: O(1)
     * @return
     */
    @Override
    public int getFirst() {
        if(head == null)
            throw new IndexOutOfBoundsException();
        return head.data;
    }

    /**
     * Returns the last element in this list.
     * Time Complexity: O(1)
     * @return
     */
    @Override
    public int getLast() {
        if(head == null)
            throw new IndexOutOfBoundsException();
        return tail.data;
    }

    /**
     * Removes the first element from this list.
     * Time complexity: O(1)
     */
    @Override
    public void removeFirst() {
        //Empty List
        if(head == null)
            return;
        //Only Node in Linked List
        if(head == tail){
            head = null;
            tail = null;
        }else{
            ListNode curr = head;
            head = curr.next;
            curr.next = null;
        }
        size--;
    }

    /**
     * Removes the last element from this list.
     * Time Complexity: O(n)
     * We can do this operation in constant time using Double LinkedList
     */
    @Override
    public void removeLast() {
        //Empty List
        if(head == null)
            return;
        //Only Node in Linked List
        if(head == tail){
            head = null;
            tail = null;
        }else {
            ListNode curr = head;
            while(curr.next!=tail){
                curr = curr.next;
            }
            tail = curr;
            tail.next = null;
        }
        size--;
    }

}
