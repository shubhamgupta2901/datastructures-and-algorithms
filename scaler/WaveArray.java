package scaler;

import java.util.Arrays;

/**
 * Given an array of integers A, sort the array into a wave like array and return it,
 * In other words, arrange the elements into a sequence such that a1 >= a2 <= a3 >= a4 <= a5.....
 * NOTE : If there are multiple answers possible, return the one that's lexicographically smallest.
 * Problem Constraints
 * 1 <= len(A) <= 106
 * 1 <= A[i] <= 106
 *
 * Input Format
 * First argument is an integer array A.
 * Output Format
 * Return an array arranged in the sequence as described.
 *
 * Example Input
 * Input 1:
 *
 * A = [1, 2, 3, 4]
 * Input 2:
 *
 * A = [1, 2]
 *
 *
 * Example Output
 * Output 1:
 *
 * [2, 1, 4, 3]
 * Output 2:
 *
 * [2, 1]
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 * One possible answer : [2, 1, 4, 3]
 * Another possible answer : [4, 1, 3, 2]
 * First answer is lexicographically smallest. So, return [2, 1, 4, 3].
 * Explanation 1:
 *
 * Only possible answer is [2, 1].
 */
public class WaveArray {
    /**
     * Approach 1: Sort the array
     * then put smallest number at oddIndex 1, increment oddIndex by 2
     * then put second smallest number at evenIndex : 0, increment even index by 2
     * repeat above alternatively
     * Time Complexity: O(nlogn)
     * Space Complexity: O(n)
     */
    public int[] approach1(int[] A) {
        if(A == null || A.length <=1)
            return A;
        Arrays.sort(A);
        int[] output = new int[A.length];
        int oddIndex = 1;
        int evenIndex =0;
        boolean shouldPlaceAtOdd = true;
        for(int i = 0; i<A.length; i++){
            if(shouldPlaceAtOdd){
                output[oddIndex] = A[i];
                if(oddIndex+2 >= A.length)
                    oddIndex++;
                else oddIndex+=2;
            }else{
                output[evenIndex] = A[i];
                evenIndex+=2;
            }
            shouldPlaceAtOdd = !shouldPlaceAtOdd;
        }

        return output;
    }


    /**
     * Sort the array, and then swap adjacent elements in pairs, the array is now in the wave form
     * Time Complexity: O(nlogn)
     * Space Complexity: O(1)
     */
    public int[] approach2(int[] A) {
        if(A == null || A.length <=1)
            return A;
        Arrays.sort(A);
        for(int i = 0; i<A.length-1; i=i+2){
            swap(A, i, i+1);
        }
        return A;
    }

    private void swap(int[] arr, int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
