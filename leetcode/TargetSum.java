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


    /**
     * Approach 2: Brute force recursion
     * Same solution and logic as approach 1,
     * Rather than creating an additional variable to keep track of number of ways,
     * changed the code such that the helper function now returns the total number of ways.
     * This looks intutive if I want to try optimization with memoization and tabulation.
     * Time Complexity: O(2^n)
     * Space Complexity: O(n)
     */
    public int findTargetSumWaysApproach2(int[] nums, int S) {
        return helper(nums, S, 0);
    }

    private int helper(int[] nums, int target,int index){
        if(index == nums.length){
            if(target == 0)
                return 1;
            return 0;
        }
        int ways = helper(nums, target+nums[index],index+1) +
                helper(nums, target-nums[index], index+1);
        return ways;
    }

    /**
     * Approach 3: memoizing approach 2
     * a 2d array where arr[index][target] refers to the number of ways of getting
     * target if we start from index "index" in array
     * Note the minimum value of target can be target-sum of nums array,
     * and max value can be target+sum of nums array.
     * so the length would be from target-sum to target+sum.
     * Also (target-sum) can yield a negative value so
     * we create an offset to be added before accessing indices
     *
     */
    public int findTargetSumWaysApproach3(int[] nums, int S) {
        int sum = 0;
        for(int i =0; i<nums.length; i++)
            sum+= nums[i];
        Integer[][] cache = new Integer[nums.length][2*sum+1];
        int offset = sum-S;
        return helper(nums,offset, S, 0, cache);
    }

    private int helper(int[] nums,int offset, int target,int index, Integer[][] cache){
        if(index >= nums.length){
            if(target == 0)
                return 1;
            return 0;
        }
        if(cache[index][target+offset] != null)
            return cache[index][target+offset];
        int ways = helper(nums,offset, target+nums[index],index+1, cache) +
                helper(nums,offset, target-nums[index], index+1, cache);
        cache[index][target+offset] = ways;
        return ways;
    }

    public static void main(String[] args) {
        int [] arr = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int target = 10;

        int [] arr1 = new int []{1,1,1,1,1};
        int target1 = 3;
        int ways = new TargetSum().findTargetSumWaysApproach2(arr1, target1);
        System.out.println(target);
    }



}
