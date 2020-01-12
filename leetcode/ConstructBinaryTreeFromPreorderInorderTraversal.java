package leetcode;

/**
 * <a href="https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/"/>
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class ConstructBinaryTreeFromPreorderInorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    /**
     * Brute force approach:
     * Run time in leetcode is 40 percentile
     * and memory usage is 24 percentile.
     * Could do better where I manipulate the indices of array rather than
     * creating new sub arrays for every subproblem
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeApproach1(int[] preorder, int[] inorder) {
        if(preorder.length == 0)
            return null;
        if(preorder.length == 1)
            return new TreeNode(preorder[0]);
        TreeNode root = new TreeNode(preorder[0]);
        int inorderIndex = indexOf(inorder, preorder[0]);
        int[] leftInorderSubarray = subarray(inorder, 0, inorderIndex-1);
        int[] leftPreorderSubarray = subarray(preorder, 1, leftInorderSubarray.length);
        int[] rightInorderSubarray = subarray(inorder, inorderIndex+1, inorder.length-1);
        int[] rightPreorderSubarray = subarray(preorder, leftPreorderSubarray.length+1,preorder.length-1);

        root.left = buildTreeApproach1(leftPreorderSubarray, leftInorderSubarray);
        root.right = buildTreeApproach1(rightPreorderSubarray, rightInorderSubarray);
        return root;
    }

    private int indexOf(int[] inorder, int key){
        for(int i = 0; i<inorder.length; i++){
            if(inorder[i] == key)
                return i;
        }
        return -1;
    }

    private int[] subarray(int[] arr, int start, int end){
        if(start>end)
            return new int[0];

        int[] subarray = new int[end-start+1];
        for(int i = start,j=0; i<=end; i++, j++)
            subarray[j] = arr[i];
        return subarray;
    }
}
