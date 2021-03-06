### Contents
  * [TLDR](#tldr)
  * [Direct Access Table](#direct-access-table)
  * [Prehashing](#prehashing)
  * [Hashing](#hashing)
  * [Collision](#collision)
  * [Chaining](#chaining)
  * [Analysis](#analysis)
  * [Basic Implementation](#basic-implementation)
  * [hashCode and equals method in JAVA](#hashcode-and-equals-method-in-java)
  
### TLDR
  
* Hashmap is a data-structure that stores the data in key-value pairs. 

* Two main functions provided by hashmap are: 
  * ```void put(Object key, Object value)``` for storing values in HashMap. 
  * ```Object get(Object key)``` for retrieving values from HashMap. 

* Time complexity of both functions is O(1). key and values can be any type of datastructure.

* Internally HashMap is an **array** of objects.At a higher level we want to store a value at a particluar index. We will have a **hash function** which will convert the key (any kind of object) to an integer. This integer is called **hashCode**. A hashCode can be a very large integer, so we pass it through a **mapping function**. This mapping function will remap the hashCode to a **index** of array.

* **key** *---(hash function)--->* **hashCode** *---(mapping function)--->* **index of array.**.

* When we need to store a key-value pair, we convert the key to index and keep the value in that index. When we need to fetch a value asociated with a given key, we convert that key to index and lookup the value at that index.

* There are only finite number of hashcodes. **So two different objects(keys) can have same hashcodes.**

* Additionaly, since the hashcode will be remapped to a even smaller index, **two different hashcodes can have same index**.

* This raises a issue known as **collision**, which means two different keys are mapped to a same index. There are multiple ways to resolve collision, but the simplest is called as **chaining**. This suggests that rather than creating an array (of data type *value*),we should have an **array of linked list**. Each node of linked list represents a key-value pair, and when a new key is mapped to the same index, simply add it to the linked list of that index.

* It may appear that the storing, lookup and removing of key-value pairs is not exactly O(1), because after converting a key to index, we are traversing the whole linked list (in worst case) to store, lookup or remove a key-value pair. But 



### Direct Access Table

* One simple approach would be to implement such a data structure would be by using array to keep values. This is called **Direct Access Table**. 
* Here index of the array will be the key.
* Whatever key is not used, we will keep null there.
* Running Time: Insert: O(1), Search: O(1) and Delete: O(1)
* **What's bad?** : 
  1. **Keys can be any data type**: It will be hard to associate every key with an integer.
  2. **Gigantic memory hog**: If the set of possible keys will be very large, we will have to create a really big array, occupying more space.
  
### Prehashing
  
* Soultion to keys may not be integers: Map the keys non-negative integers to keep them in Direct Access Table. This is called 
**Prehashing**. (This can be done because every object can be written down as array of bits which is a string).
 
 * In Java, prehashing is done using the ```hashCode()``` method.
 
 * Prehash function should not change values overtime. Suppose if you have an item which we wish to put in the hashmap. We compute the hash prehash of the key and then we put the item in the direct access tabl with index with prehash(key). And we want to search for that item in the table, and we call prehash(key) to get the index of the table in which the item is kept, Now if the value of prehash(key) change, we can not find the item.
 
### Hashing

* This is solution to gigantic size of the Direct Access Table. With hashing we will try to reduce the universe of all keys down to reasonable size for the table.

* Suppose there are n possible keys, (i.e. n key-value pairs need to be stored in the hashmap). We will try to reduce them to a resonable size m for table. i.e the size of array will be m.

* We would like to somehow map these n possible keys to a smaller set using a hash function.


### Collision:

* The number of slots in the table is wat smaller than  the possible number of keys.

* So there are going to be two keys k<sub>i</sub> and k<sub>j</sub> which are different keys, but they map to same slot in the hash map.

* i.e hash(k<sub>i</sub>) = hash(k<sub>j</sub>) but k<sub>i</sub> != k<sub>j</sub>


### Chaining

* Technique to deal with collision.

* If you have multiple items whose keys are generating same hash index, store all the itesm as a list to that index.

* So each slot of the array is actually a list which can contain multiple items.

* There will be a lot of keys which will map to the same slot. 
* Worst case of retrieving an item: O(n) This will be true for any hashing scheme we use.

* But in practice, hashing works really well. Good hash() nicely distributes the items in hash map and most of the lists in the indices will have constant length.


### Analysis

* Expected length of one chain: Assuming thatt each key is equally likely to be hashed to any slot of the table, For n keys which we need to store in a table and m slots in the map, each chain length will be n/m. This is known as the load factor of Hash Map. (loadFactor = n/m).

* Running time of get and put operation: 
  * Constant time to calculate hash function + Time taken to insert/ search an element in the list of index generated by hash function (n/m or loadFactor).
  * So time complexity: O(1+loadFactor).  
  * For cases when the loadFactor < 1. When the size of table(number of buckets) is larger than number of keys: O(1)
  * For cases when the loadFactor >= 1. O(loadFactor) or constant time.
  
###  Basic Implementation

* Here is a basic implementation of a Hashmap which takes String keys and Object values. This is not thread safe.

* The HashMap has following methods:

```java
public interface Map {

    int size();

    void put(String key, Object value);

    Object get(String key);

    void remove(String key);

}
```

```java
package playground.hashmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubham on 10/10/18
 */
public class HashMap implements Map {

    class Pair {
        private String key;
        private Object value;

        public Pair(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    private List<Pair>[] buckets;
    private int capacity;
    private int size;

    public HashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = new List[capacity];
        this.size = 0;
    }

    /**
     * Hashcode of a key is a prehash. This will covert the object into array of bits, which is essentially an integer.
     * But since the set of possible hashcodes is very large, Using this function we would like to map these possible hashcodes to
     * a smaller set which is the capacity of this hashmap.
     * Good hash functions uniformly distribute the keys, in hashmap and most of the list in indices of buckets will have same length.
     * For the demonstration purposes we are simply taking the modulo of hashcode to the capacity of hashmap.
     *
     * @param key
     * @return
     */
    private int hash(String key) {
        return key.hashCode() % capacity;
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the key is already associated with a value, replace it
     *
     * @param key
     * @param value
     */
    @Override
    public void put(String key, Object value) {
        int index = hash(key);
        List<Pair> list;

        list = buckets[index];
        //If no Pair has been inserted in this index of buckets, create a new list
        if (list == null) {
            list = new ArrayList<>();
            list.add(new Pair(key, value));
            buckets[index] = list;
            size++;
        }
        //  A list of Pairs already exist in this index of buckets
        else {
            //Checking if the key already exists in the buckets, In this case we will have to replace the value.
            boolean found = false;
            for (Pair pair : list) {
                if (key.equals(pair.key)) {
                    pair.value = value;
                    found = true;
                    break;
                }

            }
            // The key does not exist in this index of buckets(Essentially, its not in the HashMap), Append a new Pair in the list
            if (!found) {
                list.add(new Pair(key, value));
                size++;
            }

        }

    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key
     * @return
     */
    @Override
    public Object get(String key) {
        int index = hash(key);
        //If there is no list in this index of buckets, return null
        List<Pair> list = buckets[index];
        if (list == null)
            return null;

        // If the list exists, find the Pair with given key
        for (Pair pair : list) {
            if (key.equals(pair.key))
                return pair.value;
        }

        return null;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key
     */
    @Override
    public void remove(String key) {
        int index = hash(key);
        List<Pair> list = buckets[index];
        //There is no list at the index of the buckets
        if (list == null)
            return;

        //Could not use this code due to ConcurrentModificationException which occurs when we try to modify a list while using an iterator. For each loop uses an iterator inside.
        /*for(Pair pair: list){
            if(pair.key.equals(key)){
                list.remove(pair);
                    size--;

            }
        }*/
        // If the list contains the pair with given key, remove it.
        for (int i = 0; i < list.size(); i++) {
            Pair pair = list.get(i);
            if (pair.key.equals(key)) {
                list.remove(key);
                size--;
                break;
            }
        }
    }

}
```

* Here is this being is use:

```java
public class Driver {

    public static void main(String[] args) {
        HashMap map = new HashMap(4);

        String key;
        int value;

        System.out.println("Adding 10 key value pairs");
        for(int i = 0; i<10; i++){
            char c = (char) ('a'+i);
            key = Character.toString(c);
            map.put(key,i);
        }

        System.out.println("Reading key value pairs from map");
        for (int i =0; i<10; i++){
            char c = (char) ('a'+i);
            key = Character.toString(c);
            value = (int)map.get(key);
            System.out.println("KEY: "+ key+ " | VALUE:  "+value);
        }

        key = "d";
        value = 100;

        System.out.println("Replacing value of existing key: "+ key + " with value: "+ value);
        map.put(key,value);
        System.out.println("KEY: "+ key+ " | VALUE:  "+value);

        System.out.println("Removing all keys from hashmap");
        for (int i =0; i<10; i++){
            char c = (char) ('a'+i);
            key = Character.toString(c);
            map.remove(key);
        }

        System.out.println("Size of map after removing all keys: " + map.size());

    }
}
```

OUTPUT: 
```
Adding 10 key value pairs
Reading key value pairs from map
KEY: a | VALUE:  0
KEY: b | VALUE:  1
KEY: c | VALUE:  2
KEY: d | VALUE:  3
KEY: e | VALUE:  4
KEY: f | VALUE:  5
KEY: g | VALUE:  6
KEY: h | VALUE:  7
KEY: i | VALUE:  8
KEY: j | VALUE:  9
Replacing value of existing key: d with value: 100
KEY: d | VALUE:  100
Removing all keys from hashmap
Size of map after removing all keys: 0
```
### hashCode and equals method in JAVA

* In Java ```equals()``` and ```hashCode()``` methods are present in the ```java.lang.Object``` class.

* The iternal implemenatation of Hashmap uses ```hashCode()``` to generate an integer value corresponding to an object (this object is key in the Hashmap). This is prehashing. Once this hashCode is generated, using the internal implementation of ```hash()``` (which is actual hashing) this hashcode is mapped to a index of a bucket in the Hashmap. 

* Whenever ```hashCode()``` is invoked on the same object more than once during an execution of a Java application,  the ```hashCode()``` must consistently return the same integer. This integer need not remain consistent from one execution of an application to another execution of the same application.

* ```equals()``` method is generally used to compare the logical equivalance of two objects. The default implementation of ```equals()``` in ```Object``` class checks if the object references of the two objects being compared are equal, that is if both the objects being compared are the exact same object.(both the references of objects point to same memory location in heap)

* In the internal implementation of  Hashmap ```equals()``` method is used to find if such a key already exists in that bucket, if not found then a new key-value pair is created and is stored in the same bucket. If however, the key is found then the value associated with this key is simply replaced.

* An important point to keep in mind in the contract of hashCode is: **If two objects are equal according to the ```equals(Object)``` method, then calling the  ```hashCode()``` method on each of  the two objects must produce the same integer result.** (However it is *NOT* required that if two objects are unequal according to the ```java.lang.Object#equals(java.lang.Object)```method, then calling the ```hashCode()``` method on each of the two objects must produce distinct integer results.) 

* As long as we use the default implementation of these two methods in a class, everything works fine. However it requires special attention when we want to override  ```equals()``` (to establish a specific logical equivalence) in a class whose objects we intend to use as a key for a HashMap. 

* If two objects which return true when we call ```obj1.equals(obj2)``` or ```obj2.equals(obj1)``` and still return different hashCodes on calling ```obj1.hashCode()``` or ```obj2.hashCode()```. Then using Hashmap with these objects as key would create issues. Like the following example:

```java
public final class CreditCard {
    private final int number;

    public CreditCard(int number) {
        this.number = number;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CreditCard)) {
            return false;
        }
        CreditCard cc = (CreditCard) o;
        return cc.number == number;
    }

    public static void main(String[] args) {
        java.util.Map<CreditCard, String> m = new java.util.HashMap<CreditCard, String>();
        m.put(new CreditCard(100), "4111111111111111");
        System.out.println(m.get(new CreditCard(100)));
    }
}
```

OUTPUT:

```
null
```
* The cause of this erroneous behavior is that the ```CreditCard``` class overrides the ```equals()``` method but fails to override the ```hashCode()``` method. Consequently, the default ```hashCode()``` method returns a different value for each object, even though the objects are logically equivalent; these differing values lead to examination of different buckets in the HashMap, which prevents the ```get()``` method from finding the intended value.

* Therefore **it is generally necessary that classes that override the ```Object.equals()``` method also  override the ```Object.hashCode()``` method.** The ```java.lang.Object``` class requires that any two objects that compare equal using the ```equals()``` method must produce the same integer result when the ```hashCode()``` method is invoked on the objects.

* A solution to above code looks like this: 

```java
public final class CreditCard {
  private final int number;
   
  public CreditCard(int number) {
    this.number = number;
  }
 
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof CreditCard)) {
      return false;
    }
    CreditCard cc = (CreditCard)o;
    return cc.number == number;
  }
 
  public int hashCode() {
    int result = 17;
    result = 31 * result + number;
    return result;
  }
 
  public static void main(String[] args) {
    Map<CreditCard, String> m = new HashMap<CreditCard, String>();
    m.put(new CreditCard(100), "4111111111111111");
    System.out.println(m.get(new CreditCard(100)));
  }
}
```
OUTPUT:

```
4111111111111111
```
