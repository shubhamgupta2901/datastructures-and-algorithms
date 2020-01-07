package leetcode;

import java.util.LinkedList;

/**
 * Design a HashMap without using any built-in hash table libraries.
 *
 * To be specific, your design should include these functions:
 *
 * put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
 * get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
 *
 * Example:
 *
 * MyHashMap hashMap = new MyHashMap();
 * hashMap.put(1, 1);
 * hashMap.put(2, 2);
 * hashMap.get(1);            // returns 1
 * hashMap.get(3);            // returns -1 (not found)
 * hashMap.put(2, 1);          // update the existing value
 * hashMap.get(2);            // returns 1
 * hashMap.remove(2);          // remove the mapping for 2
 * hashMap.get(2);            // returns -1 (not found)
 *
 * Note:
 *
 * All keys and values will be in the range of [0, 1000000].
 * The number of operations will be in the range of [1, 10000].
 * Please do not use the built-in HashMap library.
 */
public class DesignHashMap {

    class MyHashMap {
        private class Pair{
            private int key;
            private int value;

            Pair(int key, int value){
                this.key = key;
                this.value = value;
            }
        }

        private LinkedList<Pair>[] arr;
        private int capacity;
        /** Initialize your data structure here. */
        public MyHashMap() {
            capacity = 10000;
            arr = new LinkedList[capacity];
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            Pair pair = null;
            LinkedList<Pair> list;
            int index = map(key);


            if(arr[index] == null){
                pair = new Pair(key, value);
                list = new LinkedList();
                list.add(pair);
                arr[index] = list;
                return;
            }

            list = arr[index];
            //replace the value if key exists
            for(int i = 0; i<list.size();i++){
                pair = list.get(i);
                if(pair.key == key){
                    pair.value = value;
                    return;
                }
            }

            pair = new Pair(key,value);
            list.add(pair);
            return;
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int index = map(key);
            if(arr[index] == null)
                return -1;
            LinkedList<Pair> list = arr[index];
            for(int i = 0; i<list.size(); i++){
                Pair pair = list.get(i);
                if(pair.key == key)
                    return pair.value;
            }
            return -1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int index = map(key);
            if(arr[index] == null)
                return;
            LinkedList<Pair> list = arr[index];
            for(int i = 0; i<list.size(); i++){
                Pair pair = list.get(i);
                if(pair.key == key){
                    list.remove(i);
                    return;
                }
            }
            return;
        }

        private int map(int key){
            int hashCode = Integer.hashCode(key);
            return hashCode % capacity;
        }
    }

}
