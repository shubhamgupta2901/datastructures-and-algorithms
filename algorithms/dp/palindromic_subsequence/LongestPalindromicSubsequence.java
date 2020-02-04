package algorithms.dp.palindromic_subsequence;

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


    public static void main(String[] args) {
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
        String s = "euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew";
        int length =  lps.longestPalindromeSubseqApproach2(s);
        System.out.println(length);
    }
}
