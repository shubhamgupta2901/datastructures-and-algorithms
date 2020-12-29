package problem_solving.recursion_and_backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationSumII {
    private ArrayList<ArrayList<Integer>> output;
    private int target;
    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {
        output = new ArrayList<>();
        target = b;
        Collections.sort(a);
        ArrayList<Integer> emptySet = new ArrayList<>();
        helper(a,emptySet,0,0);
        return output;
    }

    private void helper(ArrayList<Integer> input,ArrayList<Integer>subset,int index, int sum){
        if(sum == target){
            ArrayList<Integer> newSubset = new ArrayList<Integer>(subset);
            output.add(newSubset);
            return;
        }
        if(sum>target || index >= input.size())
            return;

        subset.add(input.get(index));
        helper(input, subset,index+1, sum+input.get(index));
        subset.remove(subset.size()-1);
        int i = index+1;
        while(i<input.size()){
            if(input.get(i)!=input.get(index))
                break;
            i++;
        }
        helper(input, subset, i, sum);
    }

    public static void main(String[] args) {
        CombinationSumII driver = new CombinationSumII();
        Integer[] arr = new Integer[]{1,1,1,1,1};
        List<Integer> list = Arrays.asList(arr);
        ArrayList<Integer> input = new ArrayList<>(list);
        ArrayList<ArrayList<Integer>> output = driver.combinationSum(input,5);
        for(ArrayList<Integer> combination : output){
            for(Integer num: combination){
                System.out.print(num+" ");
            }
            System.out.println(" ");
        }
    }
}
