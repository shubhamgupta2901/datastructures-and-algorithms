package algorithms.dp.palindromic_subsequence;

/**
 * Given a string, find the length of its Longest Palindromic Substring (LPS).
 * In a palindromic string, elements read the same backward and forward.
 *
 * Example 1:
 * Input: "abdbca"
 * Output: 3
 * Explanation: LPS is "bdb".
 *
 * Example 2:
 * Input: = "cddpd"
 * Output: 3
 * Explanation: LPS is "dpd".
 *
 * Example 3:
 * Input: = "pqr"
 * Output: 1
 * Explanation: LPS could be "p", "q" or "r".
 */
public class LongestPalindromicSubstring {

    /**
     * Approach 1: Brute force
     * The brute-force solution will be to try all the substrings of the given string.
     * We can start processing from the beginning and the end of the string.
     * So at any step, we will have two options:
     * 1) If the element at the beginning and the end are the same, we make a recursive call to check if the
     *    remaining substring is also a palindrome. If so, the substring is a palindrome from beginning to the end.
     * 2) If element at the beginning and end are not same, we will skip either the element from the beginning or
     *    the end to make two recursive calls for the remaining substring.
     *    The length of LPS would be the maximum of these two recursive calls.
     * Time Complexity: Due to the three recursive calls, the time complexity of the above algorithm is exponential O(3^n)
     * ​​ where ‘n’ is the length of the input string.
     * Space complexity: O(n) which is used to store the recursion stack.
     */

    public int findLPSLengthApproach1(String s) {
        int length = s.length();
        return helper(s,0,length-1);
    }

    private int helper(String s, int startIndex, int endIndex){
        if(startIndex == endIndex)
            return 1;
        if(startIndex> endIndex)
            return 0;
        //Possible palindrome -  check recursively
        if(s.charAt(startIndex) == s.charAt(endIndex)){
            if(helper(s, startIndex+1, endIndex-1) == endIndex-startIndex-1)
                return endIndex-startIndex+1;
        }
        //Recursively check for smaller substring
        return Math.max(helper(s, startIndex+1, endIndex),
                helper(s, startIndex, endIndex-1));

    }

    /**
     * We can use an array to store the already solved subproblems.
     * The two changing values to our recursive function are the two indexes, startIndex and endIndex.
     * Therefore, we can store the results of all the subproblems in a two-dimensional array.
     * Time Compelxity: One can argue that the time complexity is O(n^2) because we will not have more than
     * n*n subproblems.
     * Space Compelxity: O(n^2) for storing results in cache
     */
    public int findLPSLengthApproach2(String s){
        int length = s.length();
        Integer[][] cache = new Integer[length][length];
        return helper(s,0, length-1, cache);
    }

    private int helper(String s, int startIndex, int endIndex, Integer[][]cache){
        if(startIndex==endIndex)
            return 1;
        if(startIndex>endIndex)
            return 0;
        if(cache[startIndex][endIndex]!=null)
            return cache[startIndex][endIndex];

        if(s.charAt(startIndex) == s.charAt(endIndex)){
            if(helper(s,startIndex+1,endIndex-1,cache) == endIndex-startIndex-1){
                cache[startIndex][endIndex] = endIndex-startIndex+1;
                return cache[startIndex][endIndex];
            }
        }

        int lpsLength = Math.max(helper(s,startIndex+1, endIndex,cache),
                helper(s, startIndex, endIndex-1, cache));
        cache[startIndex][endIndex] = lpsLength;
        return lpsLength;
    }


    /**
     * Approach 3: Bottom up Tabulation
     * Since we want to try all the substrings of the given sequence, we can use a two-dimensional array to store our results.
     * Table: table[st.length()][st.length()]
     * What does table[s][e] means? It denotes what is the longest palindromic substring in string which starts from index s
     * and ends in index e (both indices inclusive)
     * Base cases: table[s][e] should be 1 if s==e and 0 if s>e
     * What do we return? table[0][st.length()-1]
     * How do we fill table bottom up?
     * run nested loops of: s : length-> 0 , e: 0->length
     * if charAt(s) == charAt(e) check if table[s+1][e-1] = length of substring from s+1 to e-1 indices
     *  in this case table[s][e] = 2+table[s+1][e-1]
     * else table[s][e] = max(table[s+1][e], table[s][e-1]
     * Time Complexity: O(n^2)
     * Space complexity: O(n^2)
     */
    public int findLPSLengthApproach3(String st){
        int length = st.length();
        int[][] table = new int[length][length];

        //Base case start == end ? table[start][end] = 1
        for(int i = 0; i<length; i++)
            table[i][i] = 1;

        // Base case start> end ? table [start][end] = 0
        // Although not required because its int array default values will be 0 anyway
        for(int s = 0; s<length; s++){
            for(int e = 0; e<s; e++){
                table[s][e] = 0;
            }
        }
        //fill table bottom up
        for(int s = length-1; s>=0; s--){
            for(int e = s+1;e<length; e++ ){
                if(st.charAt(s)==st.charAt(e)){
                    if(table[s+1][e-1] == e-s-1){
                        table[s][e] = 2+table[s+1][e-1];
                        continue;
                    }
                }

                table[s][e] = Math.max(table[s+1][e], table[s][e-1]);
            }
        }
        return table[0][length-1];
    }
}
