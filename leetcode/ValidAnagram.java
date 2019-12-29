package leetcode;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/valid-anagram/
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 * Note: You may assume the string contains only lowercase alphabets.
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length()!= t.length())
            return false;

        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c))
                map.put(c,map.get(c)+1);
            else map.put(c,1);
        }
        for(int i = 0; i<t.length(); i++){
            char c = t.charAt(i);
            if(!map.containsKey(c))
                return false;
            int occurrences = map.get(c);
            if(occurrences == 1)
                map.remove(c);
            else map.put(c,occurrences-1);
        }
        return map.isEmpty();
    }
}
