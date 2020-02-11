package algorithms.dynamic_programming.fibonacci;

import algorithms.dynamic_programming.unbounded_knapsack.MinimumCoinChange;

/**
 * Given an array of positive numbers, where each element represents the max number of jumps that can be made forward
 * from that element, write a program to find the minimum number of jumps needed to reach the end of the array
 * (starting from the first element). If an element is 0, then we cannot move through that element.
 *
 * Example 1:
 * Input = {2,1,1,1,4}
 * Output = 3
 * Explanation: Starting from index '0', we can reach the last index through: 0->2->3->4
 *
 * Example 2:
 * Input = {1,1,3,6,9,3,0,1,3}
 * Output = 4
 * Explanation: Starting from index '0', we can reach the last index through: 0->1->2->3->8
 */
public class MinimumJumpsToReachEnd {

    /**
     * Approach 1: Brute force solution
     * We will start with the '0’th index and try all options.
     * So, if the value at the current index is ‘p’, we will try every jump in the range (1 to ‘p’) from that index.
     * After taking a jump, we recursively try all option from that index.
     *
     */
    private int countMinJumps(int[] jumps){
        int minJumps =  helper(jumps, 0);
        return minJumps == Integer.MAX_VALUE ? -1 : minJumps;
    }

    private int helper(int[] jumps, int index){
        if(index >= jumps.length-1)
            return 0;
        if(jumps[index] == 0)
            return Integer.MAX_VALUE;
        int minJumps = Integer.MAX_VALUE;
        for(int i = 1; i<=jumps[index]; i++){
            int jump = helper(jumps, index+i);
            if(jump !=Integer.MAX_VALUE && (1+jump) < minJumps)
                minJumps = 1 + jump;
        }
        return minJumps;
    }

    public static void main(String[] args) {
        MinimumJumpsToReachEnd aj = new MinimumJumpsToReachEnd();
        int[] jumps = {2, 1, 1, 1, 4};
        System.out.println(aj.countMinJumps(jumps));
        jumps = new int[]{1, 1, 3, 6, 9, 3, 0, 1, 3};
        System.out.println(aj.countMinJumps(jumps));
    }


}
