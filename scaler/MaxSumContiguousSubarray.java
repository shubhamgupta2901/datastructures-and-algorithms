package scaler;

/**
 *  Find the contiguous subarray within an array, A of length N which has the largest sum.
 *
 * Problem Constraints
 * 1 <= N <= 1e6
 * -1000 <= A[i] <= 1000
 *
 * Input Format
 * The first and the only argument contains an integer array, A.
 *
 * Output Format
 * Return an integer representing the maximum possible sum of the contiguous subarray.
 *
 * Example Input
 * Input :
 *  A = [1, 2, 3, 4, -10]
 * Output: 10
 * Input:
 * A = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * Output:
 *  6
 */
public class MaxSumContiguousSubarray {
    /**
     * Approach: Brute Force. Consider all possible subarrays, and find the one with max sum
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public int approach1(final int[] A) {
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i<A.length; i++){
            int sum = 0;
            for(int j = i; j<A.length; j++){
                sum+=A[j];
                if(sum>maxSum)
                    maxSum = sum;
            }
        }
        return maxSum;
    }

    /**
     * Approach:
     * Note that no prefix of the solution will ever have a negative sum.
     * Let us say Ai, Ai+1 … Aj is our optimal solution.
     * Let us say Ai … Ak prefix had a negative sum. such that
     * Sum ( Ai Ai+1 … Aj ) = Sum (Ai … Ak) + Sum(Ak+1 … Aj)
     * Sum ( Ai Ai+1 … Aj) - Sum(Ak+1 … Aj) = Sum(Ai … Ak)
     * Now, since Sum(Ai … Ak) < 0,
     * Sum (Ai Ai+1 … Aj) - Sum (Ak+1 … Aj) < 0
     * which means Sum(Ak+1 … Aj ) > Sum (Ai Ai+1 … Aj)
     * This contradicts the fact that Ai, Ai+1 … Aj is our optimal solution.
     * Instead, Ak+1, Ak+2 … Aj will be our optimal solution.
     *
     * Based on above observation, whenever the sum becomes negative, we can start all over again
     * discarding the accumulated sum.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int approach2(final int[] A) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        for(int i = 0; i<A.length; i++){
            currSum+= A[i];
            if(currSum> maxSum)
                maxSum = currSum;
            if(currSum<0)
                currSum = 0;
        }
        return maxSum;
    }
}
