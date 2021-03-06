package algorithms.dynamic_programming.unbounded_knapsack;

/**
 * Given a number array to represent possible ribbon lengths and a total ribbon length ‘n’,
 * we need to find the maximum number of pieces that the ribbon can be cut into.
 *
 * Example 1:
 * n: 5
 * Ribbon Lengths: {2,3,5}
 * Output: 2
 * Explanation: Ribbon pieces will be {2,3}.
 *
 * Example 2:
 * n: 7
 * Ribbon Lengths: {2,3}
 * Output: 3
 * Explanation: Ribbon pieces will be {2,2,3}.
 *
 * Example 3:
 * n: 13
 * Ribbon Lengths: {3,5,7}
 * Output: 3
 * Explanation: Ribbon pieces will be {3,3,7} or {5,5,3}.
 */
public class MaximumRibbonCut {

    /**
     * Approach 1: Brute Force Approach
     *
     * for each length 'l'
     *   create a new set which includes one quantity of length 'l' if it does not exceed 'n', and
     *      recursively call to process all lengths
     *   create a new set without length 'l', and recursively call to process the remaining lengths
     * return the number of pieces from the above two sets with a higher number of pieces
     *
     * Time Complexity: O(2^(n+T)) n: length of ribbonLengths array, t: total
     * Space Complexity: O(n+T)
     */
    private int countRibbonPiecesApproach1(int[] ribbonLengths, int total){
        int res =  helper(ribbonLengths, total, 0);
        if(res == Integer.MIN_VALUE)
            return -1;
        return res;
    }

    private int helper(int[] ribbonLengths, int total, int index){
        //base case:
        if(total == 0)
            return 0;
        if(index == ribbonLengths.length)
            return Integer.MIN_VALUE;
        //recursion to break it into subproblems
        int ways1 = helper(ribbonLengths, total, index+1);
        int ways2 = Integer.MIN_VALUE;
        if(total>= ribbonLengths[index]){
            int ways = helper(ribbonLengths, total-ribbonLengths[index], index);
            if(ways!= Integer.MIN_VALUE)
                ways2 = 1 + ways;
        }
        // return
        return Math.max(ways1, ways2);
    }


    /**
     * Approach 2: Memoizing Approach 1
     */
    private int countRibbonPiecesApproach2(int[] ribbonLengths, int total){
        Integer[][] cache = new Integer[ribbonLengths.length][total+1];
        int res =  helper(ribbonLengths, total, 0, cache);
        if(res == Integer.MIN_VALUE)
            return -1;
        return res;
    }

    private int helper(int[] ribbonLengths, int total, int index, Integer [][] cache){
        //base case:
        if(total == 0)
            return 0;
        if(index == ribbonLengths.length)
            return Integer.MIN_VALUE;
        if(cache[index][total] != null)
            return  cache[index][total];
        //recursion to break it into subproblems
        int ways1 = helper(ribbonLengths, total, index+1,cache);
        int ways2 = Integer.MIN_VALUE;
        if(total>= ribbonLengths[index]){
            int ways = helper(ribbonLengths, total-ribbonLengths[index], index, cache);
            if(ways!= Integer.MIN_VALUE)
                ways2 = 1 + ways;
        }
        // return
        cache[index][total] =  Math.max(ways1, ways2);
        return cache[index][total];
    }

    /**
     * Approach 3: Tabulation Bottom up DP
     * table[i][t] represents the maximum number of ribbon cuts for total t
     * using lengths in ribbonLengths from index i to ribbonLenghts.length-1
     * Time Complexity: O(n*T)
     * Space Complexity: O(n*T)
     */
    private int countRibbonPiecesApproach3(int[] ribbonLengths, int total){
        int[][] table = new int[ribbonLengths.length][total+1];

        for(int i = 0; i<ribbonLengths.length; i++)
            table[i][0] = 0;

        for(int i =ribbonLengths.length-1; i>= 0; i--){
            for(int t = 1; t<= total; t++){
                int ways1 = Integer.MIN_VALUE;
                if(i < ribbonLengths.length-1)
                    ways1 = table[i+1][t];
                int ways2 = Integer.MIN_VALUE;
                if(t>= ribbonLengths[i]){
                    int ways = table[i][t-ribbonLengths[i]];
                    if(ways!=Integer.MIN_VALUE)
                        ways2 = 1 + ways;
                }
                table[i][t] = Math.max(ways1, ways2);
            }
        }

        return table[0][total] == Integer.MIN_VALUE ? -1 : table[0][total];
    }





    public static void main(String[] args) {
        MaximumRibbonCut cr = new MaximumRibbonCut();
        System.out.println("Brute Force");
        int[] ribbonLengths = {2,3,5};
        System.out.println(cr.countRibbonPiecesApproach1(ribbonLengths, 5));
        ribbonLengths = new int[]{2,3};
        System.out.println(cr.countRibbonPiecesApproach1(ribbonLengths, 7));
        ribbonLengths = new int[]{3,5,7};
        System.out.println(cr.countRibbonPiecesApproach1(ribbonLengths, 13));
        ribbonLengths = new int[]{3,5};
        System.out.println(cr.countRibbonPiecesApproach1(ribbonLengths, 7));
        System.out.println("--------------");
        System.out.println("Memoization");
        ribbonLengths = new int[]{2,3,5};
        System.out.println(cr.countRibbonPiecesApproach2(ribbonLengths, 5));
        ribbonLengths = new int[]{2,3};
        System.out.println(cr.countRibbonPiecesApproach2(ribbonLengths, 7));
        ribbonLengths = new int[]{3,5,7};
        System.out.println(cr.countRibbonPiecesApproach2(ribbonLengths, 13));
        ribbonLengths = new int[]{3,5};
        System.out.println(cr.countRibbonPiecesApproach2(ribbonLengths, 7));
        System.out.println("--------------");
        System.out.println("Tabulation");
        ribbonLengths = new int[]{2,3,5};
        System.out.println(cr.countRibbonPiecesApproach3(ribbonLengths, 5));
        ribbonLengths = new int[]{2,3};
        System.out.println(cr.countRibbonPiecesApproach3(ribbonLengths, 7));
        ribbonLengths = new int[]{3,5,7};
        System.out.println(cr.countRibbonPiecesApproach3(ribbonLengths, 13));
        ribbonLengths = new int[]{3,5};
        System.out.println(cr.countRibbonPiecesApproach3(ribbonLengths, 7));
    }
}
