package problem_solving.sorting;

import java.util.Arrays;

/**
 * Maximum Unsorted Subarray
 * Problem Description
 *
 * Given an array A of non-negative integers of size N. Find the minimum sub-array Al, Al+1 ,..., Ar such that
 * if we sort(in ascending order) that sub-array, then the whole array should get sorted.
 * If A is already sorted, output -1.
 *
 * Problem Constraints:
 * 1 <= N <= 1000000
 * 1 <= A[i] <= 1000000
 * Input Format:
 * First and only argument is an array of non-negative integers of size N.
 * Output Format:
 * Return an array of length 2 where First element denotes the starting index(0-based) and second element denotes the
 * ending index(0-based) of the sub-array. If the array is already sorted, return an array containing only one element i.e. -1.
 *
 * Example
 * Input A = [1, 3, 2, 4, 5]
 * Output = [1, 2] If we sort the sub-array A[1]- A[2], then the whole array A gets sorted.
 *
 * Input = [1, 2, 3, 4, 5]
 * Output = [-1] A is already sorted.
 *
 */
public class MaximumUnsortedSubarray {

    public int[] subUnsort(int[] A) {
        return helper(A,0, A.length-1);
    }

    private int[] helper(int[]A, int left, int right){
        if(left >= right){
            return new int[]{-1};
        }

        int minIndex=left, maxIndex=left;
        for(int i = left+1; i<=right; i++){
            if(A[i]<A[minIndex])
                minIndex = i;
            if(A[i]>=A[maxIndex])
                maxIndex = i;
        }

        if(minIndex!=left && maxIndex!=right){
            int[] output = new int[2];
            output[0] = left;
            output[1] = right;
            return output;
        }

        if(minIndex==left && maxIndex!=right){
            return helper(A, left+1,right);
        }else if(minIndex!=left && maxIndex==right){
            return helper(A, left, right-1);
        }else {
            return helper(A, left+1, right-1);
        }
    }

    public int[] iteratriveSubUnsort(int[] A) {
        if(A == null || A.length<=1){
            return new int[]{-1};
        }
        int left = 0, right = A.length-1;
        while(left<right){
            int minIndex=left, maxIndex=left;
            for(int i = left+1; i<=right; i++){
                if(A[i]<A[minIndex])
                    minIndex = i;
                if(A[i]>=A[maxIndex])
                    maxIndex = i;
            }
            if(minIndex!=left && maxIndex!=right){
                int[] output = new int[2];
                output[0] = left;
                output[1] = right;
                return output;
            }

            if(minIndex==left && maxIndex!=right){
                left++;
            }else if(minIndex!=left && maxIndex==right){
                right--;
            }else {
                left++;
                right--;
            }

        }

        return new int[]{-1};
    }

    public int[] approach3(int[] A) {
        if(A == null || A.length<=1){
            return new int[]{-1};
        }
        int len = A.length;
        int[] sortedA = new int[len];
        for(int i = 0; i<len; i++ )
            sortedA[i] = A[i];
        Arrays.sort(sortedA);

        int left = 0, right = len-1;
        while(left<right){
            if(A[left]!=sortedA[left] && A[right]!= sortedA[right]){
                int[] output = new int[2];
                output[0] = left;
                output[1] = right;
                return output;
            }
            if(A[left]==sortedA[left])
                left++;
            if(A[right]==sortedA[right])
                right--;
        }
        return new int[]{-1};
    }


    public static void main(String[] args) {
        MaximumUnsortedSubarray driver = new MaximumUnsortedSubarray();
        int[] input1 = new int[]{1,2,3,5,4};
        int[] input2 = new int[]{1, 1, 10, 10, 15, 10, 15, 10, 10, 15, 10, 15};
        int[] output = driver.subUnsort(input2);
        for(Integer number: output)
         System.out.print(number+" ");
    }
}
