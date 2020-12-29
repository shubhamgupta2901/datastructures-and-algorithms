package problem_solving.recursion_and_backtracking;

import java.util.Arrays;

public class NumberOfSquarefulArrays {


    int count = 0;
    public int solve(int[] A) {
        if(A== null || A.length<=1)
            return 0;
        Arrays.sort(A);
        helper(A, 0);
        return count;
    }

    private void helper(int[]A, int index){
        if(index == A.length-1){

            if(isSquareFul(A)){
                print(A);
                count++;
            }
            return;
        }

        for(int i = index;i<A.length; i++){
            if(i>index && A[index] == A[i])
                continue;
            swap(A, index, i);
            helper(A, index+1);
            swap(A, index, i);
        }
    }
    private void swap(int[]A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private boolean isSquareFul(int[]A){
        for(int i = 0; i<A.length-1; i++){
            int sum = A[i] + A[i+1];
            double squareRoot = Math.sqrt(sum);
            if(squareRoot - Math.floor(squareRoot)!=0)
                return false;
        }

        return true;
    }

    private void print(int[] arr){
        for(Integer i: arr)
            System.out.print(i+" ");
        System.out.println("");
    }

    public static void main(String[] args) {
        NumberOfSquarefulArrays driver = new NumberOfSquarefulArrays();
        int[] input = new int[]{36229, 1020, 69, 127, 162, 127};
        int output = driver.solve(input);
        System.out.println(output);
    }
}
