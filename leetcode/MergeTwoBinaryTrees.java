package leetcode;

/**
 * <a href="https://leetcode.com/problems/merge-two-binary-trees/"/>
 * Given two binary trees and imagine that when you put one of them to cover the other,
 * some nodes of the two trees are overlapped while the others are not.
 * You need to merge them into a new binary tree.
 * The merge rule is that if two nodes overlap, then sum node values up as the new value
 * of the merged node. Otherwise, the NOT null node will be used as the node of new tree.
 *
 * Input:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * Output:
 * Merged tree:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 *
 *
 * Note: The merging process must start from the root nodes of both trees.
 */
public class MergeTwoBinaryTrees {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * Approach 1: Recursive approach
     * Time complexity : O(n). A total of n nodes need to be traversed.
     * Here, n represents the minimum number of nodes from the two given trees.
     * Space complexity : O(n). The depth of the recursion tree can go upto n in the case of a skewed tree.
     * In average case, depth will be O(logn)
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null)
            return null;
        if(t1== null)
            return t2;
        if(t2 == null)
            return t1;
        t1.val = t1.val+t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
}
