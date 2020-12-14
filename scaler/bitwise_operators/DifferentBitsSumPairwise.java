package scaler.bitwise_operators;

/**
 * We define f(X, Y) as number of different corresponding bits in binary representation of X and Y.
 * For example, f(2, 7) = 2, since binary representation of 2 and 7 are 010 and 111, respectively.
 * The first and the third bit differ, so f(2, 7) = 2.
 *
 * You are given an array of N positive integers, A1, A2 ,..., AN. Find sum of f(Ai, Aj) for all pairs (i, j)
 * such that 1 ≤ i, j ≤ N. Return the answer modulo 109+7.
 *
 * Problem Constraints
 * 1 <= N <= 105
 * 1 <= A[i] <= 231 - 1
 *
 * Input Format: First and only argument of input contains a single integer array A.
 * Output Format: Return a single integer denoting the sum.
 *
 * Input 1: A = [1, 3, 5]
 * Output: 8
 * Explanation:
 * f(1, 1) + f(1, 3) + f(1, 5) + f(3, 1) + f(3, 3) + f(3, 5) + f(5, 1) + f(5, 3) + f(5, 5)
 *  *  = 0 + 1 + 1 + 1 + 0 + 2 + 1 + 2 + 0 = 8
 *
 * Input 2: A = [2, 3]
 * Output: 2
 * Explanation:
 *  f(2, 2) + f(2, 3) + f(3, 2) + f(3, 3) = 0 + 1 + 1 + 0 = 2
 */
public class DifferentBitsSumPairwise {
    private static final int MODULO = 1000000007;

    /**
     * Approach 1:
     * For every possible pair in array, find the difference in bits for the numbers and add it to result
     * Time Complexity: O(32*n^2) == O(n^2)
     * [32 because countBits can run at max 32 times, which is the number of bits in an integer ]
     * @param A
     * @return
     */
    public int approach1(int[] A) {
        if(A == null || A.length<=1)
            return 0;
        int sum = 0;
        for(int i = 0; i<A.length; i++){
            for(int j = 0; j<A.length; j++){
                sum += countBits(A[i], A[j]);
                sum=sum%MODULO;
            }
        }
        return sum%MODULO;
    }

    public int countBits(int a, int b){
        if(a == b)
            return 0;
        int result = 0;
        while(a>0 || b>0){
            if((a&1)!=(b&1))
                result++;
            a = a>>1;
            b = b>>1;
        }
        return result;

    }

    /**
     * Approach 2: Optimising the loop for calculating the sum
     * Time Complexity: O(32*n^2) == O(n^2)
     */
    public int approach2(int[] A) {
        if(A == null || A.length<=1)
            return 0;
        int sum = 0;
        for(int i = 0; i<A.length; i++){
            for(int j = i+1; j<A.length; j++){
                sum += 2* countBits(A[i], A[j]);
                sum=sum%MODULO;
            }
        }
        return sum%MODULO;
    }
}
