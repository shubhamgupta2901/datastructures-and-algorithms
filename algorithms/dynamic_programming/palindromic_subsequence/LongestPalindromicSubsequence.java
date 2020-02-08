package algorithms.dynamic_programming.palindromic_subsequence;

/**
 * Given a sequence, find the length of its Longest Palindromic Subsequence (LPS).
 * In a palindromic subsequence, elements read the same backward and forward.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting
 * some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 * Input: "abdbca"
 * Output: 5
 * Explanation: LPS is "abdba".
 *
 * Example 2:
 * Input: = "cddpd"
 * Output: 3
 * Explanation: LPS is "ddd".
 *
 * Example 3:
 * Input: = "pqr"
 * Output: 1
 * Explanation: LPS could be "p", "q" or "r".
 */
public class LongestPalindromicSubsequence {

    /**
     * Approach 1: Brute force approach: Time limit exceeds for larger strings.
     * Was expected to be honest.
     * Create all possible sub sequences, and find the string which
     * has the maximum length and is a palindrome.
     * Time Complexity: If there are n characters in string,
     * O(2^n) to generate subsequences, and for every subsequence we are checking
     * if it is palindrome which takes additional n. So O(n*2^n) ~ O(2^n)
     * Space Complexity: Recursive stack runs for O(n) but storing all the subsequences require O(2^n) space
     */
    public int longestPalindromeSubseqApproach1(String s) {
        return helper(s, 0, new String());
    }

    private int helper(String sequence, int index, String subsequence){
        if(index == sequence.length()){
            return isPalindrome(subsequence) ? subsequence.length() : 0;
        }
        String newSubsequence = subsequence + sequence.substring(index, index+1);
        return Math.max(helper(sequence, index+1, subsequence),
                helper(sequence, index+1, newSubsequence));
    }

    private boolean isPalindrome(String s){
        if(s.length()==0 || s.length() == 1)
            return true;
        for(int i = 0, j = s.length()-1; i<j; i++, j--){
            if(s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }


    /**
     * Approach 2: Brute force recursion - Time limit exceeds for larger strings
     * This time rather than taking an empty string and then either adding or not adding characters
     * in new  subsequences in the recursions tree,
     * I thought of taking the string and then either removing or not removing characters to create new
     * subsequences. I thought while it will not reduce time complexity, it will lower the number of recursive calls.
     * Apparently it does not make any difference both the solutions end up being the same.
     * Time Complexity: O(2^n)
     * Space Complexity: O(2^n)
     */
    public int longestPalindromeSubseqApproach2(String s) {
        return helper(s, 0);
    }

    private int helper(String subsequence, int index){
        if(isPalindrome(subsequence))
            return subsequence.length();
        if(index == subsequence.length())
            return 0;
        int lps1 = helper(subsequence, index+1);
        String newSubsequence = subsequence.substring(0, index) + subsequence.substring(index+1, subsequence.length());
        int lps2 = helper(newSubsequence, index);
        return Math.max(lps1, lps2);
    }


    /**
     * Approach 3: A much better brute force - Time limit exceeds for larger strings.
     * Try all the subsequences of the given sequence.
     * We can start processing from the beginning and the end of the sequence.
     * So at any step, we have two options:
     * 1) If the element at the beginning and the end are the same, we increment our count by two
     *    and make a recursive call for the remaining sequence.
     * 2) We will skip the element either from the beginning or the end to make
     *    two recursive calls for the remaining subsequence.
     * If option one applies then it will give us the length of LPS;
     * otherwise,the length of LPS will be the maximum number returned by the two recurse calls from the second option.
     * Time Complexity: O(2^n)
     * Space Complexity: O(n) for recursion stack. where n is string length.
     */
    public int longestPalindromeSubseqApproach3(String s) {
        return helper(s, 0, s.length()-1);
    }

    private int helper(String sequence, int start, int end){
        if(start>end)
            return 0;
        if(start==end)
            return 1;
        if(sequence.charAt(start) == sequence.charAt(end))
            return 2 + helper(sequence, start+1, end-1);

        return Math.max(helper(sequence, start+1, end),
                helper(sequence, start, end-1));

    }


    /**
     * Approach 4: DP Memoization - Accepted.
     * Its very strange how memoization solution are faster even when there are no overlapping subproblems.
     * The two changing values to our recursive function are the two indexes, startIndex and endIndex.
     * Therefore, we can store the results of all the subproblems in a two-dimensional array.
     * Time Complexity: O(n^2) : Since our memoization array dynamic_programming[st.length()][st.length()] stores the results
     * for all the subproblems, we can conclude that we will not have more than n*n subproblems
     * (where ‘n’ is the length of the input sequence)
     * Space complexity: O(n^2) : algorithm will be using O(n^2) space for the memoization array.
     * Other than that we will use O(n) space for the recursion call-stack.
     * So the total space complexity will be O(n^2 + n) ,which is asymptotically equivalent to O(n^2)
     */
    public int longestPalindromeSubseqApproach4(String s) {
        int length = s.length();
        Integer[][]cache = new Integer[length][length];
        return helper(s, 0, length-1, cache);
    }

    private int helper(String sequence, int start, int end, Integer[][] cache){
        if(start>end)
            return 0;
        if(start==end)
            return 1;
        if(cache[start][end]!=null)
            return cache[start][end];
        if(sequence.charAt(start) == sequence.charAt(end))
            return 2 + helper(sequence, start+1, end-1, cache);

        int lps =  Math.max(helper(sequence, start+1, end, cache),
                helper(sequence, start, end-1, cache));
        cache[start][end] = lps;
        return lps;
    }

    /**
     * Approach 5: Bottom up Tabulation
     * Since we want to try all the subsequences of the given sequence, we can use a two-dimensional array to store our results.
     * Table: table[st.length()][st.length()]
     * What does table[s][e] means? It denotes what is the longest palindromic subsequence in string which starts from index s
     * and ends in index e (both indices inclusive)
     * Base cases: table[s][e] should be 1 if s==e and 0 if s>e
     * What do we return? table[0][st.length()-1]
     * How do we fill table bottom up?
     * run nested loops of: s : length-> 0 , e: 0->length
     * if s == e table[s][e] = 2+ table[s+1][e-1]
     * else table[s][e] = max(table[s+1][e], table[s][e-1]
     * Time Complexity: O(n^2)
     * Space complexity: O(n^2)
     */
    public int longestPalindromeSubseqApproach5(String str) {
        int length = str.length();
        int[][]table = new int[length][length];

        //Base Case : start == end ? table[start][end] = 1
        for(int i = 0; i<length; i++)
            table[i][i] = 1;
        //Base Case: start>end? table[start][end] = 0
        //Not necessary: As the default value of primitive int is 0
        for(int s = 1; s<length; s++){
            for(int e = 0; e<s; e++){
                table[s][e] = 0;
            }
        }
        //Fill up table in bottom up manner.
        for(int s = length-1;s>=0; s--){
            for(int e = s+1; e<length; e++){
                if(str.charAt(s) == str.charAt(e))
                    table[s][e] = 2+ table[s+1][e-1];
                else table[s][e] = Math.max(table[s+1][e], table[s][e-1]);
            }
        }

        return table[0][length-1];
    }

    public static void main(String[] args) {
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
        String s = "euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew";
        int length =  lps.longestPalindromeSubseqApproach4(s);
        System.out.println(length);
    }
}
