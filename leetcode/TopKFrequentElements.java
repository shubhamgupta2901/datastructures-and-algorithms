package leetcode;

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
public class TopKFrequentElements {

    class Pair implements Comparable{
        int num;
        int frequency;

        Pair(int num, int frequency){
            this.num = num;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Object o) {
            return this.frequency - ((Pair)o).frequency;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        PriorityQueue<Pair> minHeap = new PriorityQueue<>();

        for(int i = 0; i<nums.length; i++){
            if(!frequencyMap.containsKey(nums[i]))
                frequencyMap.put(nums[i], 1);
            else frequencyMap.put(nums[i], frequencyMap.get(nums[i])+1);
        }


        for(Map.Entry<Integer, Integer> entry: frequencyMap.entrySet()){
            Pair pair = new Pair(entry.getKey(),entry.getValue());
            if(minHeap.size()<k)
                minHeap.add(pair);
            else if(minHeap.peek().frequency < pair.frequency){
                minHeap.poll();
                minHeap.add(pair);
            }
        }

        List<Integer> topKFrequentElements = new ArrayList<>();
        while(!minHeap.isEmpty())
            topKFrequentElements.add(minHeap.poll().num);
        return topKFrequentElements;

    }
}
