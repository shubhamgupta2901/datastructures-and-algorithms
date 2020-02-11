package algorithms.dynamic_programming.fibonacci;

/**
 * There are ‘n’ houses built in a line. A thief wants to steal maximum possible money from these houses.
 * The only restriction the thief has is that he can’t steal from two consecutive houses, as that would
 * alert the security system. Determine the maximum amount of money the thief can steal without alerting
 * the security system.
 *
 * Example 1:
 * Input: {2, 5, 1, 3, 6, 2, 4}
 * Output: 15
 * Explanation: The thief should steal from houses 5 + 6 + 4
 *
 * Example 2:
 * Input: {2, 10, 14, 8, 1}
 * Output: 18
 * Explanation: The thief should steal from houses 10 + 8
 */
public class HouseThief {
    /**
     * Approach 1: Brute force
     * For every house ‘i’, we have two options:
     *   Steal from the current house (‘i’), skip one and steal from (‘i+2’).
     *   Skip the current house (‘i’), and steal from the adjacent house (‘i+1’).
     * Choose the one with the maximum amount from the above two options.
     * Time Complexity: O(2^n)
     * Space Complexity: O(n)
     */
    private int findMaxStealApproach1(int[] wealth){
        return helper(wealth,0);
    }

    private int helper (int[] wealth, int index){
        if(index >= wealth.length)
            return 0;
        int profit1 = helper(wealth, index+1);
        int profit2 = wealth[index] + helper(wealth,index+2);
        return Math.max(profit1, profit2);
    }


    /**
     * Approach 2: Top Down Dynamic Programming - Memoization
     * Create an array of size wealth.length and save the results of solved subproblems,
     * this way when the overlapping subproblems are encountered, it can be fetched from cache.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private int findMaxStealApproach2(int[] wealth){
        Integer[] cache = new Integer[wealth.length];
        return helper(wealth,0, cache);
    }

    private int helper(int[] wealth, int index, Integer[] cache){
        if(index >= wealth.length)
            return 0;
        if(cache[index]!=null)
            return cache[index];
        int profit1 = helper(wealth, index+1,cache);
        int profit2 = wealth[index] + helper(wealth,index+2, cache);
        cache[index] =  Math.max(profit1, profit2);
        return cache[index];
    }



    public static void main(String[] args) {
        HouseThief ht = new HouseThief();
        int[] wealth = {2, 5, 1, 3, 6, 2, 4};
        System.out.println(ht.findMaxStealApproach2(wealth));
        wealth = new int[]{2, 10, 14, 8, 1};
        System.out.println(ht.findMaxStealApproach2(wealth));
    }
}
