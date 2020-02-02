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
     * Approach 1: Brute force approach
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
}
