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

    /**
     * Approach 2: DP Memoization - Accepted
     *
     */
    public String longestPalindromeApproach2(String s) {
        int length = s.length();
        Pair[][] cache = new Pair[length][length];
        Pair lpsPair = helper(s,0,length-1, cache);
        return s.substring(lpsPair.startIndex, lpsPair.endIndex+1);
    }

    private Pair helper(String s, int startIndex, int endIndex, Pair[][]cache){
        if(startIndex == endIndex)
            return new Pair(startIndex, endIndex);
        if(startIndex> endIndex)
            return new Pair(startIndex, endIndex);
        if(cache[startIndex][endIndex]!=null)
            return cache[startIndex][endIndex];

        //Possible palindrome -  check recursively
        if(s.charAt(startIndex) == s.charAt(endIndex)){
            int remainingLength = endIndex-startIndex-1;
            if(helper(s, startIndex+1, endIndex-1, cache).getLength() == remainingLength){
                cache[startIndex][endIndex]= new Pair(startIndex, endIndex);
                return cache[startIndex][endIndex];
            }
        }

        //Recursively check for smaller substring
        Pair lpsPair1 = helper(s, startIndex+1, endIndex,cache);
        Pair lpsPair2 = helper(s, startIndex, endIndex-1,cache);
        cache[startIndex][endIndex] = lpsPair1.getLength() > lpsPair2.getLength() ? lpsPair1 : lpsPair2;
        return cache[startIndex][endIndex];

    }

    /**
     *
     * Approach 3: DP tabulation - Accepted.
     * Note we could also use the logic of 2-d array of Pair as we used in memoizatiton.
     * But a better looking solution struck me, where we only maintain a boolean 2d array.
     * table[s][e] would signify that if the substring starting from index s and ending in index e
     * is a palindrome.
     * While filling the table bottom up,simultaneously we also keep track of the maximum length of
     * palindrome encountered, and what are its starting and ending index.
     *
     */
    public String longestPalindromeApproach3(String str) {
        int length = str.length();
        if(str.length() == 0 || str.length()==1)
            return str;

        boolean [][] table = new boolean[length][length];

        for(int i = 0; i<length; i++)
            table[i][i] = true;

        int maxLength = 1;
        int start = 0, end = 0;
        for(int s = length-1; s>=0; s--){
            for(int e = s+1; e<length; e++){
                if(str.charAt(s) == str.charAt(e)){
                    if(table[s+1][e-1] == true || e-s-1 == 0){
                        table[s][e] = true;
                        if(e-s+1> maxLength){
                            maxLength = e-s+1;
                            start = s;
                            end = e;
                        }
                    }
                }
            }
        }

        return str.substring(start,end+1);
    }
}
