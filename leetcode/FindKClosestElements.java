package leetcode;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/find-k-closest-elements/"/>
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array.
 * The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 *
 * Example 1:
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 *
 * Example 2:
 * Input: [1,2,3,4,5], k=4, x=-1
 * Output: [1,2,3,4]
 *
 * Note:
 * The value k is positive and will always be smaller than the length of the sorted array.
 * Length of the given array is positive and will not exceed 104
 * Absolute value of elements in the array and x will not exceed 104
 */
public class FindKClosestElements {

    class Pair{
        int element;
        int difference;

        Pair(int element, int difference){
            this.element = element;
            this.difference = difference;
        }
    }

    /**
     * Approach:
     * 1.Since the array is sorted, we can first find the number closest to ‘X’ through Binary Search.
     *  Let’s say that number is ‘Y’.
     * 2. The ‘K’ closest numbers to ‘Y’ will be adjacent to ‘Y’ in the array. We will search in both directions
     * of ‘Y’ to find the closest numbers.
     * 3. We can use a Max Heap to efficiently search for the closest numbers. We will take ‘K’ numbers in both
     * directions of ‘Y’ and push the K elements in heap, which have minimum absolute difference.
     * 4. This will ensure that the top 'K' numbers with the smallest difference from ‘X’ are now stored in heap.
     *
     * Time Complexity: O(logn)+ O(klogk).
     *  O(logn) for binary search, O(klogk) for storing in heap and O(klogk) for sorting the result.
     * Space Complexity: O(k) for heap and result
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int closestIndex = binarySearch(arr, x);
        PriorityQueue<Pair> heap = new PriorityQueue<>((pair1, pair2)-> compare(pair1, pair2));

        for(int i = closestIndex ; (i<arr.length && i<closestIndex + k); i++){
            Pair pair = new Pair(arr[i], Math.abs(x -arr[i]));
            if(heap.size()<k)
                heap.add(pair);
            else if(compare(pair, heap.peek())>0){
                heap.remove();
                heap.add(pair);
            }
        }

        for(int i = closestIndex-1; (i>= 0 && i>closestIndex-k);i--){
            Pair pair = new Pair(arr[i], Math.abs(x-arr[i]));
            if(heap.size()<k)
                heap.add(pair);
            else if(compare(pair, heap.peek())>0){
                heap.remove();
                heap.add(pair);
            }
        }
        List<Integer> list = new ArrayList<>();
        while(!heap.isEmpty())
            list.add(heap.poll().element);
        Collections.sort(list);
        return list;
    }

    private int compare (Pair pair1, Pair pair2){
        int difference = pair2.difference - pair1.difference;
        if(difference == 0)
            return pair2.element - pair1.element;
        return difference;
    }

    private int binarySearch(int[] arr, int x){
        int beg = 0, end = arr.length-1;
        int mid;
        int closestIndex = -1;
        while(beg<=end){
            mid = beg + (end-beg)/2;
            if(closestIndex == -1 || Math.abs(x-arr[mid])< Math.abs(x-arr[closestIndex]))
                closestIndex = mid;
            if(arr[mid] == x)
                return mid;
            else if(arr[mid]<x)
                beg = mid+1;
            else end =mid-1;
        }
        return closestIndex;
    }
}
