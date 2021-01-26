package problem_solving.hashing;

import java.util.*;

/**
 * Given a string A, find the length of the longest substring without repeating characters.
 * Note: Users are expected to solve in O(N) time complexity.
 *
 * Problem Constraints
 * 1 <= size(A) <= 106
 * String consists of lowerCase,upperCase characters and digits are also present in the string A.
 *
 * Input Format:
 * Single Argument representing string A.
 *
 * Output Format:
 * Return an integer denoting the maximum possible length of substring without repeating characters.
 *
 * Example
 * Input: A = "abcabcbb"
 * Output: 3 (Substring "abc" or "bca" or "cab" is the longest substring without repeating characters in string A.)
 *
 */
public class LongestSubstringWithoutRepeat {

    /**
     * Approach:
     * A Brute force approach would be to consider each substring, and see if it has all unique characters.
     * We keep track of max length of every such substring.
     * Depending on the approach this could take either O(n^4) or O(n^3) time.
     * O(n^4): O(n^2) for creating a substring, and for each character we traverse the substring to see if it repeated.
     *          This would take O(n^2) time for every substring
     * O(n^3): O(n^2) for creating a substring, and for each substring we keep track of characters in a hashset
     *          If a character is already present in set, we can say this substring does not have unique characters, else
     *          we enter the character in the substring, and look for next char. This would take O(n) time and O(n) space
     *          for each substring
     * Optimisation on Brute Force: This approach is optimisation on our O(n^3) approach, where we keep adding elements to
     *  the set as we are creating a substring.
     *  Rather than checking for each substring, we are saying what is the maximum length of a substring starting with index
     *  i which has all unique characters. At the end we return the max(max length substring with unique characters starting from 0..n)
     *
     *  Time Complexity: O(n^2) as we are constructing the substring and hashset together
     *  Space Complexity: O(n) for creating the hashset which will contain at most all chars in string
     */
    public int approach1(String str){
        int maxLen = 0;
        HashSet<Character> set = new HashSet<>();
        for(int i = 0; i< str.length(); i++){
            int j;
            set.clear();
            for( j = i; j<str.length(); j++){
                if(set.contains(str.charAt(j))){
                    int len = j-i;
                    maxLen = Math.max(maxLen, len);
                    break;
                }
                else {
                    set.add(str.charAt(j));
                }
            }
            if(j== str.length()){
                int len = j-i;
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeat driver = new LongestSubstringWithoutRepeat();
        int output = driver.approach1("");
        System.out.println(output);
    }
}
