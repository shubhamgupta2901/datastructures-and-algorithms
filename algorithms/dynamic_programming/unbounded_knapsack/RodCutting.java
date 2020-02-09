package algorithms.dynamic_programming.unbounded_knapsack;

/**
 * Given a rod of length ‘n’, we are asked to cut the rod and sell the pieces in a way that will maximize the profit.
 * We are also given the price of every piece of length ‘i’ where ‘1 <= i <= n’.
 *
 * Example:
 * Lengths: [1, 2, 3, 4, 5]
 * Prices: [2, 6, 7, 10, 13]
 * Rod Length: 5
 * Solution: We get the maximum price 14 by cutting the rod into two pieces of length ‘2’ and one piece of length ‘1’.
 */
public class RodCutting {

    /**
     * Approach 1: Brute force recursion
     * Time Complexity: O(2^(n+N)) where N is the length of rod and n is the number of elements in array
     * Space Complexity: O(n+N) for recursive stack
     */
    private int solveRodCuttingApproach1(int[] prices, int[] lengths, int n){
        return helper(prices, lengths, n, 0);
    }

    private int helper(int[] prices, int[] lengths, int n, int index){
        if(index==prices.length || n== 0)
            return 0;
        int profit1 = helper(prices, lengths, n, index+1);
        int profit2 = 0;
        if(lengths[index] <= n)
            profit2 = prices[index] + helper(prices, lengths, n-lengths[index],index);
        return Math.max(profit1,profit2);
    }

    public static void main(String[] args) {
        RodCutting rc = new RodCutting();
        int[] lengths = {1, 2, 3, 4, 5};
        int[] prices = {2, 6, 7, 10, 13};
        int maxProfit = rc.solveRodCuttingApproach1(prices, lengths, 5);
        System.out.println(maxProfit);
    }
}
