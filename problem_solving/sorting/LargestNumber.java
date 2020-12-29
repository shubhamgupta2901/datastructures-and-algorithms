package problem_solving.sorting;

import java.util.Arrays;
import java.util.Comparator;


public class LargestNumber {

    public String largestNumber(final int[] A) {
        String[] arr = new String[A.length];
        for(int i = 0; i<arr.length; i++)
            arr[i] = String.valueOf(A[i]);

        Arrays.sort(arr,new StringComparator());
        String largestNumber = "";
        for(int i = arr.length-1; i>=0; i--){
            largestNumber+=arr[i];
        }
        return largestNumber;
    }

    private class StringComparator implements Comparator<String>{
        @Override
        public int compare(String str1, String str2){
            String str1Pre = str1.concat(str2);
            String str2Pre = str2.concat(str1);
            return str1Pre.compareTo(str2Pre);
        }
    }

    public static void main(String[] args) {
        LargestNumber driver = new LargestNumber();
        int[] input1 = new int[]{1,2,3,5,4};
        int[] input2 = new int[]{3, 30, 34, 5, 9};
        String output = driver.largestNumber(input2);
        System.out.println(output);
    }

}

