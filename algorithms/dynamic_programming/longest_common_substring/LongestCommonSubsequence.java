package algorithms.dynamic_programming.longest_common_substring;

/**
 * Given two strings ‘s1’ and ‘s2’, find the length of the longest subsequence which is common in both the strings.
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without
 * changing the order of the remaining elements.
 *
 * Example 1:
 * Input: s1 = "abdca"
 *        s2 = "cbda"
 * Output: 3
 * Explanation: The longest common subsequence is "bda".
 *
 * Example 2:
 * Input: s1 = "passport"
 *        s2 = "ppsspt"
 * Output: 5
 * Explanation: The longest common subsequence is "psspt".
 */
public class LongestCommonSubsequence {
    /**
     * Approach 1: Brute force
     * Notice the similarity with {@link algorithms.dynamic_programming.palindromic_subsequence.LongestPalindromicSubsequence}
     *
     * A basic brute-force solution could be to try all subsequences of ‘s1’ and ‘s2’ to find the longest one.
     * We can match both the strings one character at a time. So for every index ‘i’ in ‘s1’ and ‘j’ in ‘s2’ we must choose between:
     *  If the character s1[i] matches s2[j], we can recursively match for the remaining lengths.
     *  If the character s1[i] does not match s2[j], we will start two new recursive calls by skipping one character separately from each string.
     * Time Complexity: O(2^(m+n))
     * Space Complexity: O(m+n) for recursion stack
     *
     */
    public int longestCommonSubsequenceApproach1(String text1, String text2) {
        return helper(text1, text2, 0, 0);
    }

    private int helper(String str1, String str2, int i, int j){
        if(i == str1.length() || j == str2.length())
            return 0;

        if(str1.charAt(i) == str2.charAt(j))
            return 1+ helper(str1, str2, i+1, j+1);
        int lcs1 = helper(str1, str2, i+1, j);
        int lcs2 = helper(str1, str2, i, j+1);
        return Math.max(lcs1, lcs2);
    }

    /**
     * Approach 2: Top Down DP optimisation using memoization
     */
    public int longestCommonSubsequence(String text1, String text2) {
        Integer[][] cache = new Integer[text1.length()][text2.length()];
        return helper(text1, text2, 0, 0, cache);
    }

    private int helper(String str1, String str2, int i, int j, Integer[][] cache){
        if(i == str1.length() || j == str2.length())
            return 0;
        if(cache[i][j] != null)
            return cache[i][j];
        if(str1.charAt(i) == str2.charAt(j))
            return 1+ helper(str1, str2, i+1, j+1, cache);
        int lcs1 = helper(str1, str2, i+1, j, cache);
        int lcs2 = helper(str1, str2, i, j+1, cache);
        cache[i][j] = Math.max(lcs1, lcs2);
        return cache[i][j];
    }
}
