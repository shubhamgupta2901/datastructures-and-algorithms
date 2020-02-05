package leetcode;

/**
 * <a href="https://leetcode.com/problems/longest-palindromic-substring/"/>
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: "cbbd"
 * Output: "bb"
 *
 * NOTE: <a href="https://github.com/shubhamgupta2901/datastructures-and-algorithms/blob/master/algorithms/dp/palindromic_subsequence/LongestPalindromicSubstring.java'"/>
 */
public class LongestPalindromicSubstring {
    class Pair{
        int startIndex;
        int endIndex;

        Pair(int startIndex, int endIndex){
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        private int getLength(){
            return endIndex-startIndex+1;
        }

    }


    /**
     * Approach 1: Brute force - Time Limit Exceeds for larger strings
     * Time Complexity: O(3^n)
     * Space Complexity: O(n)
     * where n is the length of string
     */
    public String longestPalindromeApproach1(String s) {
        int length = s.length();
        Pair lpsPair = helper(s,0,length-1);
        return s.substring(lpsPair.startIndex, lpsPair.endIndex+1);
    }

    private Pair helper(String s, int startIndex, int endIndex){
        if(startIndex == endIndex)
            return new Pair(startIndex, endIndex);
        if(startIndex> endIndex)
            return new Pair(startIndex, endIndex);

        //Possible palindrome -  check recursively
        if(s.charAt(startIndex) == s.charAt(endIndex)){
            int remainingLength = endIndex-startIndex-1;
            if(helper(s, startIndex+1, endIndex-1).getLength() == remainingLength)
                return new Pair(startIndex, endIndex);
        }

        //Recursively check for smaller substring
        Pair lpsPair1 = helper(s, startIndex+1, endIndex);
        Pair lpsPair2 = helper(s, startIndex, endIndex-1);
        if(lpsPair1.getLength() > lpsPair2.getLength())
            return lpsPair1;
        return lpsPair2;

    }
}
