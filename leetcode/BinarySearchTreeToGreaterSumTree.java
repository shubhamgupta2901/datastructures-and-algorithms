package leetcode;

import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/"/>
 * Given the root of a binary search tree with distinct values, modify it so that every node has a new value equal to
 * the sum of the values of the original tree that are greater than or equal to node.val.
 *
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 *  The left subtree of a node contains only nodes with keys less than the node's key.
 *  The right subtree of a node contains only nodes with keys greater than the node's key.
 *  Both the left and right subtrees must also be binary search trees.
 *
 * Note:
 * The number of nodes in the tree is between 1 and 100.
 * Each node will have value between 0 and 100.
 * The given tree is a binary search tree.
 */
public class BinarySearchTreeToGreaterSumTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * Approach 1: Iterative - Accepted (100% time and 100% space complexity percentile)
     * The idea was an inorder traversal of bst always returns elements in sorted manner.
     * So the idea was to perform an inorder traversal and keep the nodes in a stack. The top of this stack
     * will have the node with largest value, so we keep adding the sum to subsequent nodes in stack.
     * Time Complexity: O(n)
     * Space Complexity: O(n) where n is the number of nodes in trees
     */
    public TreeNode bstToGstApproach1(TreeNode root) {
        if(root == null)
            return null;

        Stack<TreeNode> gstStack = new Stack<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr!= null || !stack.isEmpty()){
            while(curr!=null){
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            gstStack.push(curr);
            curr = curr.right;
        }
        int sum = 0;
        while(!gstStack.isEmpty()){
            curr = gstStack.pop();
            curr.val+= sum;
            sum = curr.val;
        }
        return root;
    }
}
