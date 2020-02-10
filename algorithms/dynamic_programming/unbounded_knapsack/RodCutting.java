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


    /**
     * Approach 2: Memoizing recursion
     * Time Complexity: O(n*N)
     * Space Complexity: O(n*N)
     */
    private int solveRodCuttingApproach2(int[] prices, int[] lengths, int n){
        Integer[][] cache = new Integer[prices.length][n+1];
        return helper(prices, lengths, n, 0, cache);
    }

    private  int helper(int[] prices, int [] lengths, int n, int index, Integer[][] cache){
        if(n == 0 || index == prices.length)
            return 0;
        if(cache[index][n]!=null)
            return cache[index][n];
        int profit = helper(prices, lengths, n, index+1, cache);
        if(lengths[index]<= n)
            profit = Math.max(profit,
                    prices[index] + helper(prices, lengths, n-lengths[index],index,cache));
        cache[index][n] = profit;
        return profit;
    }


    /**
     * Approach 3: Bottom up tabulation DP
     * table[i][c] represents the what is the maximum profit we can make from cutting a rod of length 'c'
     * using lengths from index i to lengths.length-1
     * We are interested in table[0][n]
     * Time Complexity: O(n*N)
     * Space Complexity: O(n*N)
     *
     */
    private int solveRodCuttingApproach3(int[] prices, int[] lengths, int n){
        if (n <= 0 || prices.length == 0 || prices.length != lengths.length)
            return 0;
        int [][] table = new int[prices.length][n+1];

        //Base case: if n = 0, profit made will be 0
        for(int i = 0; i<prices.length; i++)
            table[i][n] = 0;

        for(int i = prices.length-1; i>=0; i--){
            for(int c = 1; c<=n ; c++){
                int profit1 = i < prices.length-1 ? table[i+1][c] : 0;
                int profit2 = c>=lengths[i] ? prices[i] + table[i][c-lengths[i]] : 0;
                table[i][c] = Math.max(profit1,profit2);
            }
        }

        return table[0][n];

    }

    public static void main(String[] args) {
        RodCutting rc = new RodCutting();
        int[] lengths = {1, 2, 3, 4, 5};
        int[] prices = {2, 6, 7, 10, 13};
        int maxProfit = rc.solveRodCuttingApproach2(prices, lengths, 5);
        System.out.println(maxProfit);
    }
}
