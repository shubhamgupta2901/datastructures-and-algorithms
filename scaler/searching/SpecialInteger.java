package scaler.searching;

/**
 * Given an array of integers A and an integer B, find and return the maximum value K such that there is no subarray
 * in A of size K with sum of elements greater than B.
 *
 * Problem Constraints
 * 1 <= |A| <= 100000
 * 1 <= A[i] <= 10^9
 * 1 <= B <= 10^9
 *
 * Input Format
 * The first argument given is the integer array A.
 * The second argument given is integer B.
 *
 * Output Format
 * Return the maximum value of K (sub array length).
 *
 *
 *
 * Example:
 * Input: A = [1, 2, 3, 4, 5], B = 10
 * Output: 2
 *
 * Input: A = [5, 17, 100, 11],  B = 130
 * Output: 3
 */
public class SpecialInteger {
    /**
     * Approach 1 : Linear search maximum subarray size
     */
    public int solveApproach1(int[] A, int B) {
        if(A==null || A.length == 0)
            return 0;
        int len = A.length;
        int K = len;
        while(K>0){
            if(isValidK(A, B, K))
                return K;
            K--;
        }
        return K;
    }

    /**
     * Approach 2 : Binary search maximum subarray size
     */
    public int solveApproach2(int[] A, int B) {
        if(A==null || A.length == 0)
            return 0;
        int len = A.length;

        int start = 0, end = len;
        int candidate = 0;
        while(start<=end){
            int midK = (start+end)/2;
            if(isValidK(A, B, midK)){
                candidate = midK;
                start = midK+1;
            }else end = midK-1;
        }
        return candidate;
    }

    /**
     * Using sliding window technique to calculate the sum of each sub array of size K
     * Rather than calculating the sum of each sub array, we can accumulate the sum of elements till index K(exclusive)
     * then subtract one element and add next element to get next subarray sum
     *
     */
    private boolean isValidK(int[] arr,int target,int subArrSize){
        //return true if each subarray of size subArrSize has sum <= target
        //return false otherwise
        long sum = 0;
        for(int i = 0; i<subArrSize; i++){
            sum+=arr[i];
        }
        if(sum>target)
            return false;
        for(int i = subArrSize; i<arr.length; i++){
            sum= sum - arr[i-subArrSize] + arr[i];
            if(sum>target)
                return false;
        }
        return true;
    }
}
