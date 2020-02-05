package algorithms.dp.palindromic_subsequence;

/**
 * Given a string, find the total number of palindromic substrings in it.
 * Please note we need to find the total number of substrings and not subsequences.
 *
 * Example 1:
 * Input: "abdbca"
 * Output: 7
 * Explanation: Here are the palindromic substrings, "a", "b", "d", "b", "c", "a", "bdb".
 *
 * Example 2:
 * Input: = "cddpd"
 * Output: 7
 * Explanation: Here are the palindromic substrings, "c", "d", "d", "p", "d", "dd", "dpd".
 *
 * Example 3:
 * Input: = "pqr"
 * Output: 3
 * Explanation: Here are the palindromic substrings,"p", "q", "r".
 */
public class CountOfPalindromicSubstrings {

    /**
     * Approach 1: Bottom down dynamic programming.
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
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
