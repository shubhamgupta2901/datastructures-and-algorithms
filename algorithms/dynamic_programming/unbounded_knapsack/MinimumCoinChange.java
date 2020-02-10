package algorithms.dynamic_programming.unbounded_knapsack;

/**
 * Given a number array to represent different coin denominations and a total amount ‘T’, we need to find
 * the minimum number of coins needed to make change for ‘T’. We can assume an infinite supply of coins,
 * therefore, each coin can be chosen multiple times.
 *
 * Example 1:
 * Denominations: {1,2,3}
 * Total amount: 5
 * Output: 2
 * Explanation: We need minimum of two coins {2,3} to make a total of '5'
 *
 * Example 2:
 * Denominations: {1,2,3}
 * Total amount: 11
 * Output: 4
 * Explanation: We need minimum four coins {2,3,3,3} to make a total of '11'
 */
public class MinimumCoinChange {

    /**
     * Approach 1: Brute force Approach
     * Time Complexity: O(2^(n+T)) where n is the length of denominations, T is target
     * Space Complexity: O(n+T)
     */
    private int findMinCoinChangeApproach1(int[] denominations, int total){
        return helper(denominations, total, 0);
    }

    private int helper(int[] denominations,int total, int index){
        //base case:
        if(total == 0)
            return 0;
        if(index == denominations.length)
            return Integer.MAX_VALUE;
        //recursion
        int change1 =  Integer.MAX_VALUE;
        if(total>= denominations[index]){
            int change = helper(denominations, total-denominations[index],index);
            if(change!= Integer.MAX_VALUE)
                change1 =  1 + change;
        }

        int change2 = helper(denominations, total, index+1);

        //return
        return Math.min(change1, change2);

    }


    /**
     * Approach 1: Brute force Approach
     * Time Complexity: O(n*T) where n is the length of denominations, T is target
     * Space Complexity: O(n*T)
     */
    private int findMinCoinChangeApproach2(int[] denominations, int total){
        Integer[][] cache = new Integer[denominations.length][total+1];
        return helper(denominations, total, 0,cache);
    }

    private int helper(int[] denominations,int total, int index, Integer[][]cache){
        //base case:
        if(total == 0)
            return 0;
        if(index == denominations.length)
            return Integer.MAX_VALUE;
        //memoization
        if(cache[index][total]!=null)
            return cache[index][total];
        //recursion
        int change1 =  Integer.MAX_VALUE;
        if(total>= denominations[index]){
            int change = helper(denominations, total-denominations[index],index, cache);
            if(change!= Integer.MAX_VALUE)
                change1 =  1 + change;
        }

        int change2 = helper(denominations, total, index+1, cache);

        //return
        cache[index][total] = Math.min(change1, change2);
        return cache[index][total];

    }

    public static void main(String[] args) {
        MinimumCoinChange cc = new MinimumCoinChange();
        int[] denominations = {1, 2, 3};
        System.out.println("Brute Force");
        System.out.println(cc.findMinCoinChangeApproach1(denominations, 5));
        System.out.println(cc.findMinCoinChangeApproach1(denominations, 11));
        System.out.println(cc.findMinCoinChangeApproach1(denominations, 7));
        denominations = new int[]{3, 5};
        System.out.println(cc.findMinCoinChangeApproach1(denominations, 7));
        System.out.println("-------------------");
        System.out.println("Memoization");
        denominations = new int[]{1, 2, 3};
        System.out.println(cc.findMinCoinChangeApproach2(denominations, 5));
        System.out.println(cc.findMinCoinChangeApproach2(denominations, 11));
        System.out.println(cc.findMinCoinChangeApproach2(denominations, 7));
        denominations = new int[]{3, 5};
        System.out.println(cc.findMinCoinChangeApproach2(denominations, 7));
    }
}
