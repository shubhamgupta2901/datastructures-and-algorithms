package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.com/problems/number-of-islands/"/>
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 *
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 */
public class NumberOfIslands {

    /**
     * Attempt 1: Trying something similar to bfs
     * While the solution seems correct, for large inputs the time limit exceeds.
     */
    class Solution {
        class Pair{
            int i, j;
            Pair(int i, int j){
                this.i = i;
                this.j = j;
            }
        }
        public int numIslands(char[][] grid) {
            int numIslands = 0;
            if(grid.length == 0 || grid[0].length==0)
                return numIslands;
            Queue<Pair> queue = new LinkedList<>();
            Pair newPair = getPair(grid,0,0);

            while(newPair!=null){
                queue.add(newPair);
                numIslands++;
                while(!queue.isEmpty()){
                    Pair pair = queue.remove();
                    grid[pair.i][pair.j] = '0';
                    if(isLand(pair.i-1,pair.j,grid))
                        queue.add(new Pair(pair.i-1, pair.j));
                    if(isLand(pair.i+1,pair.j,grid))
                        queue.add(new Pair(pair.i+1, pair.j));
                    if(isLand(pair.i,pair.j-1,grid))
                        queue.add(new Pair(pair.i, pair.j-1));
                    if(isLand(pair.i,pair.j+1,grid))
                        queue.add(new Pair(pair.i, pair.j+1));
                }
                newPair = getPair(grid,newPair.i, newPair.j);
            }
            return numIslands;

        }

        private boolean isLand(int i, int j, char[][]grid){
            if(i<0 || i>= grid.length)
                return false;
            if(j<0 || j>= grid[0].length)
                return false;
            if(grid[i][j] =='0')
                return false;
            return true;
        }

        private Pair getPair(char[][]grid,int I, int J){
            for(int i = I; i<grid.length; i++){
                for(int j = J;j<grid[0].length; j++){
                    if(grid[i][j] == '1')
                        return new Pair(i,j);
                }
            }
            return null;
        }
    }
}
