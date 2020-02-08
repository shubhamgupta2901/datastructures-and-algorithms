package algorithms.dp.knapsack;

/**
 * Given a set of positive numbers (non zero) and a target sum ‘S’.
 * Each number should be assigned either a ‘+’ or ‘-’ sign.
 * We need to find out total ways to assign symbols to make the sum of numbers equal to target ‘S’.
 *
 * Example 1: #
 * Input: {1, 1, 2, 3}, S=1
 * Output: 3
 * Explanation: The given set has '3' ways to make a sum of '1': {+1-1-2+3} & {-1+1-2+3} & {+1+1+2-3}
 *
 * Example 2: #
 * Input: {1, 2, 7, 1}, S=9
 * Output: 2
 * Explanation: The given set has '2' ways to make a sum of '9': {+1+2+7-1} & {-1+2+7+1}
 *
 * I had solved this question before in leetcode, {@link leetcode.TargetSum}, with a different approach,
 * The approach here essentially converts it in subset sum problem.
 * What we need to do is partition the array into two disjoint subsets s1 and s2.
 * If the sum of elments in first subset is s1 and sum of second subset is s2, then
 * s1-s2 = target (Performing + operations for all elements in subset s1, and performing minus operation in subset s2)
 * Also since they are disjoint sets, s1+s2 = S (where S is sum of all elements in array).
 * Adding the two equations we have 2*s1 = target+S
 * or s1 = (target+S)/2
 * so We are looking for a subset in array whose sum is (target+S)/2
 */
public class TargetSum {

    /**
     * Approach 1: Brute force
     * Time Complexity: O(2^n)
     * Space Complexity: O(n) for recursion stack.
     */
    public int findTargetSubsetsApproach1(int[] nums, int target){
        int sum = 0;
        for(int i = 0; i<nums.length; i++)
            sum += nums[i];
        sum = (target+sum)/2;

        return helper(nums, sum, 0);
    }

    private  int helper(int[] nums, int sum, int index){
        if(sum == 0)
            return 1;
        if(index== nums.length)
            return 0;

        int ways = helper(nums, sum, index+1);
        if(nums[index]<= sum)
            ways+= helper(nums, sum-nums[index], index+1);
        return  ways;
    }


}
