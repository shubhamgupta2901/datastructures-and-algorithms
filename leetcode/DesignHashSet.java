package leetcode;

import java.util.LinkedList;

/**
 * <a href="https://leetcode.com/problems/design-hashset/"/>
 * Design a HashSet without using any built-in hash table libraries.
 *
 * To be specific, your design should include these functions:
 *
 * add(value): Insert a value into the HashSet.
 * contains(value) : Return whether the value exists in the HashSet or not.
 * remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.
 *
 * Example:
 *
 * MyHashSet hashSet = new MyHashSet();
 * hashSet.add(1);
 * hashSet.add(2);
 * hashSet.contains(1);    // returns true
 * hashSet.contains(3);    // returns false (not found)
 * hashSet.add(2);
 * hashSet.contains(2);    // returns true
 * hashSet.remove(2);
 * hashSet.contains(2);    // returns false (already removed)
 *
 * Note:
 *
 * All values will be in the range of [0, 1000000].
 * The number of operations will be in the range of [1, 10000].
 * Please do not use the built-in HashSet library.
 */
public class DesignHashSet {
    class MyHashSet {

        private int capacity;
        private LinkedList<Integer>[] arr;
        /** Initialize your data structure here. */
        public MyHashSet() {
            this.capacity = 10000;
            arr = new LinkedList[capacity];
        }

        public void add(int key) {
            LinkedList<Integer> list;
            int index = hash(key);
            if(arr[index] == null){
                list = new LinkedList<Integer>();
                list.add(key);
                arr[index] = list;
                return;
            }

            list = arr[index];
            //Don't add if key already present.
            for(int i=0; i<list.size();i++){
                int el = list.get(i);
                if(el == key)
                    return;
            }

            list.add(key);
        }

        public void remove(int key) {
            int index = hash(key);
            if(arr[index]== null)
                return;
            LinkedList<Integer> list = arr[index];
            for(int i = 0; i<list.size(); i++){
                int el = list.get(i);
                if(el == key){
                    list.remove(i);
                    return;
                }
            }

        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            int index = hash(key);
            if(arr[index] == null)
                return false;
            LinkedList<Integer> list = arr[index];
            for(int i = 0; i<list.size(); i++){
                int el = list.get(i);
                if(el == key)
                    return true;
            }
            return false;
        }

        private int hash(int key){
            int hashCode = Integer.hashCode(key);
            int index = hashCode % capacity;
            return index;
        }
    }
}
