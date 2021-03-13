package problem_solving.graphs;
import java.util.*;
/**
 * Given a matrix of integers A of size N x M consisting of 0 and 1. A group of connected 1's forms an island.
 * From a cell (i, j) such that A[i][j] = 1 you can visit any cell that shares a corner with (i, j) and value in that cell is 1.
 *
 * More formally, from any cell (i, j) if A[i][j] = 1 you can visit:
 * (i-1, j) if (i-1, j) is inside the matrix and A[i-1][j] = 1.
 * (i, j-1) if (i, j-1) is inside the matrix and A[i][j-1] = 1.
 * (i+1, j) if (i+1, j) is inside the matrix and A[i+1][j] = 1.
 * (i, j+1) if (i, j+1) is inside the matrix and A[i][j+1] = 1.
 * (i-1, j-1) if (i-1, j-1) is inside the matrix and A[i-1][j-1] = 1.
 * (i+1, j+1) if (i+1, j+1) is inside the matrix and A[i+1][j+1] = 1.
 * (i-1, j+1) if (i-1, j+1) is inside the matrix and A[i-1][j+1] = 1.
 * (i+1, j-1) if (i+1, j-1) is inside the matrix and A[i+1][j-1] = 1.
 * Return the number of islands.
 * NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.
 *
 * Problem Constraints
 * 1 <= N, M <= 100
 * 0 <= A[i] <= 1
 *
 * Input Format: only argument given is the integer matrix A.
 * Output Format: Return the number of islands.
 *
 * Input 1:
 *
 *  A = [
 *        [0, 1, 0]
 *        [0, 0, 1]
 *        [1, 0, 0]
 *      ]
 * Output: 2
 *
 * Input 2:
 *  A = [
 *        [1, 1, 0, 0, 0]
 *        [0, 1, 0, 0, 0]
 *        [1, 0, 0, 1, 1]
 *        [0, 0, 0, 0, 0]
 *        [1, 0, 1, 0, 1]
 *      ]
 * Output: 5
 */
public class NumberOfIslands {

    /**
     * Approach: BFS
     */
    static class Approach1{
        class Pair{
            int i, j;
            Pair(int i, int j){
                this.i = i;
                this.j = j;
            }
        }
        public int solve(int[][] matrix) {
            int rows = matrix.length;
            int columns = matrix[0].length;
            boolean[][] visited = new boolean[rows][columns];

            int islands = 0;
            for(int i = 0; i< rows; i++){
                for(int j = 0; j< columns; j++){
                    if(matrix[i][j] == 1 && visited[i][j] == false){
                        islands++;
                        performBfs(matrix, visited, i, j);
                    }
                }
            }
            return islands;
        }

        private void performBfs(int[][] matrix, boolean[][] visited, int iStart, int jStart){
            int[] iIncrements = new int[]{-1,1,0,0,-1,1,1,-1};
            int[] jIncrements = new int[]{0,0,-1,1,-1,1,-1,1};
            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(iStart, jStart));
            visited[iStart][jStart] = true;
            while(!queue.isEmpty()){
                Pair pair = queue.remove();
                for(int i = 0; i<iIncrements.length; i++){
                    int newI = pair.i + iIncrements[i];
                    int newJ = pair.j + jIncrements[i];
                    if(isValid(matrix, newI, newJ) && matrix[newI][newJ] == 1 && visited[newI][newJ] == false){
                        visited[newI][newJ] = true;
                        queue.add(new Pair(newI, newJ));
                    }
                }
            }
            return;
        }

        private boolean isValid(int[][] matrix, int i, int j){
            if(i< 0 || j<0)
                return false;
            if(i>= matrix.length || j>= matrix[0].length)
                return false;
            return true;
        }
    }

    /**
     * Approach2: DFS traversal
     */
    static class Approach2{
        public int solve(int[][] matrix) {
            int rows = matrix.length;
            int columns = matrix[0].length;
            boolean[][] visited = new boolean[rows][columns];
            int islands = 0;
            for(int i = 0; i< rows; i++){
                for(int j = 0; j< columns; j++){
                    if(matrix[i][j] == 1 && visited[i][j] == false){
                        islands++;
                        dfs(matrix, visited, i, j);
                    }
                }
            }
            return islands;
        }

        private void dfs(int[][] matrix, boolean[][] visited, int i, int j){
            visited[i][j] = true;
            int[] iIncrements = new int[]{-1, 0, 1, 0, -1, 1, -1, 1};
            int[] jIncrements = new int[]{0, -1, 0, 1 ,-1, 1, 1, -1};
            for(int index = 0; index< iIncrements.length; index++){
                int newI = i + iIncrements[index];
                int newJ = j + jIncrements[index];
                if(isValid(matrix, newI , newJ) && matrix[newI][newJ]==1 && visited[newI][newJ] == false){
                    dfs(matrix, visited, newI, newJ);
                }
            }
        }

        private boolean isValid(int[][] matrix, int i , int j){
            if(i<0 || j< 0)
                return false;
            if(i>=matrix.length || j>= matrix[0].length)
                return false;
            return true;
        }
    }

    public static void main(String[] args) {
        NumberOfIslands.Approach1 driver = new NumberOfIslands.Approach1();
        int output = driver.solve(new int[][]{{0, 0, 1, 0, 1, 0, 1, 1, 1},{0, 1, 0, 0, 1, 1, 1, 0, 1}});
        System.out.println(output);
    }
}
