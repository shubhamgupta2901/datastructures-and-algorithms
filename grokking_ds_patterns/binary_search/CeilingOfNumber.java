package grokking_ds_patterns.binary_search;

public class CeilingOfNumber {

    /**
     * Approach 1: Linear scan
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int searchCeilingOfANumberApproach1(int[] arr, int key) {
        for(int i = arr.length -1 ; i>=0; i--){
            if(arr[i]< key)
                return i == arr.length-1 ? -1 : i+1;
        }
        return 0;
    }

    /**
     * Approach 1: Linear scan
     * Time complexity: O(logn)
     * Space complexity: O(1)
     */
    public int searchCeilingOfANumberApproach2(int[] arr, int key){
        int length = arr.length;
        int start = 0, end = length-1, mid;
        int ceil = -1;
        while (start<=end){
            mid = start + (end-start)/2;
            if(arr[mid]>=key){
                ceil = mid;
                end = mid-1;
            }else start = mid+1;
        }
        return ceil;
    }
}
