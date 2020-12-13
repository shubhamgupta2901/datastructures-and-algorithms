package scaler;

public class SingleElementInSortedArray {
    /**
     * Approach 1:
     *  1. Maintain a hashset and traverse the  array,
     *  2. For every element
     *      i. if the element is not present in hashset insert it.
     *      ii. if element is already present in hashset, remove it
     *  3. The only element at the end in hashset would be the solution.
     * Time Complexity: O(n) Space Complexity: O(n)
     *
     * Approach 2:
     * XOR bitwise operator behaviour:
     * 1. A^B = B^A
     * 2. A^A = 0
     * 3. A^0 = A
     * 4. A^B^C = A^(B^C) = (A^B)^C = (A^C)^B
     *
     * Approach:
     * 1. Take XOR of all elements in array
     * 2. Every pair of elements which are duplicate will give result 0;
     * 3. Only number A be XORed with 0 which will give result A
     * Time complexity: O(n) Space Complexity: O(1)
     *
     * Approach 3:
     * Observation:
     * Before the occurrunce of unique element in array, every number would occur twice,
     * so first instance of new number will always be at EVEN index(0,2,4,...), and second instance of same number will be at ODD INDEX(1,3,5,7)
     * After the occurunce of duplicate element , this pattern gets violated.
     * Time Complexity: O(logn)
     *
     * */
    public int solve(int[] A) {
        if(A == null || A.length == 0)
            return -1;
        int start = 0, end = A.length-1;
        while(start<=end){
            int mid = start + (end-start)/2;
            if(get(A,mid)!=get(A, mid-1) && get(A, mid)!= get(A, mid+1))
                return get(A, mid);
            else if(mid%2 == 0 && get(A, mid) == get(A, mid+1))
                start= mid+1;
            else if(mid%2 == 1 && get(A,mid) == get(A, mid-1))
                start = mid+1;
            else end = mid-1;

        }
        return -1;
    }

    private int get(int[]A, int index){
        if(index<0 || index>= A.length)
            return -1;
        return A[index];
    }
}
