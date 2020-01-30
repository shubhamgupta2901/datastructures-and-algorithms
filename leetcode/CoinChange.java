package leetcode;

/**
 * <a href="https://leetcode.com/problems/coin-change/"/>
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1:
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange {

    /**
     * Approach 1: Brute force recursion.
     * Trying out all the combinations and finding the minimum number of coins required
     * Time limit exceeds for larger amounts
     */
    public int coinChangeApproach1(int[] coins, int amount) {
        return changeHelper(coins, amount);
    }

    private int changeHelper(int[] coins, int amount){
        if(amount < 0 || coins.length == 0)
            return -1;
        if(amount == 0)
            return 0;
        int minChange = Integer.MAX_VALUE;
        for(int i = 0; i<coins.length;i++){
            int change = changeHelper(coins, amount-coins[i]);
            if(change==-1)
                continue;
            change = 1 + change;
            if(change< minChange)
                minChange = change;
        }
        return minChange == Integer.MAX_VALUE ? -1: minChange;
    }


    /**
     * Approach 2: DP: Memoizing brute force recursive solution using a cache array.
     * Solution accepted but very slow. Will attempt tabulization too.
     */
    public int coinChange(int[] coins, int amount) {
        Integer[] cache = new Integer[amount+1];
        return changeHelper(coins, amount,cache);
    }

    private int changeHelper(int[] coins, int amount,Integer[] cache){
        if(amount < 0 || coins.length == 0)
            return -1;
        if(amount == 0)
            return 0;
        if(cache[amount]!= null)
            return cache[amount];
        int minChange = Integer.MAX_VALUE;
        for(int i = 0; i<coins.length;i++){
            int change = changeHelper(coins, amount-coins[i],cache);
            if(change==-1)
                continue;
            change = 1 + change;
            if(change< minChange)
                minChange = change;
        }
        minChange = (minChange == Integer.MAX_VALUE) ? -1: minChange;
        cache[amount] = minChange;
        return minChange;
    }




    public static void main(String[] args) {
        int[] coins = new int[]{1,2,5};
        int amount = 100;
        CoinChange cc = new CoinChange();
        int minChange = cc.coinChange(coins, amount);
        System.out.println(minChange);

    }
}
