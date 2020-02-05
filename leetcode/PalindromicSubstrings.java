package leetcode;

/**
 * <a href="https://leetcode.com/problems/palindromic-substrings/"/>
 * Given a string, your task is to count how many palindromic substrings in this string.
 * The substrings with different start indexes or end indexes are counted as different
 * substrings even they consist of same characters.
 *
 * Example 1:
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
public class PalindromicSubstrings {
    /**
     * Approach 1: Bottom down dynamic programming. - Accepted
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     * TODO: "expand around center" approach mentioned in lc solutions which takes O(n^2) time and O(1) space
     */
    public int countSubstrings(String str) {
        int length = str.length();
        if(length==0 || length==1)
            return length;
        boolean[][] table = new boolean[length][length];
        int count = 0;
        for(int i = 0; i<length; i++){
            table[i][i] = true;
            count++;
        }

        for(int s = length-1; s>=0; s--){
            for(int e = s+1; e<length; e++){
                if(str.charAt(s) == str.charAt(e)){
                    if(table[s+1][e-1] == true || e-s-1 == 0){
                        table[s][e] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
