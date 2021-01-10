package problem_solving.sorting.algorithms;

public class MergeSort {
    /**
     * [3,6,9]  [1,4,9,13]
     *
     * i: 0,1,2,3
     * j: 0,1,2
     * k: 0,1,2,3,4,5
     * [1,3,4,6,9,9,13]
     */

    public void sort(int[] arr){
        if(arr == null || arr.length<=1)
            return;
        int start = 0;
        int end = arr.length-1;
        mergeSort(arr, start, end);
    }

    private void mergeSort(int[] arr, int start, int end){
        if(start == end)
            return;
        int mid = (start+end)/2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid+1, end);
        merge(arr, start, mid, end);
    }

    private void merge(int[] arr, int start, int mid, int end){
        int[] arr1 = new int[mid-start+1];
        int[] arr2 = new int[end-mid];

        for(int i = start; i<=mid; i++){
            arr1[i-start] = arr[i];
        }
        for(int i = mid+1; i<=end;i++){
            arr2[i-mid-1] = arr[i];
        }

        int i = 0, j = 0, k = start;
        while(i<arr1.length && j<arr2.length){
            if(arr1[i]<=arr2[j]){
                arr[k] = arr1[i];
                i++;
            }else{
                arr[k] = arr2[j];
                j++;
            }
            k++;
        }
        if(i<arr1.length){
            while(i< arr1.length) {
                arr[k] = arr1[i];
                i++;
                k++;
            }
        }
        if(j< arr2.length){
            while(j<arr2.length){
                arr[k] = arr2[j];
                j++;
                k++;
            }
        }
    }


    public static void main(String[] args) {
        MergeSort driver = new MergeSort();
        int[] input = new int[]{5,3,4,2,9,8,6,1,3,7};
        driver.sort(input);
        for(Integer num: input){
            System.out.print(num+" ");
        }
    }
}
