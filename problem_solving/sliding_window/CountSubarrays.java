package problem_solving.sliding_window;

import java.util.HashSet;

/**
 * Misha likes finding all Subarrays of an Array. Now she gives you an array A of N elements and told you to
 * find the number of subarrays of A, that have unique elements.
 * Since the number of subarrays could be large, return value % 109 +7.
 *
 * Problem Constraints
 * 1 <= N <= 105
 * 1 <= A[i] <= 106
 *
 * Input/Output Format
 * The only argument given is an Array A, having N integers.
 * Return the number of subarrays of A, that have unique elements.
 *
 *
 *
 * Example
 * Input: A = [1, 1, 3]
 * Output: 4
 * Subarrays of A that have unique elements only:
 * [1], [1], [1, 3], [3]
 *
 * Input : A = [2, 1, 2]
 * Output: 5
 * Subarrays of A that have unique elements only:
 * [2], [1], [2, 1], [1, 2], [2]
 */
public class CountSubarrays {
    /**
     * Approach 1: Create all possible subarrays, and keep count of frequency of each element in a hashmap,
     * if a new non-unique element occurs, discard the subarray.
     * Time Complexity: O(n^2)
     * Space Complexity: O(n) for HashMap
     */
    public int approach1(int[] A) {
        int MOD = 1000000007;
        long count = 0;
        HashSet<Integer> set;
        for(int i = 0; i<A.length; i++){
            count++;
            set = new HashSet<Integer>();
            set.add(A[i]);
            for(int j = i+1; j<A.length; j++){
                if(set.contains(A[j]))
                    break;
                else {
                    count++;
                    set.add(A[j]);
                }
            }
        }
        return (int)(count%MOD);
    }

    /**
     * Approach 2: Sliding window approach.
     * The idea is to initialize left, and right pointers from index zero, and maintain a set
     * If arr[right] was not present in the set, we can include all subarrays ending at arr[right],
     * (which would be right-left+1). and add arr[right] to set
     * If arr[right] was already present we remove arr[left] from set and increment left.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int approach2(int[] A){
        int MOD = 1000000007;
        long count = 0;
        int left = 0, right = 0;
        HashSet<Integer> set = new HashSet<>();
        while(left<=right && right<A.length){ //todo: check condition
            if(!set.contains(A[right])){
                count+= (right-left+1);
                set.add(A[right]);
                right++;
            }else {
                set.remove(A[left]);
                left++;
            }
        }
        return (int)(count%MOD);
    }

    public static void main(String[] args) {
        int[] input = new int[]{1,2,1,1,4};
        CountSubarrays driver = new CountSubarrays();
        int output = driver.approach1(input);
        System.out.println(output);
    }

}
