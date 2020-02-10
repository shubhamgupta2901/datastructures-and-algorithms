package algorithms.dynamic_programming.unbounded_knapsack;

/**
 * Given an infinite supply of ‘n’ coin denominations and a total money amount, we are asked to find
 * the total number of distinct ways to make up that amount.
 *
 * Denominations: {1,2,3}
 * Total amount: 5
 * Output: 5
 * Explanation: There are five ways to make the change for '5', here are those ways:
 * 1. {1,1,1,1,1}
 * 2. {1,1,1,2}
 * 3. {1,2,2}
 * 4. {1,1,3}
 * 5. {2,3}
 */
public class CoinChange {

    /**
     * Approach 1: Brute force
     * Try all combinations of the given coins to select the ones that give a total sum of ‘T’.
     *
     * for each coin 'c'
     *   create a new set which includes one quantity of coin 'c' if it does not exceed 'T', and
     *      recursively call to process all coins
     *   create a new set without coin 'c', and recursively call to process the remaining coins
     * return the count of sets who have a sum equal to 'T'
     *
     * Time Complexity: O(2^{N+T}) where N is number of denominations and T is the total amount we want to make change.
     * Space Complexity: O(N+T)
     */
    public int countChange(int[] denominations, int total){
        return helper(denominations, total, 0);
    }

    private int helper(int[] denominations, int total, int index){
        if(total == 0)
            return 1;
        if(index == denominations.length)
            return 0;
        int ways1 = 0;
        if(total>=denominations[index])
            ways1 = helper(denominations, total-denominations[index],index);
        int ways2 = helper(denominations, total, index+1);
        return ways1 + ways2;
    }


    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        int[] denominations = {1, 2, 3};
        System.out.println(cc.countChange(denominations, 5));
    }
}
