package leetcode;

import java.util.LinkedList;

public class InsertDeleteGetRandomO1 {

    /**
     * FIXME: All the test cases do not pass. need to rewrite solution.
     */
    static class RandomizedSet {

        private int initialCapacity = 16;
        private float loadFactor = 0.75f;

        private LinkedList<Integer>[] set;
        private int size;
        private int capacity;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            initSet(initialCapacity);
        }

        private void initSet(int capacity){
            this.capacity = capacity;
            set = new LinkedList[capacity];
            size = 0;
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            autoResize();
            LinkedList<Integer> list;
            int index = hash(val);
            if(set[index] == null){
                list = new LinkedList();
                list.add(val);
                set[index] = list;
                size++;
                return true;
            }

            list = set[index];
            for(int i = 0; i<list.size(); i++){
                if(list.get(i) == val)
                    return false;
            }

            list.add(val);
            size++;
            return true;

        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            int index = hash(val);
            if(set[index] == null)
                return false;
            LinkedList<Integer> list = set[index];
            for(int i = 0; i<list.size(); i++){
                if(list.get(i) == val){
                    list.remove(i);
                    size--;
                    return true;
                }
            }
            return false;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            LinkedList<Integer> list = null;
            while(list == null){
                int random1 = (int)(capacity*Math.random());
                list = set[random1];
            }

            int size = list.size();
            int random2 = (int)(size*Math.random());
            return list.get(random2);

        }

        private int hash(int val){
            int hashCode = Integer.hashCode(val);
            int index = hashCode%capacity;
            return index;
        }

        private void autoResize(){
            float newLoadFactor = (float)size/(float)capacity;
            if(newLoadFactor>=loadFactor){
                LinkedList<Integer>[] oldSet = set;
                initSet(capacity*2);
                for(int i = 0; i<oldSet.length; i++){
                    if(oldSet[i] != null){
                        LinkedList<Integer> list = oldSet[i];
                        for(int j = 0; j<list.size(); j++){
                            insert(list.get(i));
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        RandomizedSet randomSet = new RandomizedSet();
//        randomSet.insert(1);
//        randomSet.remove(2);
//        randomSet.insert(2);
//        randomSet.getRandom();
//        randomSet.remove(1);
//        randomSet.insert(2);
//        randomSet.getRandom();


        randomSet.insert(-1);
        randomSet.remove(-2);
        randomSet.insert(-2);
        randomSet.getRandom();
        randomSet.remove(-1);
        randomSet.insert(-2);
        randomSet.getRandom();
    }
}
