package problem_solving.searching;

/**
 *  Given a sorted array of integers A of size N and an integer B.
 *  array A is rotated at some pivot unknown to you beforehand.
 *  (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2 ).
 *  You are given a target value B to search. If found in the array, return its index, otherwise return -1.
 *  You may assume no duplicate exists in the array.
 *  NOTE: Users are expected to solve this in O(log(N)) time.
 *
 * Problem Constraints
 * 1 <= N <= 1000000
 * 1 <= A[i] <= 10^9
 * all elements in A are disitinct.
 *
 * Input Format
 * The first argument given is the integer array A.
 * The second argument given is the integer B.
 *
 * Output Format
 * Return index of B in array A, otherwise return -1
 *
 *
 *
 * Example
 * Input: A = [4, 5, 6, 7, 0, 1, 2, 3], B = 4
 * Output: 0
 *
 * Input: A = [1], B = 1
 * Output: 0
 *
 */
public class RotatedSortedArraySearch {
        public int search(int[] A, int B) {
            if(A == null || A.length == 0)
                return -1;
            int length = A.length;
            //pivot is an element whose both neighbours are smaller than itself 0<=pivot<=len-1
            int pivot = findPivot(A, 0, length-1);
            int leftSearch = binarySearch(A, B, 0, pivot);
            if(leftSearch  != -1)
                return leftSearch;
            return binarySearch(A, B, pivot+1,length-1);
        }

        private int binarySearch(int []arr, int B, int start, int end){
            if(start>end)
                return -1;
            int mid = (start + end)/2;
            if(arr[mid] == B)
                return mid;
            if(arr[mid]<B)
                return binarySearch(arr, B, mid+1, end);
            else return binarySearch(arr, B, start, mid-1);
        }

        private int findPivot(int []arr, int start, int end){
            //TODO handle cases when pivot is at 0 or length-1
            if(start== end)
                return start;
            int mid = (start+end)/2;
            if(get(arr,mid-1)<arr[mid] && get(arr, mid+1)<arr[mid])
                return mid;
            if(arr[0]<=arr[mid])
                return findPivot(arr, mid+1, end);
            return findPivot(arr, start, mid-1);
        }

        private int get(int[] arr, int index){
            if(index<0 || index>=arr.length)
                return Integer.MIN_VALUE;
            return arr[index];
        }

    public static void main(String[] args) {
        RotatedSortedArraySearch driver = new RotatedSortedArraySearch();
        int[] input1 = new int[]{ 0, 1, 2, 3,4,7};
        int val = driver.search(input1,1);
        System.out.println(val);
    }
}
