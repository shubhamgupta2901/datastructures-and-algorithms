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
     *
     * The idea behind this solution was to find the sum of all elements in an array,
     * Try all combinations of partitioning the given numbers into two sets to see if any pair of sets has an equal sum.
     * And in a way (similar to how we find subsets) I keep going through the array, either selecting the number
     * or not selecting the number.
     * When a number is selected, sum is reduced by the number , and remainder is increased by the number.
     * For an array of size n there will be 2^n such possibilities.
     * If for any such possibility where the sum is equal to remainder, we say that we have partitioned the array in two
     * subsets such that the elements of elements in both subsets is equal.
     *
     * The solution seems to be right for the testcases I checked.
     * However, for very large arrays, the timelimit exceeds as expected.
     */
    private boolean canPartitionApproach1(int[]set){
        int totalSum = 0;
        for(int i = 0; i<set.length; i++)
            totalSum+= set[i];
        return canPartitionHelper(set,totalSum,0,0);
    }

    private boolean canPartitionHelper(int[]set, int sum, int remainder, int index){
        if(sum == remainder)
            return true;
        if(index>= set.length)
            return false;
        boolean case1 = canPartitionHelper(set,sum-set[index],remainder+set[index], index+1);
        boolean case2 = canPartitionHelper(set, sum, remainder, index+1);
        return  case1 || case2;
    }

    /**
     * Approach 2:
     * Another Brute force solution
     * Assume if S represents the total sum of all the given numbers,
     * then the two equal subsets must have a sum equal to S/2.
     * This essentially transforms our problem to: "Find a subset of the given numbers that has a total sum of S/2".
     * Time Complexity: O(2^n)
     * Space Complexity: O(n)
     */
    private boolean canPartitionApproach2(int[] set){
        int sum = 0;
        for(int i = 0; i<set.length; i++)
            sum+=set[i];
        //If the sum is odd, don't proceed
        if(sum%2 != 0)
            return false;
        return canPartitionHelper(set, sum/2,0);
    }

    private boolean canPartitionHelper(int[] set, int sum, int index){
        if(sum == 0)
            return true;
        if(index>= set.length)
            return false;
        if(sum >= set[index]){
            if(canPartitionHelper(set, sum-set[index], index+1))
                return true;
        }
        return canPartitionHelper(set, sum, index+1);
    }

    /**
     * Approach 3: Optimising approach 2 with DP memoization
     * Time Complexity:O(N*S) where N is number of elements in set
     * and S is the total sum of all the numbers.
     * Space Compleixty: O(N*S) for memoization array
     */
    private boolean canPartitionApproach3(int[] set){
        int sum = 0;
        for(int i = 0; i<set.length; i++)
            sum += set[i];
        if(sum%2 !=0)
            return false;
        int halfSum = sum/2;
        Boolean[][]cache = new Boolean[set.length][halfSum+1];
        return canPartitionHelper(set,halfSum,0,cache);
    }

    private boolean canPartitionHelper(int[] set, int target, int index, Boolean[][] cache){
        if(target == 0)
            return true;
        if(index>= set.length)
            return false;
        if(cache[index][target]!= null)
            return cache[index][target];

        boolean case1 = false;
        if(target>=set[index])
                case1 = canPartitionHelper(set, target-set[index], index+1, cache);

        if(case1){
            cache[index][target] = true;
            return true;
        }
        else{
            boolean case2 = canPartitionHelper(set,target,index+1, cache);
            cache[index][target] = case2;
            return case2;
        }
    }

    public static void main(String[] args) {
        int[] set = new int[]{10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
        System.out.println(set.length);
        boolean canPartition = new EqualSubsetSumPartition().canPartitionApproach1(set);
        System.out.println(canPartition);
    }
}
