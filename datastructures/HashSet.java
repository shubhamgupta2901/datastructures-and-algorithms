package datastructures;

import datastructures.interfaces.ISet;

import java.util.ArrayList;
import java.util.List;

/**
 * HashSet is a used to store elements using hashing mechanism. It contains unique elements only.
 * Its different from a list in many aspect: Unlike HashSet doesn't maintain the insertion order.
 * Here, elements are inserted on the basis of their hashcode.
 * Note on Time Complexity of add(), contains() and remove() operations:
 * Depending on the hash function, time complexity of these operation range from O(1) to O(n).
 * If the hash function is bad and is mapping all the elements to a single bucket, then all the above operations will require
 * traversing through the entire List in that bucket which is of size n.
 * But if the hash function is good and evenly distributes the key-value pairs in all the buckets,
 * then with a reasonable loadFactor we can achieve O(1) time complexity.
 */
public class HashSet implements ISet {

    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private List<String> [] buckets;
    private int capacity;
    private int size;

    public HashSet(){
        this.size = 0;
        this.capacity = INITIAL_CAPACITY;
        this.buckets = new List[capacity];
    }

    /**
     * returns the size of hashset
     * @return
     * Time Complexity: O(1)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * returns true if hashset is empty.
     * @return
     * Time Complexity: O(1)
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns true if this set contains the specified element.
     * @param key
     * @return
     * Time Complexity: O(1)
     */
    @Override
    public boolean contains(String key) {
        int index = hash(key);
        if(buckets[index] == null)
            return false;
        List<String> list = buckets[index];
        for(int i = 0; i<list.size(); i++){
            if(list.get(i).equals(key))
                return true;
        }
        return false;
    }

    /**
     * Adds the specified element to this set if it is not already present.
     * If this set already contains the element, the call leaves the set
     * unchanged and returns false, this ensures that sets never contain
     * duplicate elements.
     * @param key
     * Time Complexity: O(1)
     */
    @Override
    public boolean add(String key) {
        int index = hash(key);
        List<String> list;
        if(buckets[index] == null){
            list = new ArrayList<>();
            list.add(key);
            buckets[index] = list;
            size++;
            expandIfRequired();
            return true;
        }

        list = buckets[index];
        for(int i = 0 ; i < list.size(); i++){
            if(list.get(i).equals(key))
                return false;
        }

        list.add(key);
        size++;
        expandIfRequired();
        return true;
    }

    /**
     * Removes the specified element from this set if it is present. Returns true if this set
     * contained the element (or equivalently, if this set changed as a result of the call).
     * Returns true if this set contained the element
     * (This set will not contain the element once the call returns.)
     * @param key
     * @return
     * Time Complexity: O(1)
     */
    @Override
    public boolean remove(String key) {
        int index = hash(key);
        if(buckets[index] == null)
            return false;
        List<String> list = buckets[index];
        for(int i = 0; i< list.size(); i++){
            if(list.get(i).equals(key)){
                list.remove(i);
                size--;
                return true;
            }
        }
        return false;
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
     * We need to expand the  hashset (increase the number of buckets in the hashset)
     * if load factor of hashset is getting larger than our decided load factor.
     *
     * Load Factor = Total number of elements in hashset (or size) / Total number of buckets in hashset (or capacity).
     * Capacity is the number of number of buckets in the HashSet. Initial capacity is kept at 16.
     * The default load factor is defined as 0.75
     * (This is done in order to have O(1) time complexity during get and put operations)
     * So, total number of key-value pairs that can be entered without crossing the load factor: 0.75 * 16 = 12.
     * As soon as the 13th element is entered, the load factor of our hashset will be greater than 0.75.
     * So we will double the number of buckets in the hashset.
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
        float currentLoadFactor = (float) this.size / (float) this.capacity;
        if(currentLoadFactor < LOAD_FACTOR)
            return;

        List<String>[] oldBuckets = this.buckets;
        this.size = 0;
        this.capacity = capacity*2;
        this.buckets = new List[capacity];

        for(int i = 0; i<oldBuckets.length; i++){
            List<String> list = oldBuckets[i];
            if(list != null){
                for(int j = 0; j<list.size(); j++){
                    add(list.get(j));
                }
            }
        }
    }
}
