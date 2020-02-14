package leetcode;

/**
 * https://leetcode.com/problems/unique-paths/submissions/
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.
 * Note: m and n will be at most 100.
 *
 * How many possible unique paths are there?
 *
 * Example 1:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 * Example 2:
 *
 * Input: m = 7, n = 3
 * Output: 28
 * TODO: Dynamic Programming solution
 */
public class UniquePaths {
    /**
     * Approach 1: Permutation - Accepted
     * My idea of implementation was that there are total m-1 right moves the robot can move.
     * And there are total n-1 down moves that the robot can take.
     * In m+n-2 moves he will reach to the matrix[m][n], if he is at matrix[0][0] at start.
     * So total possible combinations of moves can be (m-1+n-1)!/(m-1)!(n-1)!.
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsApproach1(int m, int n) {
        if(m == 0 || n==0)
            return 0;
        if(m == 1 || n==1)
            return 1;
        if(m>n)
            return (int)(multiplyTillLimit(m+n-2,m-1) /factorial(n-1));
        else return (int)(multiplyTillLimit(m+n-2,n-1)/factorial(m-1));
    }

    private long factorial(int x){
        long factorial = 1;
        while(x>0){
            factorial*=x;
            x--;
        }
        return factorial;
    }

    private long multiplyTillLimit(int x, int limit){
        long product = 1;
        while(x>limit){
            product*= x;
            x--;
        }
        return product;
    }

    /**
     * Approach 2 : Top Down Dynamic Programming with memoization - Accepted
     *
     */
    public int uniquePathsApproach2(int m, int n) {
        if(m == 0 || n ==0)
            return 0;
        if(m ==1 || n == 1)
            return 1;
        Integer[][] cache = new Integer[m][n];
        return helper(m-1, n-1, 0, 0, cache);
    }

    private int helper(int m, int n, int mIndex, int nIndex, Integer[][]cache){
        //base case
        if(mIndex == m && nIndex == n)
            return 1;
        if(cache[mIndex][nIndex]!=null)
            return cache[mIndex][nIndex];
        //recurrence
        int ways1 = mIndex < m ? helper(m, n, mIndex+1, nIndex, cache) : 0;
        int ways2 = nIndex < n ? helper(m, n, mIndex, nIndex+1, cache) : 0;
        //return
        cache[mIndex][nIndex] =  ways1+ways2;
        return cache[mIndex][nIndex];
    }

    /**
     * Approach 3: Bottom up Dynamic Programming - Accepted
     */
    public int uniquePathsApproach3(int m, int n) {
        if(m == 0 || n ==0)
            return 0;
        if(m ==1 || n == 1)
            return 1;
        int[][] table = new int[m][n];

        //table[i][j] represents number of unique paths from grid[i][j] to grid[m][n]
        // we want to find table[0][0]

        //bottom up case: table[i][n-1] = 1
        for(int i = 0; i<m ; i++)
            table[i][n-1] = 1;

        //bottom up case: table[m-1][j] = 1
        for(int j = 0; j<n; j++)
            table[m-1][j] = 1;

        //filling table bottom-up
        //table[i][j] = table[i+1][j] + table[i][j+1]
        for(int i = m-2; i>= 0; i--){
            for(int j = n-2; j>=0; j--){
                table[i][j] = table[i+1][j] + table[i][j+1];
            }
        }

        return table[0][0];
    }

}
