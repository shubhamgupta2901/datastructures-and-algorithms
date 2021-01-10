package problem_solving.sorting;

/**
 * Inversion count in an array
 * Given an array of integers A. If i < j and A[i] > A[j] then the pair (i, j) is called an inversion of A.
 * Find the total number of inversions of A modulo (109 + 7).
 *
 * Problem Constraints
 * 1 <= length of the array <= 100000
 * 1 <= A[i] <= 10^9
 *
 * Input Format: The only argument given is the integer array A.
 * Output Format: Return the number of inversions of A modulo (109 + 7).
 *
 * Input 1: A = [3, 2, 1]
 * Output 1: 3
 *
 * Input 2: A = [1, 2, 3]
 * Output 2: 0
 *
 */
public class InversionCount {

    static class Approach1{
        public int solve(int[] A) {
            if(A == null || A.length<=1)
                return 0;
            int MOD = 1000000007;
            int len = A.length;
            int count = 0;
            for(int i = 0; i<len; i++){
                for(int j =i+1; j<len; j++){
                    if(A[i]>A[j]){
                        count++;
                        count = count%MOD;
                    }

                }
            }
            return count%MOD;
        }
    }

    static class Approach2{
        int MOD = 1000000007;
        int count = 0;
        public int solve(int[] A) {
            if(A==null || A.length<=1)
                return count;
            sort(A, 0, A.length-1);
            return count%MOD;
        }

        private void sort(int[] arr, int start, int end){
            if(start == end)
                return;
            int mid = (start+end)/2;
            sort(arr, start, mid);
            sort(arr, mid+1, end);
            mergeAndCount(arr, start, mid, end);
        }

        private void mergeAndCount(int[] arr, int start, int mid, int end){
            int[] arr1 = new int[mid-start+1];
            int[] arr2 = new int[end-mid];

            for(int i = start; i<=mid; i++){
                arr1[i-start] = arr[i];
            }

            for(int i = mid+1; i<=end; i++){
                arr2[i-mid-1] = arr[i];
            }

            int i = 0, j = 0, k = start;
            while(i<arr1.length && j<arr2.length){
                if(arr2[j]<arr1[i]){
                    count+=(mid-(i+start)+1)%MOD;
                    count = count%MOD;
                    arr[k] = arr2[j];
                    j++;
                }else {
                    arr[k] = arr1[i];
                    i++;
                }
                k++;
            }
            while(i<arr1.length){
                arr[k] = arr1[i];
                i++;
                k++;
            }
            while(j<arr2.length){
                arr[k] = arr2[j];
                j++;
                k++;
            }
        }
    }

    public static void main(String[] args) {
        InversionCount.Approach2 driver = new InversionCount.Approach2();
        int[] input = new int[]{ 28, 18, 44, 49, 41, 14};
        int output = driver.solve(input);
        System.out.println(output);
    }

}
