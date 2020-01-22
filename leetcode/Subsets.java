package leetcode;

import playground.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/subsets/"/>
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Subsets {

    /**
     * Approach 1: Iterative approach
     * Start from empty subset in output list.
     * At each step takes a new integer from array into consideration
     * and generates new subsets from the existing ones.
     * Time Complexity: O(n2^n)
     * Space Complexity: O(2^n) storing number of solutions for subsets
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> emptySubset = new ArrayList<>();
        subsets.add(emptySubset);
        for(int i = 0; i<nums.length; i++){
            int size = subsets.size();
            for(int j = 0; j<size; j++){
                List<Integer> newSubset = deepCopy(subsets.get(j));
                newSubset.add(nums[i]);
                subsets.add(newSubset);
            }
        }
        return subsets;
    }

    private List<Integer> deepCopy(List<Integer> originalList){
        List<Integer> copiedList = new ArrayList<>();
        for(int i = 0; i<originalList.size(); i++){
            copiedList.add(originalList.get(i));
        }
        return copiedList;
    }


    public static void main(String[] args) {
        Subsets demo = new Subsets();
        List<List<Integer>> subsets = demo.subsets(new int[]{1,2,3});
        for(int i = 0; i<subsets.size(); i++){
            List<Integer> subset = subsets.get(i);
            System.out.print("{ ");
            for(int j = 0 ; j<subset.size(); j++){
                System.out.print(subset.get(j)+ ",");
            }
            System.out.print("}");
            System.out.println("");
        }
    }
}
