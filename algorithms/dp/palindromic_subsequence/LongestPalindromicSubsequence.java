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
    public int longestPalindromeSubseq(String s) {
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

    public static void main(String[] args) {
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
        String s = "euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew";
        int length =  lps.longestPalindromeSubseq(s);
        System.out.println(length);
    }
}
