package problem_solving;

import java.util.*;

/**
 * Given an array of integers, A of length N, find out the maximum sum sub-array of non negative numbers from A.
 * The sub-array should be contiguous i.e., a sub-array created by choosing the second and fourth element and skipping the third element is invalid.
 * Maximum sub-array is defined in terms of the sum of the elements in the sub-array.
 * Find and return the required subarray.
 * NOTE:
 * 1. If there is a tie, then compare with segment's length and return segment which has maximum length.
 * 2. If there is still a tie, then return the segment with minimum starting index.
 *
 *  Input 1:
 *     A = [1, 2, 5, -7, 2, 3]
 *
 * Output 1:
 *     [1, 2, 5]
 *
 * Explanation 1:
 *     The two sub-arrays are [1, 2, 5] [2, 3].
 *     The answer is [1, 2, 5] as its sum is larger than [2, 3].
 *
 * Input 2:
 *     A = [10, -1, 2, 3, -4, 100]
 *
 * Output 2:
 *     [100]
 *
 * Explanation 2:
 *     The three sub-arrays are [10], [2, 3], [100].
 *     The answer is [100] as its sum is larger than the other two.
 */

public class MaxNonNegativeSubArray {

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param A
     * @return
     */
    public ArrayList<Integer> maxset(ArrayList<Integer> A) {
        ArrayList<Integer> outputList = new ArrayList<>();
        if(A == null || A.size() == 0)
            return outputList;
        long candidateSum = 0, maxSum = 0;
        ArrayList<Integer> candidateList = new ArrayList<>();
        for(int i = 0; i<A.size(); i++){
            if(A.get(i)<0){
                if(candidateSum>maxSum || (candidateSum == maxSum && candidateList.size()> outputList.size())){
                    outputList = candidateList;
                    maxSum = candidateSum;
                }
                candidateList = new ArrayList<>();
                candidateSum = 0;
            }else {
                candidateList.add(A.get(i));
                candidateSum+= A.get(i);
            }
        }
        if(candidateSum>maxSum || (candidateSum == maxSum && candidateList.size()> outputList.size())){
            outputList = candidateList;
        }
        return outputList;
    }


    public static void main(String[] args) {
        MaxNonNegativeSubArray driver = new MaxNonNegativeSubArray();
        List<Integer> list = Arrays.asList(24115, -75629, -46517, 30105, 19451, -82188, 99505, 6752, -36716, 54438, -51501, 83871, 11137, -53177, 22294, -21609, -59745, 53635, -98142, 27968, -260, 41594, 16395, 19113, 71006, -97942, 42082, -30767, 85695, -73671);
        ArrayList<Integer> input = new ArrayList<>(list);
        ArrayList<Integer> output = driver.maxset(input);
        System.out.println(output);
    }
}
