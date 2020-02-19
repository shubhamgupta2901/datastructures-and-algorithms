package algorithms.dynamic_programming.longest_common_substring;

/**
 * Given two sequences ‘s1’ and ‘s2’, write a method to find the length of the shortest sequence
 * which has ‘s1’ and ‘s2’ as subsequences.
 *
 * Example 1:
 * Input: s1: "abcf" s2:"bdcf"
 * Output: 5
 * Explanation: The shortest common super-sequence (SCS) is "abdcf".
 *
 * Example 2:
 * Input: s1: "dynamic" s2:"programming"
 * Output: 15
 * Explanation: The SCS is "dynprogrammicng".
 *
 * Solution: My approach for finding the length was to find out the length of Longest Common Subsequence (lcs)
 * in str1 and str2.
 * Then length of shortest common supersequence = str1.length() + str2.length() - lcs(str1,str2).
 * This is like finding out the union of two sets A and B. where A ⋃ B = A + B - A ⋂ B
 * (however we need to keep track of indices so we have valid subsequences)
 * But going with the following technique so that if I have to return the shortest common superseq string, instead of its
 * length, I will have to do minimal changes.
 */
public class ShortestCommonSupersequenceLength {

    /**
     * Approach 1: Brute force
     * We can process both of the sequences one character at a time, so at any step we must choose between:
     *  1. If the sequences have a matching character, we can skip one character from both the sequences and make a
     *  recursive call for the remaining lengths to get SCS.
     *  2. If the strings don’t match, we start two new recursive calls by skipping one character separately from
     *  each string. The minimum of these two recursive calls will have our answer.
     * Time Complexity: O(2^(m+n))
     * Space Complexity: O(m+n)
     */
    private int findSCSLengthApproach1(String str1, String str2){
        return helper(str1, str2, 0, 0);
    }

    private int helper(String str1, String str2, int i , int j){
        //base case:
        // if we have reached the end of a string, return the remaining length of the other string
        if(i == str1.length())
            return str2.length() - j;

        if(j == str2.length())
            return str1.length() - i;

        //recurrence
        if(str1.charAt(i) == str2.charAt(j))
            return 1 + helper(str1, str2, i+1, j+1);

        int seqLen1 = 1 + helper(str1, str2, i+1, j);
        int seqLen2 = 1 + helper(str1, str2, i, j+1);

        //return
        return Math.min(seqLen1, seqLen2);
    }


    private int findSCSLengthApproach2(String str1, String str2){
        Integer[][] cache = new Integer[str1.length()][str2.length()];
        return helper(str1, str2, 0, 0, cache);
    }

    private int helper(String str1, String str2, int i , int j, Integer[][] cache){
        if(i == str1.length())
            return str2.length() - j;
        if(j == str2.length())
            return str1.length() - i;

        if(cache[i][j]!=null)
            return cache[i][j];

        if(str1.charAt(i) == str2.charAt(j)){
            cache[i][j] = 1 + helper(str1, str2, i+1, j+1,cache);
            return cache[i][j];
        }

        int seqLen1 = 1 + helper(str1, str2, i+1, j,cache);
        int seqLen2 = 1 + helper(str1, str2, i, j+1,cache);

        //return
        cache[i][j] =  Math.min(seqLen1, seqLen2);
        return cache[i][j];
    }


    public static void main(String[] args) {
        ShortestCommonSupersequenceLength scs = new ShortestCommonSupersequenceLength();
        System.out.println(scs.findSCSLengthApproach2("abcf", "bdcf"));
        System.out.println(scs.findSCSLengthApproach2("dynamic", "programming"));
    }
}
