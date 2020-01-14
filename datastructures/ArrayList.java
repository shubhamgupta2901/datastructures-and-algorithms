package datastructures;

import datastructures.interfaces.IList;

import java.util.Arrays;

/**
 * ArrayList are a way to provide dynamic arrays when we do not know the length of array.
 * It is internally backed by array and when the array gets full, it performs an auto resize operation,
 * where it creates a new array of double the capacity,and copies the content from old array to new array.
 *
 * In terms of performance Array and ArrayList provides similar performance.
 * They take constant time for adding or getting element if you know index.
 * Though automatic resize of ArrayList may slow down insertion a bit.
 */
public class ArrayList implements IList {

    private static final int INITIAL_CAPACITY = 10;
    private int[] arr;
    private int size, capacity;

    public ArrayList(){
        this.capacity = INITIAL_CAPACITY;
        this.arr = new int[capacity];
        this.size = 0;
    }

    public ArrayList(int capacity){
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.size = 0;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param element to be inserted
     * Time Complexity: O(1)
     * When autoResize() is called , a new array is created with double capacity
     * and all the values are copied to it. This takes O(n)
     * But the Amortized Time Complexity is O(1)
     */
    @Override
    public void add(int element) {
        autoResize();
        arr[size++] = element;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     * When autoResize() is called , a new array is created with double capacity
     * and all the values are copied to it. This takes O(n)
     * But the Amortized Time Complexity is O(1)
     * @param index
     * @param element
     * Time Complexity: O(n)
     */
    @Override
    public void add(int index, int element) {
        if(index<0 || index > size)
            return;
        autoResize();
        for(int i = size;i>index;i--)
            arr[i] = arr[i-1];
        arr[index] = element;
        size++;
    }

    /**
     * Removes all of the elements from this list. The list will be empty after this call returns.
     * Time complexity: O(n)
     */
    @Override
    public void clear() {
        for(int i = 0; i<size; i++)
            arr[i] = 0;
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
        for(int i = 0; i<size; i++){
            if(arr[i] == element)
                return true;
        }
        return false;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index
     * @return
     * Time Complexity: O(1)
     */
    @Override
    public int get(int index) {
        if(index<0 || index>=size)
            throw new IndexOutOfBoundsException();
        return arr[index];
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
        for(int i = 0; i<size; i++){
            if(arr[i] == element)
                return i;
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
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     * @param index
     * @return
     * Time Complexity: O(n)
     */
    @Override
    public int removeAtIndex(int index) {
        if(index<0 || index >= size)
            throw new IndexOutOfBoundsException();
        int el = arr[index];
        for(int i = index; i<size-1; i++)
            arr[i] = arr[i+1];
        size--;
        return el;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * If this list does not contain the element, it is unchanged.
     * returns true if this list contained the specified element
     * Time Complexity: O(n)
     */
    @Override
    public boolean removeElement(int element) {
        int index = indexOf(element);
        if(index == -1)
            return false;
        for(int i = index; i<size-1; i++)
            arr[i] = arr[i+1];
        size--;
        return true;
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

    private void autoResize() {
        if(size == capacity){
            capacity = capacity*2;
            arr = Arrays.copyOf(arr,capacity);
        }
    }
}
