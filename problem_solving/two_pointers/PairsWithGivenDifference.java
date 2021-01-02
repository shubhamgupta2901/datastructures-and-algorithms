package problem_solving.two_pointers;

import java.util.*;

/**
 * Given an one-dimensional integer array A of size N and an integer B.
 * Count all distinct pairs with difference equal to B.
 * Here a pair is defined as an integer pair (x, y), where x and y are both numbers in the array and their absolute difference is B.
 *
 * Problem Constraints
 * 1 <= N <= 104
 * 0 <= A[i], B <= 105
 *
 * Input Format
 * First argument is an one-dimensional integer array A of size N.
 * Second argument is an integer B.
 *
 * Output Format
 * Return an integer denoting the count of all distinct pairs with difference equal to B.
 *
 * Input:
 *  A = [1, 5, 3, 4, 2]
 *  B = 3
 * Output : 2
 * There are 2 unique pairs with difference 3, the pairs are {1, 4} and {5, 2}
 *
 * Input:
 *  A = [8, 12, 16, 4, 0, 20]
 *  B = 4
 * Output: 5
 * There are 5 unique pairs with difference 4, the pairs are {0, 4}, {4, 8}, {8, 12}, {12, 16} and {16, 20}
 *
 * Input:
 *  A = [1, 1, 1, 2, 2]
 *  B = 0
 * Output: 2
 * There are 2 unique pairs with difference 0, the pairs are {1, 1} and {2, 2}.
 */
public class PairsWithGivenDifference {

    /**
     * Approach 1: While traversing the array, maintain a hashset and for every number A[i],
     * check whether A[i]-B or A[i]+B exists in array,
     * When a number is encountered which already exists in hashset we can skip it, since we need unique pairs.
     * This won't work when B = 0, for this case we can simply count the number of elements which are repeating.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int approach1(int[] A, int B) {
        int count = 0;
        if(A == null || A.length <= 1)
            return count;
        if(B==0){
            //count the numbers which are repeating;
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int i = 0; i<A.length; i++){
                int frequency = map.getOrDefault(A[i], 0);
                map.put(A[i], frequency+1);
            }
            for(Map.Entry<Integer, Integer> entry: map.entrySet()){
                if(entry.getValue()>1)
                    count++;
            }
            return count;
        }
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i = 0; i<A.length; i++){
            if(set.contains(A[i]))
                continue;
            if(set.contains(A[i]-B))
                count++;
            if(set.contains(A[i]+B))
                count++;
            set.add(A[i]);
        }
        return count;
    }

    /**
     * Approach 2: Sort the array, and apply two pointers approach
     * left pointer starts at index 0, right pointer at index 1
     * Time Complexity: O(nlogn) : For sorting the array
     * Space Complexity: O(1)
     */
    public int approach2(int[] A, int B) {
        int count = 0;
        if(A == null || A.length <= 1)
            return count;
        Arrays.sort(A);
        int left = 0, right = 1;
        while(right<A.length){
            if(left == right){
                right++;
                continue;
            }
            int diff = A[right]-A[left];
            if(diff<B){
                right++;
            }else if(diff>B){
                left++;
            }
            else{
                count++;
                while(left< A.length-1 && A[left] == A[left+1])
                    left++;
                while(right< A.length-1 && A[right] == A[right+1])
                    right++;
                left++;
                right++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1,2};
        PairsWithGivenDifference driver = new PairsWithGivenDifference();
        int output = driver.approach2(input,3);
        System.out.println(output);
    }

}
