package grokking_ds_patterns.top_k_elements;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/top-k-frequent-elements/>
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 *
 */
public class TopKFrequentNumbers {

    /**
     * Approach: We first need to know the frequency of each number, for which we can use a HashMap.
     * Once we have the frequency map, we can use a Min Heap to find the ‘K’ most frequently occurring number.
     * In the Min Heap, instead of comparing numbers we will compare their frequencies
     * in order to get frequently occurring numbers.
     * 
     * Also, We don't need to store both the number and its frequency as a Pair in the heap.
     * Rather, whenever we only store the number in Heap, and when we need to know a number's frequency,
     * we do a look-up in hashmap which takes constant time.
     *
     * Time Complexity: O(Nlogk), where N is the length of numbers.
     * We count the frequency of each number in O(N) time, then we add N numbers to the heap, each in O(logk) time.
     * Finally, we pop from the heap up to k times. As k≤N, this is O(Nlogk) in total.
     * Space Complexity: O(N) for hashMap, O(k) for heap and O(N) for output list.
     */
    public List<Integer> topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i<nums.length; i++){
            int count = map.getOrDefault(nums[i], 0);
            map.put(nums[i], ++count);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((num1, num2)->map.get(num1)-map.get(num2));
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            if(queue.size()<k)
                queue.add(entry.getKey());
            else if(entry.getValue()-map.get(queue.peek())>0){
                queue.remove();
                queue.add(entry.getKey());
            }
        }
        List<Integer> list = new LinkedList<>();
        while(!queue.isEmpty()){
            list.add(0,queue.poll());
        }
        return list;
    }
}
