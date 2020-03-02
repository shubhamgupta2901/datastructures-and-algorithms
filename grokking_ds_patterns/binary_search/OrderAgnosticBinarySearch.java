package grokking_ds_patterns.binary_search;

/**
 * Given a sorted array of numbers, find if a given number ‘key’ is present in the array.
 * Though we know that the array is sorted, we don’t know if it’s sorted in ascending or descending order.
 * You should assume that the array can have duplicates.
 */
public class OrderAgnosticBinarySearch {
    /**
     * Idea is to find the order of sorting in array (ascending or descending)
     * I can compare the first and second numbers in the array, but since the array can have duplicates,
     * it would not conclusively tell us about the order of array.
     * I can keep on comparing the numbers till I reach the conclusion but that would mean I may have to do n comparisions,
     * (in case when the array has only one number and its duplicate)
     * So I compare numbers at first and last indices. This would conclusively tell us aobut the ordering in the array
     * in constant time. Then in O(logn) time, I can perform binary search.
     *
     * Time Complexity: O(logn)
     * Space Conplexity: O(1)
     */
    public static int search(int[] arr, int key) {
        int length = arr.length;
        if(length == 0)
            return -1;
        //only duplicates
        if(arr[0] == arr[length-1])
            return arr[0] == key ? 0 : -1;
        //array in ascending order
        boolean isAscending = arr[0] < arr[length-1];
        int start = 0, end = length-1, mid;
        while (start<=end){
            mid = start + (end-start)/2;
            if(arr[mid] == key)
                return mid;
            else if (arr[mid]<key){
                if(isAscending)
                    start = mid + 1 ;
                else end = mid - 1;
            }
            else{
                if(isAscending)
                    end = mid - 1;
                else start = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(OrderAgnosticBinarySearch.search(new int[] { 4, 6, 10 }, 10));
        System.out.println(OrderAgnosticBinarySearch.search(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 5));
        System.out.println(OrderAgnosticBinarySearch.search(new int[] { 10, 6, 4 }, 10));
        System.out.println(OrderAgnosticBinarySearch.search(new int[] { 10, 6, 4 }, 4));
    }
}
