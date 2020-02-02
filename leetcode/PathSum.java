package leetcode;

/**
 * <a href="https://leetcode.com/problems/path-sum/"/>
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class PathSum {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * Approach 1: recursive approach
     * Time complexity: O(n) where n is the number of nodes.
     * Space complexity: O(h) where h is height of the tree.
     * Note that this is the space recursive stack occupies,
     * and at a time there will be at max h nodes in stack.
     * For skewed trees h ~ n. So O(n) would also not be wrong.
     */
    public boolean hasPathSum(TreeNode root, int sum){
        if(root == null)
            return false;
        //When the node is leaf
        if(root.left == null && root.right == null)
            return root.val == sum;
        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }
}
