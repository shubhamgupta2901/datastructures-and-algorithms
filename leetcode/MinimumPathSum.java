package leetcode;

/**
 * <a href="https://leetcode.com/problems/minimum-path-sum/"/>
 *
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 *
 * Example:
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class MinimumPathSum {
    /**
     * Approach 1: Brute force approach - Time limit exceeds
     * Try all the possible moves and find minimum path sum.
     * Solution seems correct but time limit exceeds for larger grids.
     * Time complexity : ? Its evident that its exponential O(2^N) because at taking two decisions,
     * but what is N? probably it is (m+n) for m*n grid. Need to check.
     * Space complexity : O(N)
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        return helper(grid, 0, 0);
    }

    private int helper(int[][]grid, int i, int j){
        if(i>= grid.length || j>= grid[0].length)
            return Integer.MAX_VALUE;
        if(i == grid.length-1 && j == grid[0].length-1)
            return grid[grid.length-1][grid[0].length-1];
        int sum = grid[i][j] + Math.min(helper(grid,i+1,j),helper(grid, i, j+1));
        return sum;
    }

    /**
     * Approach 2: Dynamic Programming memoization - Accepted
     * Its difficult for me to say, how this code works and has 99.4% time complexity in leetcode.
     * Memoization is generally used when you can see overlapping subproblems
     * and you cache them to save time. Here I could not see any overlapping subproblems.
     * Time Complexity: O(mn)
     * Space Complexity: O(mn)
     */
    public int minPathSumApproach2(int[][] grid) {
        Integer[][]cache = new Integer[grid.length][grid[0].length];
        return helper(grid, 0, 0, cache);
    }

    private int helper(int[][]grid, int i, int j, Integer[][] cache){
        if(i>= grid.length || j>= grid[0].length)
            return Integer.MAX_VALUE;
        if(i == grid.length-1 && j == grid[0].length-1)
            return grid[grid.length-1][grid[0].length-1];
        if(cache[i][j]!=null)
            return cache[i][j];
        int sum = grid[i][j] + Math.min(helper(grid,i+1,j, cache),helper(grid, i, j+1,cache));
        cache[i][j] = sum;
        return sum;
    }
}
