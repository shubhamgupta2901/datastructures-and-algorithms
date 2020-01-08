package datastructures;

import datastructures.interfaces.IMap;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * HashMap is a datastructure to store values in key-value pairs. It allows get and remove operations as well. It performs these operations in constant time.
 * Note on Time Complexity of get(), put() and remove() operations:
 * Depending on the hash function time complexity of these operation range from O(1) to O(n).
 * For a HashMap with n key-value pairs, If the hash function is bad and is mapping all the values to a single bucket, then all the above operations will require
 * traversing through the entire List in that bucket which is of size n.
 * But if the hash function is good and evenly distributes the key-value pairs in all the buckets, then with a reasonable loadFactor we can achieve O(1) time complexity.
 */

public class HashMap implements IMap {

    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private List<Pair> [] buckets;
    private int size;
    int capacity;

    public HashMap(){
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        buckets = new List[capacity];
    }

    /**
     * Returns the size of HashMap
     * @return
     * Time Complexity: O(1)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * puts a new key-value pair in HashMap, if key exists replaces the value for corresponding key
     * @param key
     * @param value
     */
    @Override
    public void put(String key, Object value) {
        if(key == null)
            return;

        List<Pair> list;
        Pair pair;
        int index = hash(key);
        if(buckets[index]== null){
            list = new ArrayList<>();
            pair = new Pair(key,value);
            list.add(pair);
            buckets[index] = list;
            size++;
            expandIfRequired();
            return;
        }

        list = buckets[index];
        //replace if existing
        for(int i = 0; i<list.size(); i++){
            pair = list.get(i);
            if(pair.key.equals(key)){
                pair.value = value;
                return;
            }
        }

        pair = new Pair(key, value);
        list.add(pair);
        size++;
        expandIfRequired();
    }

    /**
     * Returns the value corresponding to a
     * @param key
     * @return
     */
    @Override
    public Object get(String key) {
        int index = hash(key);
        if(buckets[index] == null)
            return null;
        List<Pair> list = buckets[index];
        for(int i = 0; i<list.size(); i++){
            Pair pair = list.get(i);
            if(pair.key.equals(key))
                return pair.value;
        }
        return null;
    }

    @Override
    public void remove(String key) {
        if(key == null)
            return;
        int index = hash(key);
        if(buckets[index] == null)
            return;
        List<Pair> list = buckets[index];
        for(int i = 0 ;i<list.size(); i++){
            if(list.get(i).key.equals(key)){
                list.remove(i);
                size--;
                return;
            }
        }
    }

    /**
     * Note: sometimes the hashcode calculation itself goes beyond the Integer.MAX_VALUE, i.e 2147483647.
     * what happens then is that we get a negative integer after the overflow.
     * Although negative hashCodes are perfectly valid, but after hashing the key,
     * just before we map it to an index in array, we should bitwise & the hashCode
     * with Integer.MAX_VALUE to map it to a valid index in buckets.
     *
     * @param key
     * @return index of the buckets array, where this element will be added in the list.
     */
    private int hash(String key){
        int hashCode = key.hashCode();
        int index = (hashCode & Integer.MAX_VALUE) % capacity;
        return index;
    }


    /**
     * We need to expand the  hashmap (increase the number of buckets in the hashtable)
     * if load factor of hashtable is getting larger than our decided load factor.
     *
     * Load Factor = Total number of elements in hashmap (or size) / Total number of buckets in hashmap (or capacity).
     * Capacity is the number of number of buckets in the HashMap. Initial capacity is kept at 16.
     * The default load factor is defined as 0.75
     * (This is done in order to have O(1) time complexity during get and put operations)
     * So, total number of key-value pairs that can be entered without crossing the load factor: 0.75 * 16 = 12.
     * As soon as the 13th element is entered, the load factor of our hashmap will be greater than 0.75.
     * So we will double the number of buckets in the hashmap.
     *
     * NOTE: Hash tables in real-time systems, cannot pay the price of enlarging the hash table all at once,
     * because it may interrupt time-critical operations. If one cannot avoid dynamic resizing,
     * a solution is to perform the resizing gradually. Disk-based hash tables almost always use some
     * alternative to all-at-once rehashing, since the cost of rebuilding the entire table on disk would be too high.
     *
     * One alternative to enlarging the table all at once is Incremental resizing:
     * During the resize, allocate the new hash table, but keep the old table unchanged.
     * In each lookup or delete operation, check both tables.
     * Perform insertion operations only in the new table.
     * At each insertion also move r elements from the old table to the new table.
     * When all elements are removed from the old table, deallocate it.
     * To ensure that the old table is completely copied over before the new table itself needs to be enlarged,
     * it is necessary to increase the size of the table by a factor of at least (r + 1)/r during resizing.
     * */
    private void expandIfRequired(){
        float currentLoadFactor = (float) size / (float) capacity;
        if(currentLoadFactor <= LOAD_FACTOR)
            return;

        List<Pair> [] oldBuckets = this.buckets;
        capacity = capacity * 2;
        this.buckets = new ArrayList[capacity];
        this.size = 0;


        for(int i = 0; i< oldBuckets.length; i++){
            List<Pair> list = oldBuckets[i];
            if(list !=null){
                for(int j = 0; j<list.size(); j++){
                    Pair pair = list.get(j);
                    put(pair.key,pair.value);
                }
            }

        }

    }

    private class Pair {
        String key;
        Object value;

        Pair(String key, Object value){
            this.key = key;
            this.value = value;
        }
    }
}
