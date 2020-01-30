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
    public boolean canPartition(int[] set) {
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
}
