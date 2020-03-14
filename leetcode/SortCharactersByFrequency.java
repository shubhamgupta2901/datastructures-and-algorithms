package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/sort-characters-by-frequency/"/>
 * Given a string, sort it in decreasing order based on the frequency of characters.
 *
 * Example 1:
 * Input: "tree"
 * Output: "eert"
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 *
 * Example 2:
 * Input: "cccaaa"
 * Output: "cccaaa"
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 *
 * Example 3:
 * Input: "Aabb"
 * Output: "bbAa"
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 *
 */
public class SortCharactersByFrequency {

    /**
     * Approach: Create a Max-Heap, and insert characters by ordering of their frequency.
     * (for which I have used a map to which has character-frequency pairs stored in them)
     *
     * Time Complexity: O(D∗logD) where ‘D’ is the number of distinct characters in the input string.
     * This means, in the worst case, when all characters are unique the time complexity of the
     * algorithm will be O(N∗logN) where ‘N’ is the total number of characters in the string.
     * Space Complexity: O(N)
     */
    public String frequencySort(String s) {
        if(s == null || s.length() == 0)
            return s;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i = 0; i<s.length(); i++){
            int count = map.getOrDefault(s.charAt(i),0);
            map.put(s.charAt(i),++count);
        }

        PriorityQueue<Character> queue = new PriorityQueue<>((c1, c2)->map.get(c2)-map.get(c1));
        for(Map.Entry<Character,Integer> entry: map.entrySet()){
            queue.add(entry.getKey());
        }

        StringBuilder output = new StringBuilder();
        while(!queue.isEmpty()){
            char c = queue.poll();
            int frequency = map.get(c);
            for(int i = frequency; i>0; i--)
                output.append(c);
        }
        return output.toString();
    }
}
