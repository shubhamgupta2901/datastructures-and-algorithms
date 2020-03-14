package leetcode;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/top-k-frequent-words/"/>
 * Given a non-empty list of words, return the k most frequent elements.
 * Your answer should be sorted by frequency from highest to lowest.
 * If two words have the same frequency, then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 *     Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *     with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 *
 * Follow up: Try to solve it in O(n log k) time and O(n) extra space.
 */
public class TopKFrequentWords {
    /**
     * Approach: We can use a Min Heap to find ‘K’ most frequent words.
     * While iterating through all words, if a string (str) is has greater frequency
     * than the top string of the Min heap, we will remove that top string from the heap and add str
     * to always keep the most frequent strings in heap
     *
     * Time Complexity: O(Nlogk), where N is the length of words.
     * We count the frequency of each word in O(N) time, then we add N words to the heap, each in O(logk) time.
     * Finally, we pop from the heap up to k times. As k≤N, this is O(Nlogk) in total.
     * Space Complexity: O(N) for hashMap, O(k) for heap and O(N) for output list.
     */
    private HashMap<String, Integer> map;
    public List<String> topKFrequent(String[] words, int k) {
        map = new HashMap<String, Integer>();
        for(int i = 0; i<words.length; i++){
            int count = map.getOrDefault(words[i],0);
            map.put(words[i], ++count);
        }

        PriorityQueue<String> heap = new PriorityQueue<>((str1, str2)->compare(str1,str2));
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if(heap.size()<k)
                heap.add(entry.getKey());
            else if(compare(heap.peek(),entry.getKey()) < 0 ){
                heap.poll();
                heap.add(entry.getKey());
            }
        }
        List<String> list = new LinkedList<>();
        while(!heap.isEmpty())
            list.add(0,heap.poll());
        return list;
    }

    private int compare(String str1, String str2){
        int compare = map.get(str1) - map.get(str2);
        if(compare == 0)
            return str2.compareTo(str1);
        return compare;
    }
}
