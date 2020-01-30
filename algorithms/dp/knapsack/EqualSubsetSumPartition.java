package algorithms.dp.knapsack;

/**
 * Given a set of positive numbers, find if we can partition it into
 * two subsets such that the sum of elements in both the subsets is equal.
 *
 * Example 1:
 * Input: {1, 2, 3, 4}
 * Output: True
 * Explanation: The given set can be partitioned into two subsets with equal sum: {1, 4} & {2, 3}
 *
 * Example 2:
 * Input: {1, 1, 3, 4, 7}
 * Output: True
 * Explanation: The given set can be partitioned into two subsets with equal sum: {1, 3, 4} & {1, 7}
 *
 * Example 3:
 * Input: {2, 3, 4, 6}
 * Output: False
 * Explanation: The given set cannot be partitioned into two subsets with equal sum.
 */
public class EqualSubsetSumPartition {

    /**
     * Approach 1: Brute force recursive solution.
     * Time Complexity: O(2^n)
     * Space Complexity: O(n) because at any given time there may be n elements in recursive stack
     */
    private boolean solveEqualSubsetSumPartition(int[]set){
        int totalSum = 0;
        for(int i = 0; i<set.length; i++)
            totalSum+= set[i];
        return helper(set,totalSum,0,0);
    }

    private boolean helper(int[]set, int sum, int remainder, int index){
        if(sum == remainder)
            return true;
        if(index>= set.length)
            return false;
        return helper(set,sum-set[index],remainder+set[index], index+1) || helper(set, sum, remainder, index+1);
    }

    public static void main(String[] args) {
        int[] set = new int[]{100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100};
        System.out.println(set.length);
        boolean canPartition = new EqualSubsetSumPartition().solveEqualSubsetSumPartition(set);
        System.out.println(canPartition);
    }
}
