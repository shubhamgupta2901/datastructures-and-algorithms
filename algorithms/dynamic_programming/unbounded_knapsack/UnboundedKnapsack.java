package algorithms.dynamic_programming.unbounded_knapsack;

/**
 * Given two integer arrays to represent weights and profits of ‘N’ items, we need to find a subset
 * of these items which will give us maximum profit such that their cumulative weight is not more
 * than a given number ‘C’. We can assume an infinite supply of item quantities; therefore,
 * each item can be selected multiple times.
 *
 * The only difference between the 0/1 Knapsack problem and this problem is
 * that we are allowed to use an unlimited quantity of an item.
 *
 * Example:
 * Items: { Apple, Orange, Melon }
 * Weights: { 1, 2, 3 }
 * Profits: { 15, 20, 50 }
 * Knapsack capacity: 5
 * Solution: 2 Apples + 1 Melon = 80 profit
 */
public class UnboundedKnapsack {

    /**
     * Approach 1: Brute force
     * A basic brute-force solution could be to try all combinations of the given items to choose the one
     * with maximum profit and a weight that doesn't exceed ‘C’. This is what our algorithm will look like:
     *
     * for each item 'i'
     *   create a new set which includes one quantity of item 'i' if it does not exceed the capacity, and
     *      recursively call to process ALL items
     *   create a new set without item 'i', and recursively process the REMAINING items
     * return the set from the above two sets with higher profit
     *
     * Time complexity:  O(2^{N+C}) where ‘N’ represents the total number of items and C is capacity.
     * Space complexity: will be O(N+C) to store the recursion stack.
     */
    private int solveKnapsackApproach1(int[] profits, int[] weights, int capacity){
        return knapsackHelper(profits, weights, capacity, 0);
    }

    private int knapsackHelper(int [] profits, int[] weights, int capacity, int index){
        if(capacity == 0 || index==profits.length)
            return 0;
        int profit1 = 0;
        if(weights[index]<= capacity)
            profit1 = profits[index] + knapsackHelper(profits, weights, capacity-weights[index], index);
        int profit2 = knapsackHelper(profits, weights, capacity, index+1);
        return Math.max(profit1, profit2);
    }


    public static void main(String[] args) {
        UnboundedKnapsack ks = new UnboundedKnapsack();
        int[] profits = { 15, 50, 60, 90 };
        int[] weights = { 1, 3, 4, 5 };
        int maxProfit = ks.solveKnapsackApproach1(profits, weights, 8);
        System.out.println(maxProfit);
    }

}
