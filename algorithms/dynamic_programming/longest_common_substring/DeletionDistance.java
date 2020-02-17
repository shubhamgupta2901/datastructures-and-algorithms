package algorithms.dynamic_programming.longest_common_substring;

/**
 * This question comes in two patterns:
 *
 * 1. Deletion Distance:
 * The deletion distance of two strings is the minimum number of characters you need to delete in the two strings
 * in order to get the same string.
 * For instance, the deletion distance between "heat" and "hit" is 3:
 * By deleting 'e' and 'a' in "heat", and 'i' in "hit", we get the string "ht" in both cases.
 * We cannot get the same string from both strings by deleting 2 letters or fewer.
 * Given the strings str1 and str2, write an efficient function deletionDistance that
 * returns the deletion distance between them. Explain how your function works,
 * and analyze its time and space complexities.
 *
 * 2. Minimum Deletions & Insertions to Transform a String into another
 * Given strings s1 and s2, we need to transform s1 into s2 by deleting and inserting characters.
 * Write a function to calculate the count of the minimum number of deletion and insertion operations.
 *
 * Example 1:
 * Input: s1 = "abc"
 *        s2 = "fbc"
 * Output: 1 deletion and 1 insertion.
 * Explanation: We need to delete {'a'} and insert {'f'} to s1 to transform it into s2.
 *
 * Example 2:
 * Input: s1 = "abdca"
 *        s2 = "cbda"
 * Output: 2 deletions and 1 insertion.
 * Explanation: We need to delete {'a', 'c'} and insert {'c'} to s1 to transform it into s2.
 *
 * Example 3:
 * Input: s1 = "passport"
 *        s2 = "ppsspt"
 * Output: 3 deletions and 1 insertion
 * Explanation: We need to delete {'a', 'o', 'r'} and insert {'p'} to s1 to transform it into s2.
 *
 * Both the problems are variations of {@link LongestCommonSubsequence} only.
 * DeletionDistance: The "deletion distance" between two strings is just the total length of the strings
 * minus twice the length of the LCS.
 * i.e deletion distance = str1.length() + str2.length() - 2*lcs(str1, str2)
 *
 * Minimum Deletions & Insertions to Transform a String into another:
 * 1. To transform str1 into str2, we need to delete everything from str1 which is not part of LCS,
 * so minimum deletions we need to perform from str1 => str1.length() - lcs(str1, str2)
 * 2. We need to insert everything in str1 which is present in str2 but not part of LCS,
 * so minimum insertions we need to perform in str1 => str2.length() - lcs(str1,str2)
 * so, Insertions + Deletions = str1.length() + str2.length() - 2*lcs(str1, str2)
 */
public class DeletionDistance {
    /**
     * Approach 1: Top Down DP + Memoization
     * Time complexity Brute force: O(2^(M+N)
     * Space Complexity Brute force : O(M+N)
     *
     * Time Complexity Memoized DP: O(M*N)
     * Space Complexity Memoized DP: O(M*N)
     */
    private int deletionDistanceApproach1(String str1, String str2){
        Integer[][] cache = new Integer[str1.length()][str2.length()];
        int lcs = lcsHelper(str1, str2, 0, 0, cache);
        return str1.length() + str2.length() - 2 * lcs;
    }

    private int lcsHelper(String str1, String str2, int i, int j, Integer[][] cache){
        if(i == str1.length() || j == str2.length())
            return 0;
        if(cache[i][j] != null)
            return  cache[i][j];
        if(str1.charAt(i) == str2.charAt(j))
            return 1 + lcsHelper(str1, str2, i+1, j+1,cache);

        int lcs1 = lcsHelper(str1, str2, i+1, j,cache);
        int lcs2 = lcsHelper(str1, str2, i, j+1,cache);
        cache[i][j] =  Math.max(lcs1, lcs2);
        return cache[i][j];
    }


}
