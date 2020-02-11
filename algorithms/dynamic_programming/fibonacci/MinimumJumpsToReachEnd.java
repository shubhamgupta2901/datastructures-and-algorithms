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
     * Time complexity: O(2^n) where ‘n’ is the size of the input array.
     * Need to check how for loop effects the time complexity.
     * The space complexity is O(n) which is used to store the recursion stack.
     *
     * TODO: We can optimise the for-loop by running it till from 1 to min(jump[index],jumps.length).
     */
    private int countMinJumpsApproach1(int[] jumps){
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

    /**
     * Approach 2: Top Down Dynamic Programming - Memoization
     * saving the results of subproblems in an array, so that overlapping subproblems don't need
     * to be solved again.
     */
    private int countMinJumpsApproach2(int[] jumps){
        Integer[] cache = new Integer[jumps.length];
        int minJumps =  helper(jumps, 0, cache);
        return minJumps == Integer.MAX_VALUE ? -1 : minJumps;
    }

    private int helper(int[] jumps, int index, Integer[] cache){
        if(index >= jumps.length-1)
            return 0;
        if(jumps[index] == 0)
            return Integer.MAX_VALUE;
        if(cache[index]!=null)
            return cache[index];
        int minJumps = Integer.MAX_VALUE;
        for(int i = 1; i<=jumps[index]; i++){
            int jump = helper(jumps, index+i, cache);
            if(jump !=Integer.MAX_VALUE && (1+jump) < minJumps)
                minJumps = 1 + jump;
        }
        cache[index] = minJumps;
        return minJumps;
    }


    /**
     * table[i] represents the minimum number of jumps required to reach the end of array,
     * in the subarray of 'jumps' starting from index i to jumps.length-1
     * We are interested in table[0]
     */
    private int countMinJumpsApproach3(int[] jumps){
        int length = jumps.length;
        int[] table = new int[length];

        //base case: 0 steps needed to go from last index to last index
        table[length-1] = 0;

        for(int i = length-2; i>=0; i--){
            int minJump = Integer.MAX_VALUE;
            for(int jump = 1; jump<=jumps[i]; jump++){
                if(get(table,i+jump) < minJump)
                    minJump = table[i+jump];
            }
            table[i] = minJump == Integer.MAX_VALUE ? minJump : 1 + minJump;
        }

        return table[0];

    }

    private int get(int[]table, int index){
        return index < table.length ? table[index] : 0;
    }

    public static void main(String[] args) {
        MinimumJumpsToReachEnd aj = new MinimumJumpsToReachEnd();
        int[] jumps = {2, 1, 1, 1, 4};
        System.out.println(aj.countMinJumpsApproach3(jumps));
        jumps = new int[]{1, 1, 3, 6, 9, 3, 0, 1, 3};
        System.out.println(aj.countMinJumpsApproach3(jumps));
    }


}
