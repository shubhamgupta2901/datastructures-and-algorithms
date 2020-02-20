package algorithms.dynamic_programming.longest_common_substring;

/**
 * Given a number sequence, find the minimum number of elements that should be deleted to make the remaining
 * sequence sorted.(ascending order)
 *
 * Example 1:
 * Input: {4,2,3,6,10,1,12}
 * Output: 2
 * Explanation: We need to delete {4,1} to make the remaing sequence sorted {2,3,6,10,12}.
 *
 * Example 2:
 * Input: {-4,10,3,7,15}
 * Output: 1
 * Explanation: We need to delete {10} to make the remaing sequence sorted {-4,3,7,15}.
 *
 * Example 3:
 * Input: {3,2,1,0}
 * Output: 3
 * Explanation: Since the elements are in reverse order, we have to delete all except one to get a
 * sorted sequence. Sorted sequences are {3}, {2}, {1}, and {0}
 *
 * Approach: This seems like a variation of Longest Increasing Subsequence. If we find out the Longest Increasing
 * Subsequence in a sequence, all the remaining numbers need to deleted to make the sequence sorted.
 * so [Minimum Deletions to Make Sequence Sorted = Length of Array - Length of Longest Increasing Subsequence]
 *
 * Trying a different approach here where I take two indices - previous and current
 * If the current index is larger than
 *
 */
public class MinimumDeletionsToMakeSequenceSorted {

    /**
     * Approach 1: Trying a slightly different approach brute force approach here
     * where I take two indices - previous and current, There can be two possibilites
     *  1. If nums[prev] >= nums[current], delete nums[curr] and recursively find for prevIndex = currentIndex
     *  and current Index = currentIndex+1
     *  2. Else, Do not delete num[curr] and recursively find for prevIndex = prevIndex and
     *  currIndex = currIndex+1
     * minimum deletions will be minimum value of the two cases.
     * Time Complexity: O(2^N)
     * Space Complexity: O(N) for recursive stack.
     */
    private int findMinimumDeletionsApproach1(int[] nums){
        return helper(nums, -1, 0);
    }

    private int helper(int[] nums, int prevIndex, int currentIndex){
        //base condition
        if(currentIndex == nums.length)
            return 0;
        //recursion
        int delOp1 = Integer.MAX_VALUE;
        if(prevIndex == -1 || nums[prevIndex]<nums[currentIndex])
            delOp1 = helper(nums, currentIndex, currentIndex+1);
        int delOp2 = 1 + helper(nums, prevIndex, currentIndex+1);

        //return statements
        return Math.min(delOp1, delOp2);
    }


    private int findMinimumDeletionsApproach2(int[] nums){
        Integer[][] cache = new Integer[nums.length+1][nums.length];
        return helper(nums, -1, 0, cache);
    }

    private int helper(int[] nums, int prevIndex, int currentIndex, Integer[][] cache){
        //base condition
        if(currentIndex == nums.length)
            return 0;
        if(cache[prevIndex+1][currentIndex]!=null)
            return cache[prevIndex+1][currentIndex];
        //recursion
        int delOp1 = Integer.MAX_VALUE;
        if(prevIndex == -1 || nums[prevIndex]<nums[currentIndex])
            delOp1 = helper(nums, currentIndex, currentIndex+1);
        int delOp2 = 1 + helper(nums, prevIndex, currentIndex+1);

        //return statements
        cache[prevIndex+1][currentIndex] =  Math.min(delOp1, delOp2);
        return cache[prevIndex+1][currentIndex];
    }


    public static void main(String[] args) {
        MinimumDeletionsToMakeSequenceSorted mdss = new MinimumDeletionsToMakeSequenceSorted();
        int[] nums = {4,2,3,6,10,1,12};
        System.out.println(mdss.findMinimumDeletionsApproach2(nums));
        nums = new int[]{-4,10,3,7,15};
        System.out.println(mdss.findMinimumDeletionsApproach2(nums));
        nums = new int[]{3,2,1,0};
        System.out.println(mdss.findMinimumDeletionsApproach2(nums));
    }

}
