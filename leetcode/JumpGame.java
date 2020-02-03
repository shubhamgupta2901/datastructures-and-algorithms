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
    public boolean canJumpApproach1(int[] nums) {
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


    /**
     * Approach 2: Directly attempting dp tabulation
     * Accepted but quite slow.
     * Time Complexity: ?  Probably O(n*S) where n is the number of elements
     * and S is the largest number in array.
     * Space Complexity: O(n)
     */
    public boolean canJumpApproach2(int[] nums) {
        boolean[] canJump = new boolean[nums.length];
        canJump[nums.length-1] = true;

        for(int i = nums.length-2;i>=0; i--){
            for(int j = nums[i]; j>0; j--){
                if(i+j >= nums.length || canJump[i+j] == true){
                    canJump[i] = true;
                    break;
                }
            }
        }

        return canJump[0];
    }
}
