package leetcode;


import java.util.*;

/**
 * <a href="https://leetcode.com/problems/3sum/"/>
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Note: The solution set must not contain duplicate triplets.
 *
 * Example:
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class ThreeSum {

    /**
     * Approach 1: Time limit exceeds. passes 311/313 cases.
     * Idea is pretty simple, find all the combinations in an array that add up to 0.
     * To remove the duplicates we use a HashMap with entry 'Key'. Key is a wrapper class which contains the
     * sorted list of triplet.
     * One very important concept is to override the equals and hashcode method for Key, because HashMap will be looking
     * at these two methods to find if two entries are same.
     * Internal working of hashmap tells us that we need, it uses the hash function to find the bucket, that means
     * two objects of Key with same content need to have same hashcode.
     * Once the correct bucket is identified, we also need to override equals method and tell if the contents of two Key
     * objects(the triplet in sorted order) is same then the two objects are equal.
     *
     * Time Complexity: O(n^3)
     */
   class Approach1{
       class Key {
           List<Integer> list = new ArrayList<>();

           Key(int i1, int i2, int i3){
               list.add(i1);
               list.add(i2);
               list.add(i3);
               Collections.sort(list);
           }

           @Override
           public boolean equals(Object obj) {
               if(obj instanceof Key == false)
                   return false;
               Key key = (Key) obj;
               for(int i = 0; i<3; i++){
                   if(this.list.get(i) != key.list.get(i))
                       return false;
               }
               return true;
           }

           @Override
           public int hashCode() {
               return Objects.hash(this.list.get(0), this.list.get(1), this.list.get(2));
           }
       }

       int[] nums;
       public List<List<Integer>> threeSum(int[] nums) {
           this.nums = nums;
           HashSet<Key> hashSet = new HashSet<>();
           for(int i = 0; i<nums.length; i++){
               for(int j = i+1; j<nums.length; j++){
                   for(int k = j+1; k<nums.length; k++){
                       if(nums[i] + nums[j] + nums[k] == 0){
                           hashSet.add(new Key(nums[i] , nums[j] , nums[k]));
                       }
                   }
               }
           }

           List<List<Integer>> sums = new ArrayList<>();
           Iterator<Key> iterator = hashSet.iterator();
           while(iterator.hasNext()){
               sums.add(iterator.next().list);
           }
           return sums;

       }
   }

    /**
     * Approach 2: Wrong answer for one really large array, passes 312/313 cases.
     * Don't understand why this solution is not working, my only guess in may adding two numbers is overflowing for really
     * large integers.
     *
     * Pretty similar to Approach 2 but optimising the finding out of triplets.
     * For each number x to find a triplet [x,y,z] such that x+y+z = 0,
     * we need to find two numbers y and such that y+x = -z.
     * Using the two sum approach from here where we use a hashset to find if two numbers equal to a target.
     *
     * Time Complexity: O(n^2)
     */
    class Approach2 {
        class Key {
            List<Integer> list = new ArrayList<>();

            Key(int i1, int i2, int i3){
                list.add(i1);
                list.add(i2);
                list.add(i3);
                Collections.sort(list);
            }

            @Override
            public boolean equals(Object obj) {
                if(obj instanceof Key == false)
                    return false;
                Key key = (Key) obj;
                for(int i = 0; i<3; i++){
                    if(this.list.get(i) != key.list.get(i))
                        return false;
                }
                return true;
            }

            @Override
            public int hashCode() {
                return Objects.hash(this.list.get(0), this.list.get(1), this.list.get(2));
            }
        }

        int[] nums;
        public List<List<Integer>> threeSum(int[] nums) {
            this.nums = nums;
            HashSet<Key> hashSet = new HashSet<>();
            for(int i = 0; i<nums.length; i++){
                int target = -1 * nums[i];
                HashSet<Integer> targetSet = new HashSet<Integer>();
                for(int j = i+1; j<nums.length; j++){
                    if(targetSet.contains(target-nums[j]))
                        hashSet.add(new Key(nums[i], nums[j], target-nums[j]));
                    targetSet.add(nums[j]);
                }
            }

            List<List<Integer>> sums = new ArrayList<>();
            Iterator<Key> iterator = hashSet.iterator();
            while(iterator.hasNext()){
                sums.add(iterator.next().list);
            }
            return sums;

        }
    }

}
