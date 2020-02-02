package leetcode;

/**
 * <a href="https://leetcode.com/problems/jump-game/"/>
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 * jump length is 0, which makes it impossible to reach the last index.
 */
public class JumpGame {
    /**
     * Approach 1: Brute force - Time limit exceeds.
     * All test cases passed except one really large array.
     * Time Complexity: ?
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        return helper(nums, 0);
    }

    private boolean helper(int[] nums, int index){
        if(index >= nums.length-1)
            return true;
        if(nums[index] == 0)
            return false;
        boolean canJump = false;
        for(int i = nums[index]; i>=1; i--)
            canJump = canJump || helper(nums, index+i);
        return canJump;
    }
}
