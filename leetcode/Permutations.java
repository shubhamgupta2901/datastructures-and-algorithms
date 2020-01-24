package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * < a href="https://leetcode.com/problems/permutations/"/>
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        if(nums.length == 0)
            return permutations;
        List<Integer> permutation = toArrayList(nums);
        permuteHelper(permutation,0, permutations);
        return permutations;
    }

    private void permuteHelper(List<Integer> permutation, int index, List<List<Integer>> permutations){
        if(index == permutation.size()-1){
            permutations.add(permutation);
            return;
        }
        permuteHelper(permutation, index+1, permutations);

        for(int i = index+1; i<permutation.size(); i++){
            List<Integer> newPermutation = new ArrayList<>(permutation);
            swap(newPermutation, index, i);
            permuteHelper(newPermutation, index+1, permutations);
        }
        return;
    }

    private  List<Integer> toArrayList(int[] nums){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i<nums.length; i++)
            list.add(nums[i]);
        return list;
    }

    private void swap(List<Integer> list, int index1, int index2){
        int temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}
