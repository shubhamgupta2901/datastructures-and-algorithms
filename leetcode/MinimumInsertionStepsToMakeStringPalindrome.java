package leetcode;

/**
 * <a href = "https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/"/>
 * Given a string s. In one step you can insert any character at any index of the string.
 * Return the minimum number of steps to make s palindrome.
 * A Palindrome String is one that reads the same backward as well as forward.
 *
 * Example 1:
 * Input: s = "zzazz"
 * Output: 0
 * Explanation: The string "zzazz" is already palindrome we don't need any insertions.
 *
 * Example 2:
 * Input: s = "mbadm"
 * Output: 2
 * Explanation: String can be "mbdadbm" or "mdbabdm".
 *
 * Example 3:
 * Input: s = "leetcode"
 * Output: 5
 * Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 *
 * Example 4:
 * Input: s = "g"
 * Output: 0
 *
 * Example 5:
 * Input: s = "no"
 * Output: 1
 *
 * Constraints:
 * 1 <= s.length <= 500
 * All characters of s are lower case English letters.
 */
public class MinimumInsertionStepsToMakeStringPalindrome {

    /**
     * Approach 1: Brute force - time limit exceeds
     * Note that this problem is exactly same to minimum number of deletions in string to make it palindrome.
     * Approach: Take string, characters at its startIndex and endIndex.
     * If characters at start and end indices are same then check how many characters need to be deleted in its substring
     * start+1 to end-1.
     * If they are not similar, then one character needs to added which gives us two options:
     * 1) suppose we add character at end index to before start index, then we check how many characters need to deleted
     * in substring(start, end-1).
     * 2) suppose we add character at start index after end index , then we check how many characters need to deleted
     *  in substring(start+1, end).
     * 3) we return 1 + min(step1, step2)
     * Time Complexity: O(2^n)
     * Space Complexity: O(2^n)
     */
    public int minInsertionsApproach1(String s) {
        return helper(s, 0, s.length()-1);
    }

    private int helper(String s, int startIndex, int endIndex){
        if(startIndex>= endIndex)
            return 0;
        if(s.charAt(startIndex) == s.charAt(endIndex))
            return helper(s, startIndex+1, endIndex-1);
        int minInsertions = 1 + Math.min(helper(s, startIndex+1, endIndex),
                helper(s,startIndex, endIndex-1));
        return minInsertions;
    }

    /**
     * Approach 2: Memoization of first approach - Accepted
     */
    public int minInsertionsApproach2(String s) {
        int length = s.length();
        Integer[][] cache = new Integer[length][length];
        return helper(s, 0, length-1, cache);
    }

    private int helper(String s, int startIndex, int endIndex, Integer[][] cache){
        if(startIndex>= endIndex)
            return 0;
        if(cache[startIndex][endIndex] != null)
            return cache[startIndex][endIndex];
        if(s.charAt(startIndex) == s.charAt(endIndex))
            return helper(s, startIndex+1, endIndex-1, cache);
        int minInsertions = 1 + Math.min(helper(s, startIndex+1, endIndex, cache),
                helper(s,startIndex, endIndex-1,cache));
        cache[startIndex][endIndex] = minInsertions;
        return minInsertions;
    }

    /**
     * Approach 3: Tabulating Bottom up DP - Accepted
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     */
    public int minInsertionsApproach3(String str) {
        int length = str.length();
        int[][] table = new int[length][length];

        for(int s = length-1; s>=0; s--){
            for(int e = s+1; e<length; e++){
                if(str.charAt(s)== str.charAt(e))
                    table[s][e] = table[s+1][e-1];
                else table[s][e] = 1+ Math.min(table[s+1][e], table[s][e-1]);
            }
        }

        return table[0][length-1];

    }
}
