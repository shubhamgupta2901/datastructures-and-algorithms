package algorithms.dynamic_programming.palindromic_subsequence;

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
    private int minDeleteApproach1(String s){
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


    /**
     * Approach 2: Top down Dynamic Programming - Memoization
     */
    private int minDeleteApproach2(String str){
        int length = str.length();
        Integer[][] cache = new Integer[length][length];
        return helper(str, 0, length-1,cache);
    }

    private int helper(String str, int startIndex, int endIndex, Integer[][]cache){
        if(startIndex>=endIndex)
            return 0;
        if(cache[startIndex][endIndex]!=null)
            return cache[startIndex][endIndex];
        if(str.charAt(startIndex) == str.charAt(endIndex))
            return helper(str, startIndex+1, endIndex-1, cache);

        int min = 1 + Math.min(helper(str, startIndex+1, endIndex, cache),
                helper(str, startIndex, endIndex-1, cache));
        cache[startIndex][endIndex] = min;
        return min;
    }


    /**
     * Approach 3: Bottom Down DP - Tabulating
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     * Base case: when start<=end table[start][end] = 0
     * here its not necessary because we are using int array
     */
    private int minDeleteApproach3(String str){
        int length = str.length();
        if(length == 0 || length== 1)
            return 0;
        int[][] table = new int[length][length];

        for(int s = length-1; s>=0; s--){
            for(int e = s+1; e< length; e++){
                if(str.charAt(s) == str.charAt(e))
                    table[s][e] = table[s+1][e-1];
                else table[s][e] = 1 + Math.min(table[s+1][e], table[s][e-1]);
            }
        }
        return table[0][length-1];
    }




    public static void main(String[] args) {
        MinimumDeletionInStringToMakePalindrome mdsp = new MinimumDeletionInStringToMakePalindrome();
        System.out.println("--Approach 1--");
        System.out.println(mdsp.minDeleteApproach1("abdbca"));
        System.out.println(mdsp.minDeleteApproach1("cddpd"));
        System.out.println(mdsp.minDeleteApproach1("pqr"));
        System.out.println(mdsp.minDeleteApproach1("bbbab"));
        System.out.println("--Approach 2--");
        System.out.println(mdsp.minDeleteApproach2("abdbca"));
        System.out.println(mdsp.minDeleteApproach2("cddpd"));
        System.out.println(mdsp.minDeleteApproach2("pqr"));
        System.out.println(mdsp.minDeleteApproach2("bbbab"));
        System.out.println("--Approach 3--");
        System.out.println(mdsp.minDeleteApproach3("abdbca"));
        System.out.println(mdsp.minDeleteApproach3("cddpd"));
        System.out.println(mdsp.minDeleteApproach3("pqr"));
        System.out.println(mdsp.minDeleteApproach3("bbbab"));
    }
}
