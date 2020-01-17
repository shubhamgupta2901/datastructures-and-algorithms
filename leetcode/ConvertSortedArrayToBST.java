package leetcode;

/**
 * <a href="https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/" />
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class ConvertSortedArrayToBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    /**
     * Approach 1: Recursive approach to problem.
     * Time Complexity: O(n) for array of n elements.
     * Runtime percentile is 100%
     * But memory usage percentile is 5%.
     * This may be because the use of recursive stack.
     */
    public TreeNode sortedArrayToBSTRecursive(int[] nums) {
        return arrayToBST(nums, 0, nums.length-1);
    }


    private TreeNode arrayToBST(int[] nums, int start, int end){
        if(start>end)
            return null;
        int mid = (end-start)/2+start;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = arrayToBST(nums, start, mid-1);
        root.right = arrayToBST(nums, mid+1, end);
        return root;
    }
}
