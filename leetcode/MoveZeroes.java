package leetcode;

/**
 * <a href="https://leetcode.com/problems/move-zeroes/"/>
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Example:
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 *
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZeroes {


    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0)
            return;
        int index = -1;
        int zeroCount = 0;

        for(int i = 0; i<nums.length; i++){
            if(nums[i]!=0){
                if(index!=-1){
                    nums[index] = nums[i];
                    index++;
                }

            }else{
                if(index == -1)
                    index = i;
                zeroCount++;
            }
        }
        for(int i = nums.length-zeroCount; i<nums.length; i++)
            nums[i] = 0;
    }
}
