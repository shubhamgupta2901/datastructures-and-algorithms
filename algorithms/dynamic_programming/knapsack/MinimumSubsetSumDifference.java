package algorithms.dynamic_programming.knapsack;

/**
 * Given a set of positive numbers, partition the set into two subsets with a minimum difference between their subset sums.
 *
 * Example:
 * Input: {1, 2, 3, 9}
 * Output: 3
 * Explanation: We can partition the given set into two subsets where minimum absolute difference
 * between the sum of numbers is '3'. Following are the two subsets: {1, 2, 3} & {9}.
 *
 * Example:
 * Input: {1, 2, 7, 1, 5}
 * Output: 0
 * Explanation: We can partition the given set into two subsets where minimum absolute difference
 * between the sum of number is '0'. Following are the two subsets: {1, 2, 5} & {7, 1}.
 *
 * Example:
 * Input: {1, 3, 100, 4}
 * Output: 92
 * Explanation: We can partition the given set into two subsets where minimum absolute difference
 * between the sum of numbers is '92'. Here are the two subsets: {1, 3, 4} & {100}.
 */
public class MinimumSubsetSumDifference {

    /**
     * Approach 1: Brute force
     * The idea is to create all the possible subsets and finding out its sum.
     * Sum of array - sum of subset is the remainder sum left.
     * return the subset which has minimum absolute difference between its sum and remainder sum.
     * Time complexity: O(2^n)
     * Space Complexity: O(n) for recursive stack, because at any point the stack will have maximum n elements.
     */
    public int findDifferenceApproach1(int []set){
        int sum = 0;
        for(int i = 0; i<set.length; i++)
            sum+=set[i];

        return  findDifferenceHelper(set,0,0,sum);
    }

    private int findDifferenceHelper(int [] set, int index, int subsetSum, int remainder){
        if(index == set.length)
            return Math.abs(subsetSum-remainder);
        return Math.min(findDifferenceHelper(set,index+1,subsetSum,remainder),
                findDifferenceHelper(set, index+1,subsetSum+set[index],remainder-set[index]));
    }


    /**
     * Approach 2: Same as approach 1 but a modification in thinking process.
     * I don't need to maintain the remainder of sum of array
     * Because remainder will always be total sum of array - total sum of subset.
     * And the difference between the sum of current subset and the sum of rest of elements will be
     * difference of sum between subsets = |sum of current subset - sum of remainder of array|
     * Or |sum of current subset - (total sum of array - sum of current subset)|
     * Time complexity: O(2^n)
     * Space Complexity: O(n)
     */
    public int findDifferenceApproach2(int[] set) {
        int sum = 0;
        for(int i = 0; i<set.length; i++)
            sum+=set[i];
        return this.findDifferenceHelper2(set,sum, 0,0);
    }

    private int findDifferenceHelper2(int [] set,int totalSum, int index, int subsetSum){
        if(index == set.length)
            return Math.abs(totalSum-2*subsetSum);
        return Math.min(findDifferenceHelper2(set,totalSum,index+1,subsetSum),
                findDifferenceHelper2(set, totalSum,index+1,subsetSum+set[index]));
    }


    /**
     * Approach 3: Memoization (Top Down)
     * If at all there are any overlapping subproblems, we this technique could be useful
     * Time complexity: ? Probably O(2^n) because I couldn't find overlapping subproblems here
     * Space Complexity: O(n*S) where S is the sum of array.
     */
    public int findDifferenceApproach3(int[] set) {
        int sum = 0;
        for(int i = 0; i<set.length; i++)
            sum+=set[i];
        Integer [][] cache = new Integer[set.length][sum+1];
        return this.findDifferenceHelper3(set,sum, 0,0,cache);
    }

    private int findDifferenceHelper3(int [] set,int totalSum, int index, int subsetSum, Integer[][]cache){
        if(index == set.length)
            return Math.abs(totalSum-2*subsetSum);
        if(cache[index][subsetSum] != null)
            return cache[index][subsetSum];
        int difference =  Math.min(findDifferenceHelper3(set,totalSum,index+1,subsetSum,cache),
                findDifferenceHelper3(set, totalSum,index+1,subsetSum+set[index],cache));
        cache[index][subsetSum] = difference;
        return difference;
    }

    /**
     * Approach 4: Bottom up tabulation
     * Idea is:
     * 1. table[i][s] represents the minimum difference of subsets starting from index i with current subset sum s
     * 2. we need to find table[0][0] i.e. starting from index 0 and with current subset sum 0,
     *   find the minimum difference in two subsets of set
     * 3.Bottom case:  The table[set.length-1][s] row is filled by calculating the both the possiblity,
     *  adding the last number of set and checking difference, and not adding the last number and checking the difference.
     *  fill it with minimum of the two differences.
     * 4. Fill up the table in bottom up fashion.
     * Time complexity: O(n*S)
     * Space Complexity: O(n*S)
     */
    public int findDifferenceApproach4(int[] set){
        int sum = 0;
        for(int i = 0; i<set.length; i++)
            sum+=set[i];
        int[][] table = new int[set.length][sum+1];

        //Filling up the bottom cases.
        for(int s = 0; s<=sum; s++)
            table[set.length-1][s] = Math.min(Math.abs(sum-2*s),Math.abs(sum-2*(s+set[set.length-1])));

        for(int i = set.length-2; i>=0; i--){
            for (int s = 0; s<=sum; s++){
                if(s+set[i]>sum)
                    table[i][s] =table[i+1][s];
                else table[i][s] = Math.min(table[i+1][s],table[i+1][s+set[i]]);
            }
        }

        return table[0][0];
    }




    public static void main(String[] args) {
        MinimumSubsetSumDifference ps = new MinimumSubsetSumDifference();
        int[] num = {1, 2, 3, 9};
        System.out.println(ps.findDifferenceApproach4(num));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(ps.findDifferenceApproach4(num));
        num = new int[]{1, 3, 100, 4};
        System.out.println(ps.findDifferenceApproach4(num));
    }

}
