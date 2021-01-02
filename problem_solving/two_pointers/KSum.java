package problem_solving.two_pointers;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Given a sorted array with distinct integers and a number K, find all pairs of indices in the array where the
 * sum of value at those indices is equal to K.
 * Example:
 * array: [1,2,6,9,14,20,21], K = 15
 * Output: [[0,4],[2,3]]
 *
 * left: 0,1,2,3
 * right:6,5,4,3,2
 *
 * [[0,4],[2,3]]
 */
public class KSum {

    /**
     * HashSet approach similar to 2 sum
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public ArrayList<ArrayList<Integer>> findPairsApproach1(int[] arr, int K){
        ArrayList<ArrayList<Integer>> output = new ArrayList<>();
        if(arr == null || arr.length<=1)
            return output;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<arr.length; i++){
            if(map.containsKey(K-arr[i])){
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(map.get(K-arr[i]));
                pair.add(i);
                output.add(pair);
            }
            map.put(arr[i],i);
        }
        return  output;
    }
    /**
     * Two Pointers approach
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ArrayList<ArrayList<Integer>> findPairsApproach2(int[] arr, int K){
        ArrayList<ArrayList<Integer>> output = new ArrayList<>();
        if(arr == null || arr.length<=1)
            return output;
        int left = 0, right = arr.length-1;
        while(left<right) {
            long sum = arr[left]+ arr[right];
            if(sum == K) {
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(left);
                pair.add(right);
                output.add(pair);
                left++;
                right--;
            }else if (sum<K){
                left++;
            }else  right--;
        }
        return  output;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1,2,6,9,14,20,21};
        KSum driver = new KSum();
        ArrayList<ArrayList<Integer>> output = driver.findPairsApproach1(input, 15);
        for(ArrayList<Integer> pair: output){
            System.out.print("[ "+pair.get(0) +" , "+pair.get(1)+" ]");
            System.out.println("");
        }
    }
}
