package grokking_ds_patterns.tree_dfs;

/**
 * Given a binary tree and a number ‘S’, find if the tree has a path from root-to-leaf such that the
 * sum of all the node values of that path equals ‘S’.
 */
public class BinaryTreePathSum {

    /**
     * Time Complexity: O(n) where ‘n’ is the total number of nodes in the tree.
     *  This is due to the fact that we traverse each node once.
     * Space Complexity: O(h), For recursion stack, where h is height of the binary tree.
     * In worst cases h == n when the tree is a linked list.
     */
    public boolean hasPathSum(TreeNode root, int sum){
        if(root == null)
            return false;
        if(root.left == null && root.right == null)
            return root.val == sum;
        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }
}
