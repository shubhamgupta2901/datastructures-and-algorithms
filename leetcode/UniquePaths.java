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
     * My idea of implementation was that there are total m-1 right moves the robot can move.
     * And there are total n-1 down moves that the robot can take.
     * In m+n-2 moves he will reach to the matrix[m][n], if he is at matrix[0][0] at start.
     * So total possible combinations of moves can be (m-1+n-1)!/(m-1)!(n-1)!.
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
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
}
