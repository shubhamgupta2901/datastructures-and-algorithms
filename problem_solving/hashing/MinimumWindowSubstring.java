package problem_solving.hashing;

import java.util.HashMap;

/**
 * Given two strings s and t, return the minimum window in s which will contain all the characters in t.
 * If there is no such window in s that covers all characters in t, return the empty string "".
 * Note that If there is such a window, it is guaranteed that there will always be only one unique minimum window in s.
 *
 * Example 1:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 *
 * Example 2:
 * Input: s = "a", t = "a"
 * Output: "a"
 *
 * Constraints:
 * 1 <= s.length, t.length <= 105
 * s and t consist of English letters.
 *
 * Follow up: Could you find an algorithm that runs in O(n) time?
 */
public class MinimumWindowSubstring {
    /**
     * Approach 1: Starting at every index in str1, we try to find the minimum length substring that has all characters of str2.
     * We return the minimum substring found from any of the index.
     * The idea is to create a hashmap, which will have frequency of all characters in str2.
     * while creating every substring starting at index 0, we can keep removing the frequency of characters from map, and as soon as the map becomes empty,
     * we can say that this substring contains all characters of str2.
     * Now we try to find a substring starting at index 1 and so on
     * Time Complexity: O(n^2+mn), where n is length of str1, and m is length of str2.
     *  For every index  we refill the map in O(m) time and in O(n) we find if it meets the condition.
     * Space Complexity: O(m) at most map will have all chars of str2.
     */
    public String minWindow(String s, String t) {
        int start = -1, end = -1;
        int minLen = Integer.MAX_VALUE;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i<s.length(); i++){
            map.clear();
            for(int k = 0; k<t.length(); k++){
                char c = t.charAt(k);
                int count = map.getOrDefault(c,0);
                map.put(c, count+1);
            }
            for(int j = i; j<s.length(); j++){
                char c = s.charAt(j);
                int count = map.getOrDefault(c, 0);
                if(count == 1)
                    map.remove(c);
                else if (count>1)
                    map.put(c, count-1);

                if(map.isEmpty()){
                    int len = j-i+1;
                    if(len<minLen){
                        start = i;
                        end = j;
                        minLen = len;
                    }
                    break;
                }
            }
        }

        if(start== -1)
            return "";
        else return s.substring(start, end + 1);
    }

    /**
     * Approach 2: Two Pointer Approach
     * The question asks us to return the minimum window from the string S which has all the characters of the string T.
     * Let us call a window desirable if it has all the characters from T. We can use a simple sliding window approach to solve this problem.
     * In any sliding window based problem we have two pointers. One right pointer whose job is to expand the current window
     * and then we have the left pointer whose job is to contract a given window. At any point in time only one of these pointers move and the other one remains fixed.
     * The solution is pretty intuitive. We keep expanding the window by moving the right pointer.
     * When the window has all the desired characters, we contract (if possible) and save the smallest window till now.
     * The answer is the smallest desirable window.
     *
     * Algorithm
     * 1. We start with two pointers, left and right initially pointing to the first element of the string SS.
     * 2. We use the right pointer to expand the window until we get a desirable window i.e. a window that contains all of the characters of TT.
     * 3. Once we have a window with all the characters, we can move the left pointer ahead one by one.
     * If the window is still a desirable one we keep on updating the minimum window size.
     * 4. If the window is not desirable any more, we repeat step 2 onwards.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public String minWindow2(String A, String B) {
        if(A == null || B == null)
            return "";
        if(A.length()< B.length())
            return "";

        HashMap<Character, Integer> aMap = new HashMap<>();
        HashMap<Character, Integer> bMap = new HashMap<>();
        for(int i = 0; i<B.length(); i++){
            char c = B.charAt(i);
            int freq = bMap.getOrDefault(c, 0);
            bMap.put(c, freq+1);
        }

        int start = -1, end = -1;
        int minLen = Integer.MAX_VALUE;
        int i = 0, j = 0;
        int count = 0;
        while(j<A.length() || count== B.length()){
            if(count == B.length()){
                int len = j-i;
                if(len<minLen){
                    start = i;
                    end = j;
                    minLen = len;
                }
                char d = A.charAt(i);
                int freqA = aMap.getOrDefault(d, 0);
                int freqB = bMap.getOrDefault(d, 0);
                if(freqA == freqB)
                    count--;
                aMap.put(d, freqA-1);
                i++;
            }else{
                char c = A.charAt(j);
                int freqA = aMap.getOrDefault(c, 0);
                int freqB = bMap.getOrDefault(c, 0);
                if(freqA<freqB)
                    count++;
                aMap.put(c, freqA+1);
                j++;
            }
        }

        if(start == -1)
            return "";
        return A.substring(start, end);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring driver = new MinimumWindowSubstring();
        String output = driver.minWindow2("ADOBECODEBANC", "ABC");
        System.out.println(output);
    }

}
