package problem_solving;

/**
 * Given an integer array A of size N. You have to delete one element such that the GCD(Greatest common divisor) of the remaining array is maximum.
 * Find the maximum value of GCD.
 *
 * Problem Constraints
 * 2 <= N <= 105
 * 1 <= A[i] <= 109
 * Input Format: First argument is an integer array A.
 * Output Format: Return an integer denoting the maximum value of GCD.
 *
 * Example:
 *  A = [12, 15, 18]
 *  Output = 6
 *  If you delete 12, gcd will be 3.
 *  If you delete 15, gcd will be 6.
 *  If you delete 18, gcd will 3.
 *  Maximum value of gcd is 6.
 *
 * Example
 *  A = [5, 15, 30]
 *  Output: 15
 *  If you delete 5, gcd will be 15.
 *  If you delete 15, gcd will be 5.
 *  If you delete 30, gcd will be 5.
 */
public class MaxGcdInArray   {

    /**
     * Time Complexity: O(nlogn)
     * Space Complexity: O(n)
     * Observations:
     * 1. GCD is commutative: GCD(a,b) =  GCD(b,a)
     * 2. GCD is associative: GCD(a, b,c) = GCD(GCD(a,b), c) = GCD(GCD(b,c), a) = GCD(GCD(a,c), b)
     * Approach:
     * Use two arrays, prefix[i] will keep GCD of elements from index 0..i
     * suffix[i] will keep GCD of elements from i..len_of_array
     * for A[i] the gcd of all elements in A except A[i] would be GCD(prefix[i-1], suffix[i+1])
     */
    public int solve(int[] A) {
        int length = A.length;
        int[] prefixGcd = new int[length];
        int[] suffixGcd = new int[length];

        prefixGcd[0] = A[0];
        for(int i = 1; i<length; i++){
            prefixGcd[i] = gcd(prefixGcd[i-1], A[i]);
        }

        suffixGcd[length-1] = A[length-1];
        for(int i = length-2; i>=0; i--){
            suffixGcd[i] = gcd(suffixGcd[i+1], A[i]);
        }

        int maxGcd = 1;
        for(int i = 0; i<length; i++){
            if(i == 0){
                maxGcd = suffixGcd[1];
            }else if(i == length-1){
                maxGcd = Math.max(maxGcd, prefixGcd[length-2]);
            }else {
                int gcd = gcd(prefixGcd[i-1], suffixGcd[i+1]);
                maxGcd = Math.max(gcd, maxGcd);
            }
        }
        return maxGcd;

    }

    private int gcd(int A, int B){
        if(B == 0)
            return A;
        return gcd(B, A%B);
    }
}
