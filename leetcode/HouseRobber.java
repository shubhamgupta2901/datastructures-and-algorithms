package leetcode;

/**
 * <a href="https://leetcode.com/problems/house-robber/"/>
 * You are a professional robber planning to rob houses along a street.Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security system connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 * Input: [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 *              Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class HouseRobber {

    /**
     * Approach1: Brute force recursive solution
     * While the solution seems correct, time limit exceeds for large arrays.
     * Time Complexity: ?
     * Space Complexity: O(n)
     *
     */
    public int robApproach1(int[] nums) {
        return robHelper(nums, 0);
    }

    private int robHelper(int[]nums, int index){
        if(index >= nums.length)
            return 0;
        int profit1 = robHelper(nums, index+1);
        int profit2 = nums[index]+ robHelper(nums, index+2);
        return Math.max(profit1, profit2);
    }

    /**
     * Approach2: DP memoization of approach 1.
     * Solution is correct for even large arrays.
     * Except one edge case: A large array containing only zeros
     * Time Complexity: ?
     * Space Complexity: O(n)
     */
    public int robApproach2(int[] nums) {
        int[] cache = new int[nums.length];
        return robHelper(nums, 0, cache);
    }

    private int robHelper(int[]nums, int index, int[] cache){
        if(index >= nums.length)
            return 0;
        if(cache[index]!=0)
            return cache[index];
        int profit1 = robHelper(nums, index+1, cache);
        int profit2 = nums[index]+ robHelper(nums, index+2,cache);
        int profit = Math.max(profit1, profit2);
        cache[index] = profit;
        return profit;
    }
}
