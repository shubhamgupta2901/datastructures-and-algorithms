package algorithms.dp.knapsack;

/**
 * Given a set of positive numbers, find the total number of subsets whose sum is equal to a given number ‘S’.
 *
 * Example:
 * Input: {1, 1, 2, 3}, S=4
 * Output: 3
 * The given set has '3' subsets whose sum is '4': {1, 1, 2}, {1, 3}, {1, 3}
 * Note that we have two similar sets {1, 3}, because we have two '1' in our input.
 *
 * Input: {1, 2, 7, 1, 5}, S=9
 * Output: 3
 * The given set has '3' subsets whose sum is '9': {2, 7}, {1, 7, 1}, {1, 2, 1, 5}
 */
public class CountOfSubsetSum {

    /**
     * Approach 1: Brute force solution
     * Time complexity: O(2^n)
     * Space Complexity: O(n)
     */
    private int countSubsetsApproach1(int[] set, int sum){
        return helper(set, sum, 0);
    }

    private int helper(int[]set,int sum, int index){
        if(sum == 0)
            return 1;
        if(index>=set.length)
            return 0;

        int count = helper(set, sum, index+1);
        if(sum>=set[index])
            count += helper(set,sum-set[index], index+1);
        return count;
    }


    /**
     * Approach 1: Brute force solution
     * Time complexity: O(2^n)
     * Space Complexity: O(n)
     */
    private int countSubsetsApproach2(int[] set, int sum){
        Integer[][] cache = new Integer[set.length][sum+1];
        return helper(set, sum, 0,cache);
    }

    private int helper(int[]set,int sum, int index, Integer[][]cache){
        if(sum == 0)
            return 1;
        if(index>=set.length)
            return 0;

        if(cache[index][sum] != null)
            return cache[index][sum];

        int count = helper(set, sum, index+1,cache);
        if(sum>=set[index])
            count += helper(set,sum-set[index], index+1,cache);
        cache[index][sum] = count;
        return count;
    }



}
