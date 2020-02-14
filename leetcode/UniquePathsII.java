package leetcode;

/**
 * <a href ="https://leetcode.com/problems/unique-paths-ii/"/>
 * A robot is located at the top-left corner of a m x n grid.
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid .
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * Note: m and n will be at most 100.
 *
 * Input:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 */
public class UniquePathsII {
    /**
     * Approach 1: Top Down Dynamic Programming with Memoization - Accepted.
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     */
    public int uniquePathsWithObstaclesApproach1(int[][] obstacleGrid) {
        if(obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;
        if(obstacleGrid[0][0] == 1)
            return 0;
        Integer[][] cache = new Integer[obstacleGrid.length][obstacleGrid[0].length];
        return helper(obstacleGrid, 0, 0, cache);
    }

    private int helper(int[][] grid, int m, int n, Integer[][] cache){
        if(m == grid.length-1 && n== grid[0].length-1)
            return grid[m][n] == 0 ? 1 : 0;
        if(cache[m][n] != null)
            return cache[m][n];
        int ways1 = 0;
        if( m  < grid.length-1 && grid[m+1][n] == 0 )
            ways1 = helper(grid, m+1, n, cache);
        int ways2 = 0;
        if(n < grid[0].length-1 && grid[m][n+1] == 0)
            ways2 = helper(grid, m, n+1, cache);
        cache[m][n] =  ways1 + ways2;
        return cache[m][n];
    }
}
