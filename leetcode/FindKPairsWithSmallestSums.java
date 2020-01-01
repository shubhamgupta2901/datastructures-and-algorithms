package leetcode;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/find-k-pairs-with-smallest-sums/"/>
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 *
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 *
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 *
 * Example 1:
 *
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence:
 *              [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 *
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [1,1],[1,1]
 * Explanation: The first 2 pairs are returned from the sequence:
 *              [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Example 3:
 *
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [1,3],[2,3]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 */
public class FindKPairsWithSmallestSums {
    /**
     * TODO: Try a better approach, the runtime is pretty bad and beats only 16% of solutions.
     * Note: I have not leveraged the fact that input arrays are in sorted order, the solution runs O(mn) to find all the possible pairs,
     * and maintains them in a Max heap which furthur takes log(k) time in every iteration.
     * Can surely do better than O(mnlogk)
     *
     */

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> heap = new PriorityQueue<>(k, new Comparator<List<Integer>>(){
            public int compare(List<Integer> list1, List<Integer> list2){
                return list2.get(0)+ list2.get(1) - list1.get(0) - list1.get(1);
            }
        });
        for(int i = 0; i<nums1.length; i++){
            for(int j = 0; j<nums2.length; j++){
                List<Integer> pair = new ArrayList<>(Arrays.asList(nums1[i], nums2[j]));
                if(heap.size()<k){
                    heap.add(pair);
                }else{
                    int pairSum = nums1[i]+nums2[j];
                    int maxSum = heap.peek().get(0) + heap.peek().get(1);
                    if(pairSum< maxSum){
                        heap.poll();
                        heap.add(pair);
                    }
                }
            }
        }
        int i =0;
        List<List<Integer>> smallestSumPairs = new ArrayList<>();
        while(!heap.isEmpty() && i<k){
            smallestSumPairs.add(heap.poll());
            i++;
        }
        return smallestSumPairs;
    }
}
