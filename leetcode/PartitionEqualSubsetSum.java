package leetcode;

/**
 * <a href="https://leetcode.com/problems/partition-equal-subset-sum/"/>
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Note:
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 *
 * Example 1:
 * Input: [1, 5, 11, 5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 * Input: [1, 2, 3, 5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class PartitionEqualSubsetSum {
    /**
     * DP with memoization
     * Time Complexity: O(N*S)
     * Space Complexity: O(N*S)
     * where N is total number of elements in set.
     * and S is the sum of elements in set.
     */
    public boolean canPartitionApproach1(int[] set) {
        int sum = 0;
        for(int i = 0; i<set.length; i++)
            sum+=set[i];
        //If the sum is odd, don't proceed
        if(sum%2 != 0)
            return false;
        int halfSum = sum/2;
        Boolean[][] cache = new Boolean[set.length][halfSum+1];
        return canPartitionHelper(set, halfSum,0,cache);
    }

    private boolean canPartitionHelper(int[] set, int sum, int index, Boolean[][]cache){
        if(sum == 0)
            return true;
        if(index>= set.length)
            return false;
        if(cache[index][sum]!=null)
            return cache[index][sum];
        if(sum >= set[index]){
            if(canPartitionHelper(set, sum-set[index], index+1, cache)){
                cache[index][sum] = true;
                return true;
            }

        }
        boolean canPartition =  canPartitionHelper(set, sum, index+1, cache);
        cache[index][sum] = canPartition;
        return canPartition;
    }


    /**
     * DP Tabulization
     * Time Complexity: O(N*S)
     * Space Complexity: O(N*S)
     * where N is total number of elements in set.
     * and S is the sum of elements in set.
     */
    private boolean canParititionApproach2(int[]set){
        int sum = 0;
        for(int i = 0; i<set.length; i++)
            sum+=0;
        if(sum%2 !=0)
            return false;
        int target = sum/2;
        boolean[][] table = new boolean[set.length][target+1];

        // Solving the smallest subproblem: table[i][0] will always be true as we can always get '0' sum starting from any index.
        for(int index = 0; index<set.length; index++)
            table[index][0] = true;

        // Solving the smallest subproblem: with only one number, we can form a subset only when the required target is equal to its value
        for(int index=0;index<set.length; index++)
            table[index][target] = set[index] == target ? true : false;

        for (int index = 1; index <set.length; index++){
            for(int t =1; t<= target; t++){
                if(table[index-1][t])
                    table[index][t] = true;
                else if(t>=set[index]){
                    table[index][t] = table[index-1][t-set[index]];
                }else
                    table[index][t] = false;
            }
        }
        return table[set.length-1][target];

    }

}
