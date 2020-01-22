package algorithms.dp.knapsack;


import java.util.ArrayList;
import java.util.List;

/**
 * Given the weights and profits of ‘N’ items, we are asked to put these items in a knapsack which has a capacity ‘C’.
 * The goal is to get the maximum profit from the items in the knapsack. Each item can only be selected once,
 * as we don’t have multiple quantities of any item.
 *
 * Example:
 * Items: { Apple, Orange, Banana, Melon }
 * Weights: { 2, 3, 1, 4 }
 * Profits: { 4, 5, 3, 7 }
 * Knapsack capacity: 5
 *
 * Answer: Banana + Melon (total weight 5) => 10 profit
 */
class Knapsack {

    /**
     * Approach 1: Very bad brute force.
     * 1. Generate all the possible subsets of indexes of weights: O(2^n)
     * 2. Find the valid subsets which do not violate the capacity
     * 3. Get the maximumProfit subset from valid subsets.
     * Time complexity: O(n2^n) : there will be 2^n subsets and for each subset can have at max n elements
     * Space complexity: O(2^n): Storing the 2^n subsets.
     */
    private int solveKnapsack(int[] profits, int[] weights, int capacity){
        List<List<Integer>> subsets = getValidWeightSets(profits, weights, capacity);
        int maxProfit = 0;
        for(int i = 0; i<subsets.size(); i++){
            int totalProfit = 0, totalWeight = 0;
            List<Integer> subset =subsets.get(i);
            int j;
            for(j = 0; j<subset.size(); j++){
                int index = subset.get(j);
                if(totalWeight + weights[index] > capacity)
                    break;
                totalWeight += weights[index];
                totalProfit += profits[index];
            }
            if(j==subset.size() && totalProfit> maxProfit)
                maxProfit = totalProfit;
        }
        return maxProfit;
    }

    private List<List<Integer>>  getValidWeightSets(int[] profits, int[] weights, int capacity){
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> emptySubset = new ArrayList<>();
        subsets.add(emptySubset);
        for(int i = 0; i<weights.length; i++){
            int size =subsets.size();
            for(int j =0; j < size; j++){
                List<Integer> newSubset = new ArrayList<>(subsets.get(j));
                newSubset.add(i);
                subsets.add(newSubset);
            }
        }
        return subsets;
    }

    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}