package problem_solving.sorting;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Sum the Difference
 * Given an integer array A of size N.
 * You have to find all possible non-empty subsequence of the array of numbers and then, for each subsequence,
 * find the difference between the largest and smallest numbers in that subsequence Then add up all the differences to get the number.
 * As the number may be large, output the number modulo 1e9 + 7 (1000000007).
 * NOTE: Subsequence can be non-contiguous.
 *
 * Problem Constraints
 * 1 <= N <= 10000
 * 1<= A[i] <=1000
 *
 * Input Format: First argument is an integer array A.
 * Output Format: Return an integer denoting the output.
 *
 * Example
 * Input : A = [1, 2]
 * Output : 1
 * All possible non-empty subsets are:
 * [1]    largest-smallest = 1 - 1 = 0
 * [2]    largest-smallest = 2 - 2 = 0
 * [1 2]  largest-smallest = 2 - 1 = 1
 * Sum of the differences = 0 + 0 + 1 = 1
 * So, the resultant number is 1
 *
 * Input : A = [1]
 * Output : 0
 *
 */
public class SumTheDifference {

    /**
     * Brute Force Approach: Generate all the possible subsets of array,
     * If we sort the array before generating the subsets we do not need to keep track of min and max elements in subset
     * keep adding difference of max and min number of subset to answer.
     * Time Complexity: O(2^n)
     * Space Compelxity: (n) for recursive stack
     */
    static class Approach1{
        int ans = 0;
        int MOD = 1000000007;
        public int solve(int[] A) {
            if(A == null || A.length<=1)
                return 0;
            Arrays.sort(A);
            generateSubsets(A, new ArrayList<>(), 0);
            return ans%MOD;
        }

        private void generateSubsets(int[] arr,ArrayList<Integer> subset, int index){
            if(index == arr.length){
                if(subset.size() == 0)
                    return;
                ans += (subset.get(subset.size()-1) - subset.get(0))%MOD;
                ans = ans%MOD;
                return;
            }
            subset.add(arr[index]);
            generateSubsets(arr,subset, index+1);
            subset.remove(subset.size()-1);
            generateSubsets(arr,subset, index+1);
        }
    }

    /**
     * Observations:
     * 1. We need to find all the subsets of array. Prior to generating subsets, if we sort the array,
     * the resulting subsets would still be same, only their internal contents would be rearranged in sorted manner.
     * 2. This means for an array [3,4,5,6], the value of (max-min) for all subsets starting from index i to index j
     * would be same. [3,6], [3,4,6], [3,5,6], [3,4,5,6].
     * 3. The number of subsets where a[i] and a[j] are terminal points: Math.pow(2, j-i-1) [keep 3 and 6 and count all subsets from i+1 to j-1 index]
     *
     * Approach :
     * 1. We can sort the array
     * 2. Between any two indices i and j we can find the difference between the maximum and minimum, and multiply it with number of subsets that
     *  have a[i] and a[j] as terminals.
     *  3. Collate the differences and return the answer.
     *
     *  Time Complexity: O(n^2)
     *  Space Complexity: O(1)
     */
    static class Approach2{
        public int solve(int[] A) {
            int ans = 0;
            int MOD = 1000000007;
            if(A == null || A.length<=1)
                return 0;
            Arrays.sort(A);
            for(int i = 0; i<A.length; i++){
                for(int j = i+1; j<A.length; j++){
                    int diff = ((A[j]-A[i])%MOD * (int)(Math.pow(2,j-i-1)%MOD))%MOD;
                    ans+=diff;
                    ans=ans%MOD;
                }
            }
            return ans%MOD;
        }
    }

    /**
     * Observations:
     * 1. (a-b) + (c-d) + (e-f) = (a+c+e) - (b+d+f)
     * 2. So we can calculate the sum of all the maximums from all subsets, and sum of all minimums from all subsets, and subtract them
     * Approach:
     * 1. Sort the array
     * 2. For each element at index i, the number of subsets where a[i] is maximum: Math.pow(2,i)
     *      And for each of these subsets a[i]'s contribution in sum of maximums of all subsets would be: a[i]*Math.pow(2,i)
     * 3. Similarly, For each element at index i, the number of subsets where a[i] is minimum: Math.pow(2,len-1-i)
     *     And for each of these subsets a[i]'s contribution in sum of minimums of all subsets would be: a[i]*Math.pow(2,len-1-i)
     * 4. Keep collating each element's contribution in sum of maximums, and sum of minimums
     * 5. Finally subtract them to get answer.
     * Time Complexity: O(nlogn)
     * Space Complexity: O(1)
     *
     */
    static class Approach3{
        int MOD = 1000000007;
        public int solve(int[] A) {
            if(A == null || A.length<=1)
                return 0;
            Arrays.sort(A);
            int ans = 0;
            long maxSum = 0;
            long minSum =0;
            for(int i = 0; i<A.length; i++){
                maxSum += (Math.pow(2,i) * A[i])%MOD;
                maxSum= maxSum%MOD;
                minSum += (Math.pow(2, A.length-1-i) * A[i])%MOD;
                minSum = minSum%MOD;
            }
            return (int)(maxSum%MOD-minSum%MOD)%MOD;
        }
    }

}
