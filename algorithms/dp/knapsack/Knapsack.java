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
     * Time complexity: O(2^n) : there will be 2^n subsets and for each subset can have at max n elements
     * Space complexity: O(2^n): Storing the 2^n subsets.
     */
    private int solveKnapsackApproach1(int[] profits, int[] weights, int capacity){
        List<List<Integer>> subsets = getValidWeightSets(weights);
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

    private List<List<Integer>>  getValidWeightSets(int[] weights){
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

    /**
     * Approach 2: A better Brute force approach : recursive
     * Time Complexity: O(2^​n)
     * ​​Space complexity is O(n). This space will be used to store the recursion stack.
     * Since our recursive algorithm works in a depth-first fashion,
     * which means, we can’t have more than ‘n’ recursive calls on the call stack at any time.
     */
    private int solveKnapsackApproach2(int[] profits, int[] weights, int capacity){
        return knapsackHelper(profits,weights, capacity, 0);
    }

    private int knapsackHelper(int [] profits, int[] weights, int capacity, int index){
        if(capacity == 0 || index == weights.length)
            return 0;
        int profit1 = knapsackHelper(profits, weights, capacity, index+1);
        int profit2 = 0;
        if(capacity >= weights[index])
            profit2 = knapsackHelper(profits, weights,capacity-weights[index], index+1) + profits[index];
        return Math.max(profit1, profit2);
    }

    /**
     * Approach 3: top down dynamic programming
     * Recusive solution as approach 2 with memoization.
     * For overlapping subproblems, we can use memoization to solve them.
     * Memoization is when we store the results of all the previously solved sub-problems and
     * return the results from memory if we encounter a problem that has already been solved.
     * Since we have two changing values (capacity and index) in our recursive function,
     * we can use a two-dimensional array to store the results of all the solved sub-problems.
     * Time Complexity: O(N*C) where N is length of weights array, and C is capacity
     * Space Complexity: O(N*C) for memoization array
     */
    private int solveKnapsackApproach3(int[] profits, int [] weights, int capacity){
        int [][] cache = new int[profits.length][capacity+1];
        return knapsackHelper(profits, weights, capacity, 0, cache);
    }

    private int knapsackHelper(int [] profits, int[] weights, int capacity, int currentIndex, int[][] cache){
        if(capacity == 0 || currentIndex == profits.length){
            return 0;
        }

        if(cache[currentIndex][capacity] != 0)
            return cache[currentIndex][capacity];

        int profit1  = knapsackHelper(profits, weights, capacity, currentIndex+1, cache);
        int profit2 = 0;
        if(capacity>= weights[currentIndex])
            profit2 = profits[currentIndex] + knapsackHelper(profits, weights, capacity-weights[currentIndex],currentIndex+1, cache);

        int profit = Math.max(profit1, profit2);
        cache[currentIndex][capacity] = profit;
        return profit;
    }


    /**
     * Approach 4: Tabulization (Bottom up approach)
     *
     */
    private int solveKnapsackApproach4(int[] profits, int[] weights, int capacity){
        int[][]table = new int[profits.length][capacity+1];

        for(int index = profits.length-1; index>=0; index--){
            for(int c = 0; c<= capacity; c++){
                int profit1 = get(table, index+1, c);
                int profit2 = 0;
                if(weights[index]<=c)
                    profit2 = profits[index] + get(table, index+1, c-weights[index]);
                table[index][c] = Math.max(profit1, profit2);
            }
        }
        return table[0][capacity];

    }

    private int get(int[][] table, int i, int c){
        if(i >= table.length || c<0)
            return 0;
        return table[i][c];
    }
    
    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsackApproach4(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = ks.solveKnapsackApproach4(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}