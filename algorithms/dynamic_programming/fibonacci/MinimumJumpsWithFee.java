package algorithms.dynamic_programming.fibonacci;

/**
 * Given a staircase with ‘n’ steps and an array of ‘n’ numbers representing the fee that you have to pay
 * if you take the step. Implement a method to calculate the minimum fee required to reach the top of
 * the staircase (beyond the top-most step). At every step, you have an option to take either
 * 1 step, 2 steps, or 3 steps. You should assume that you are standing at the first step.
 *
 * Example 1:
 * Number of stairs (n) : 6
 * Fee: {1,2,5,2,1,2}
 * Output: 3
 * Explanation: Starting from index '0', we can reach the top through: 0->3->top
 * The total fee we have to pay will be (1+2).
 *
 * Example 2:
 * Number of stairs (n): 4
 * Fee: {2,3,4,5}
 * Output: 5
 * Explanation: Starting from index '0', we can reach the top through: 0->1->top
 * The total fee we have to pay will be (2+3).
 */
public class MinimumJumpsWithFee {

    /**
     * Time Complexity: O(3^n)
     * Space Complexity: O(n) for recursion stack.
     */
    private int minimumFeeApproach1(int[] fees){
        return helper(fees,0);
    }

    private int helper(int[] fees, int index){
        //base case:
        if(index == fees.length-1)
            return fees[fees.length-1];

        if(index>=fees.length)
            return 0;

        //recursion
        int fees1 = helper(fees,index+1);
        int fees2 = helper(fees, index+2);
        int fees3 = helper(fees, index+3);

        //return
        return fees[index] + Math.min(Math.min(fees1,fees2), fees3);
    }

    /**
     * Approach 2: Top Down Memoization
     * store the solutions of overlapping subproblems
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private int minimumFeeApproach2(int[] fees){
        Integer[] cache = new Integer[fees.length];
        return helper(fees,0, cache);
    }

    private int helper(int[] fees, int index, Integer[] cache){
        //base case:
        if(index == fees.length-1)
            return fees[fees.length-1];

        if(index>=fees.length)
            return 0;
        //memomization
        if(cache[index] != null)
            return cache[index];

        //recursion
        int fees1 = helper(fees,index+1);
        int fees2 = helper(fees, index+2);
        int fees3 = helper(fees, index+3);

        //memoization & return
        cache[index] =  fees[index] + Math.min(Math.min(fees1,fees2), fees3);
        return cache[index];
    }

    /**
     * Approach 3: Bottom up DP - Tabulation
     * create a 1-d array table of length fees.length
     * table[i] represents minimum fees required to reach the top (beyond the top most stair)
     * in fees subarray starting from i to fees.length-1
     * We are interested in table[0]
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private int minimumFeeApproach3(int[] fees){
        int length = fees.length;
        int[] table = new int[length];

        //Base cases
        table[length-1] = fees[length-1];

        for(int i = length-2; i>=0; i--){
            table[i] = fees[i] + Math.min(
                            Math.min(
                                    get(table, i+1),
                                    get(table, i+2)),
                            get(table,i+3));
        }
        return table[0];
    }

    private int get(int[] table, int i){
        return (i >= table.length) ? 0 : table[i];
    }

    public static void main(String[] args) {
        MinimumJumpsWithFee sc = new MinimumJumpsWithFee();
        int[] fee = {1,2,5,2,1,2};
        System.out.println(sc.minimumFeeApproach3(fee));
        fee = new int[]{2,3,4,5};
        System.out.println(sc.minimumFeeApproach3(fee));
    }
}
