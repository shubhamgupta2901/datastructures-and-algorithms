package leetcode;

/**
 * <a href="https://leetcode.com/problems/target-sum/"/>
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
 * Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 *
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 *
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 *
 * Explanation:
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 *
 * Note:
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
public class TargetSum {
    /**
     * Approach 1: Brute force way.
     * I will take each number of array one by one, and will perform either a + operation
     * or a - operation. Once the complete array is traversed if the target is now zero, this
     * means that we have a possible way.
     *
     * Time Complexity: O(2^n)
     * Space Complexity: O(n) because at one point there can be at max n elements
     * in recursive stack.
     */
    public int findTargetSumWays(int[] nums, int S) {
        int[] ways = new int[1];
        helper(nums, S, 0, ways);
        return ways[0];
    }

    private void helper(int[] nums, int target,int index, int[] ways){
        if(index == nums.length){
            if(target == 0){
                ways[0]++;
                return;
            }
            return;
        }
        helper(nums, target+nums[index],index+1, ways);
        helper(nums, target-nums[index], index+1, ways);
    }
}
