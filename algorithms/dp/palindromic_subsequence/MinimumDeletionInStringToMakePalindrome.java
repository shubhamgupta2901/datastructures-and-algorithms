package algorithms.dp.palindromic_subsequence;

/**
 * Given a string, find the minimum number of characters that we can remove to make it a palindrome.
 *
 * Input: "abdbca"
 * Output: 1
 * Explanation: By removing "c", we get a palindrome "abdba".
 *
 * Example 2:
 * Input: = "cddpd"
 * Output: 2
 * Explanation: Deleting "cp", we get a palindrome "ddd".
 *
 * Example 3:
 * Input: = "pqr"
 * Output: 2
 * Explanation: We have to remove any two characters to get a palindrome, e.g. if we
 * remove "pq", we get palindrome "r".
 */
public class MinimumDeletionInStringToMakePalindrome {

    /**
     * Approach 1: Brute force
     * If the start and end indices are same, check for substring by removing start and end indices
     * else check for substrings by removing start once and end once, add 1 to min of those 2.
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     */
    private int minDelete(String s){
        return helper(s,0, s.length()-1);
    }

    private int helper(String str, int startIndex, int endIndex){
        if(startIndex>=endIndex)
            return 0;
        if(str.charAt(startIndex) == str.charAt(endIndex))
            return helper(str, startIndex+1, endIndex-1);

        int min = 1 + Math.min(helper(str,startIndex+1, endIndex),
                helper(str,startIndex, endIndex-1));
        return min;
    }



    public static void main(String[] args) {
        MinimumDeletionInStringToMakePalindrome mdsp = new MinimumDeletionInStringToMakePalindrome();
        System.out.println(mdsp.minDelete("abdbca"));
        System.out.println(mdsp.minDelete("cddpd"));
        System.out.println(mdsp.minDelete("pqr"));
    }
}
