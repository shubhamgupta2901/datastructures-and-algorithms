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
        int result =  helper(denominations, total, 0);
        if(result == Integer.MAX_VALUE)
            return -1;
        return result;
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
        int result =  helper(denominations, total, 0,cache);
        if(result == Integer.MAX_VALUE)
            return -1;
        return result;
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

    /**
     * Approach 3: Bottom up DP Tabulation
     * table[i][t] would represent the min number of coins that we need to make target t from
     * denominations starting at index i to index denominations.length-1.
     * Time Complexity: O(n*T)
     * Space Complexity: O(n*T)
     */
    private int findMinCoinChangeApproach3(int[] denominations, int total){
        int[][] table = new int[denominations.length][total+1];

        //base case:
        for(int i = 0; i<denominations.length; i++)
            table[i][0] = 0;

        //fill table bottom up
        for(int i = denominations.length-1; i>=0; i--){
            for (int t = 1; t<=total; t++){
                int ways1 = Integer.MAX_VALUE;
                if(i<denominations.length-1)
                    ways1 =  table[i+1][t];
                int ways2 = Integer.MAX_VALUE;
                if(t>= denominations[i]){
                    int ways = table[i][t-denominations[i]];
                    if(ways!=Integer.MAX_VALUE)
                        ways2 = 1 + ways;
                }
                table[i][t] = Math.min(ways1, ways2 );
            }
        }
        return table[0][total] == Integer.MAX_VALUE ? -1 : table[0][total];
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
        System.out.println("-------------------");
        System.out.println("Tabulation");
        denominations = new int[]{1, 2, 3};
        System.out.println(cc.findMinCoinChangeApproach3(denominations, 5));
        System.out.println(cc.findMinCoinChangeApproach3(denominations, 11));
        System.out.println(cc.findMinCoinChangeApproach3(denominations, 7));
        denominations = new int[]{3, 5};
        System.out.println(cc.findMinCoinChangeApproach3(denominations, 7));
    }
}
