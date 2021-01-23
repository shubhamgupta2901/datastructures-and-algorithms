package problem_solving.strings;

import javafx.util.Pair;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 *
 * Example 3:
 * Input: s = "a"
 * Output: "a"
 *
 * Example 4:
 * Input: s = "ac"
 * Output: "a"
 *
 * Constraints:
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters (lower-case and/or upper-case),
 */
public class LongestPalindromicString {
    /**
     * Approach 1: Brute Force
     * For all substrings of string, find if it is a palindrome;
     * Time Complexity: O(n^3) : n^2  for creating substrings, and n for finding if it is a palindrome.
     * Space Complexity: O(1)
     */
    static class Approach1{
        public String longestPalindrome(String str) {
            if(str == null || str.length()<=1)
                return str;
            int size = str.length();
            int maxLen = 0;
            int start = -1, end = -1;
            for(int i = 0; i<size; i++){
                for(int j = i; j<size; j++){
                    if(isPalindrome(str, i, j)){
                        int len = j - i +1;
                        if(len>maxLen){
                            maxLen = len;
                            start = i;
                            end = j;
                        }
                    }
                }
            }
            return str.substring(start, end+1);
        }

        private boolean isPalindrome(String str, int start, int inclusiveEnd){
            while(start<inclusiveEnd){
                if(str.charAt(start)!= str.charAt(inclusiveEnd))
                    return false;
                start++;
                inclusiveEnd--;
            }
            return true;
        }
    }

    /**
     * Approach 2: Expanding around the center approach
     * The idea is simple: A palindrome can be either of even length("123321") or odd length("12321")
     * So for every index we check if the index is center of a palindrome for example: "12321" (center is at 3)
     * So i being center we ask if i-1 and i+1 indices are same or not (and keep decrementing left index, and incrementing right index)
     * Another case can be when the center of palindrome lies between two indices: "123321" (center is between two 3s)
     * For this case when the center of palindrome lies between two indices and not at one index,
     * we also need to check if i-1 and i are same or not, (and keep decrementing left index, and incrementing right index)
     *
     * We keep checking this for every index, till we find a point(an index, or a point between two indices)
     * which is center of the largest palindrome
     * Time Complexity: O(n^2):
     * At every index we are calling the check function twice : 2n
     * check function takes linear time to expand in both directions: n
     * So Time Complexity: O(n*2n) = O(n^2)
     */
    static class Approach2{
        int start = 0, incEnd = 0;
        int maxLen = 0;
        public String longestPalindrome(String str) {
            if(str == null || str.length()<=1)
                    return str;
            int size = str.length();
            for(int i = 0; i<size; i++){
                check(str, i-1, i);
                check(str, i-1, i+1);
            }
            return str.substring(start, incEnd+1);
        }

        private void check(String str, int i, int j){
            while(i>=0 && j<str.length() && str.charAt(i)== str.charAt(j)){
                i--;
                j++;
            }

            int len = j-i-1;
            if(len>maxLen){
                maxLen = len;
                start = i+1;
                incEnd = j-1;
            }
        }
    }

    public static void main(String[] args) {
        LongestPalindromicString.Approach2 driver = new Approach2();
        String palindrome = driver.longestPalindrome("4123321");
        System.out.println(palindrome);
    }

}
