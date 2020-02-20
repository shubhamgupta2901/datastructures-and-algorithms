package leetcode;

/**
 * <a href="https://leetcode.com/problems/shortest-common-supersequence/"/>
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
 * If multiple answers exist, you may return any of them.
 * (A string S is a subsequence of string T if deleting some number of characters from T
 * (possibly 0, and the characters are chosen anywhere from T) results in the string S.)
 *
 * Example 1:
 * Input: str1 = "abac", str2 = "cab"
 * Output: "cabac"
 * Explanation:
 * str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
 * str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
 * The answer provided is the shortest such string that satisfies these properties.
 *
 * Note:
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of lowercase English letters.
 */
public class ShortestCommonSupersequence {
    /**
     * Approach 1: Brute force: Time limit exceeds ( 28/47 cases passed)
     * We can process both of the sequences one character at a time, so at any step we must choose between:
     *  1. If the sequences have a matching character, we can skip one character from both the sequences and
     *  make a recursive call for the remaining lengths to get SCS.
     *  2.If the strings don’t match, we start two new recursive calls by skipping one character separately from
     *  each string. The minimum of these two recursive calls will have our answer.
     * Time Complexity: O(2^(m+m)) ~O(2^m) where m is the length of smaller string
     * Space Compleixty: The recursive stack can have at max (m+m) at one time. so O(m).
     * Also at every step two new strings are being created with possible lengths of (m+m). there are 2^(m+m) calls
     * So Space Complexity would be 0((m+m)2^(m+m))
     */
    public String shortestCommonSupersequenceApproach1(String str1, String str2) {
        return helper(str1, str2, 0,0, new String(""));
    }

    private String helper(String str1, String str2, int i, int j, String superSeq){
        //base cases
        if(i == str1.length()){
            superSeq += str2.substring(j);
            return superSeq;
        }

        if(j == str2.length()){
            superSeq += str1.substring(i);
            return superSeq;
        }

        //recurrence
        if(str1.charAt(i) == str2.charAt(j)){
            superSeq+= Character.toString(str1.charAt(i));
            return helper(str1, str2, i+1, j+1, superSeq);
        }
        String superSeq1 = new String(superSeq);
        superSeq1+= Character.toString(str1.charAt(i));
        String seq1 = helper(str1, str2, i+1, j, superSeq1);
        String superSeq2 = new String(superSeq);
        superSeq2+= Character.toString(str2.charAt(j));
        String seq2 = helper(str1, str2, i, j+1, superSeq2);

        //return statement
        return seq1.length() >= seq2.length() ? seq2 : seq1;
    }

    /**
     * Approach 2: Bottom up Dynamic Programming - Tabulation
     * Memory Limit Exceeds 46/47 cases passed.
     * Time Complexity: O(m*n)
     * Space Complexity: O(mn(m+n)) , m*n for table and each element of table is a string
     * of possibly m+n length.
     */
    public String shortestCommonSupersequenceApproach2(String str1, String str2) {
        int M = str1.length();
        int N = str2.length();
        String[][] table = new String[M+1][N+1];

        //table[i][j] represents the SCS in str1.substring(i) and str2.substring(j).
        //interested in table[0][0];

        //bottom case:
        for(int i = M; i>=0; i--){
            table[i][N] = new String(str1.substring(i));
        }

        //bottom case:
        for(int j = N; j>=0; j--){
            table[M][j] = new String(str2.substring(j));
        }

        for(int i = M-1; i>=0; i--){
            for(int j = N-1; j>=0; j--){
                if(str1.charAt(i) == str2.charAt(j))
                    table[i][j] = new String(Character.toString(str1.charAt(i))) + table[i+1][j+1];
                else{
                    if(table[i+1][j].length()<= table[i][j+1].length())
                        table[i][j] = new String(Character.toString(str1.charAt(i))) + table[i+1][j];
                    else table[i][j] = new String(Character.toString(str2.charAt(j))) + table[i][j+1];
                }
            }
        }

        return table[0][0];

    }

    public static void main(String[] args) {
        ShortestCommonSupersequence scs = new ShortestCommonSupersequence();
        String sequence = scs.shortestCommonSupersequenceApproach2("abac", "cab");
        System.out.println(sequence);
    }
}
