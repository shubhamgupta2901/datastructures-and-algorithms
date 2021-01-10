package problem_solving.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Largest Number
 * Given a array A of non negative integers, arrange them such that they form the largest number.
 * Note: The result may be very large, so you need to return a string instead of an integer.
 *
 * Problem Constraints
 * 1 <= len(A) <= 100000
 * 0 <= A[i] <= 2*109
 *
 * Input Format: First argument is an array of integers.
 * Output Format: Return a string representing the largest number.
 *
 * Input  : A = [3, 30, 34, 5, 9]
 * Output : "9534330"
 *
 * Input 2: A = [2, 3, 9, 0]
 * Output: "9320"
 */

public class LargestNumber {

    /**
     * Brute Force Approach: Generate all the permutations of Array, and compare them, keep track of maximum.
     * Time Complexity: O(n*n!) ?? O(n!) for generating all permutations, and O(n) for processing permutation at the end?
     * Space Complexity: O(n) for recursive stack.
     */
    static class Approach1{
        String max = "";
        public String largestNumber(final int[] A) {
            if(A == null)
                return "";
            generatePermuatations(A, 0);
            return max;
        }

        private void generatePermuatations(int[] arr, int index){
            if(index == arr.length-1){
                updateIfRequired(arr);
                return;
            }
            for(int i = index; i<arr.length; i++){
                swap(arr, index, i);
                generatePermuatations(arr,index+1);
                swap(arr,i, index);
            }
        }

        private void swap(int[] arr, int i , int j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        private void updateIfRequired(int[] permutation){
            StringBuilder builder = new StringBuilder();
            for(Integer num: permutation)
                builder.append(num);
            if(max == ""){
                max = builder.toString();
                return;
            }

            for(int i = 0; i< builder.length();i++){
                if(builder.charAt(i) == max.charAt(i))
                    continue;
                else if(builder.charAt(i) >max.charAt(i)){
                    max = builder.toString();
                    return;
                }else return;
            }
        }
    }

    /**
     * Observations:
     * 1. If the array had all elements between 0-9, we would simply require to sort the array in descending order.
     * 2. Here "3" > "30" because if we concat both strings, "330" > "303", which can become our criteria to sort elements.
     * Time Complexity: O(nlogn) since comparator will take O(1) time for each comparison
     *
     */
   static class Approach2{
       public String largestNumber(final int[] A) {
           if(A == null)
               return "";
           String[] arr = new String[A.length];
           boolean areAllZeros = true;
           for(int i = 0; i<arr.length; i++){
               if(A[i]>0)
                   areAllZeros = false;
               arr[i] = String.valueOf(A[i]);
           }
           if(areAllZeros){
               return "0";
           }

           Arrays.sort(arr, new CustomComparator());
           StringBuilder output = new StringBuilder();
           for(int i = 0; i<arr.length; i++){
               output.append(arr[i]);
           }
           return output.toString();
       }

       class CustomComparator implements Comparator<String>{
           public int compare(String str1,String str2){
               String str1Pre = str1.concat(str2);
               String str2Pre = str2.concat(str1);
               return str2Pre.compareTo(str1Pre);
           }
       }
   }

    public static void main(String[] args) {
        Approach1 driver = new LargestNumber.Approach1();
        int[] input1 = new int[]{1,2,3,5,4};
        int[] input2 = new int[]{3, 30, 34, 5, 9};
        int[] input3 = new int[] {472, 663, 964, 722, 485, 852, 635, 4, 368, 676};
        String output = driver.largestNumber(input3);
        System.out.println(output);
    }

}

