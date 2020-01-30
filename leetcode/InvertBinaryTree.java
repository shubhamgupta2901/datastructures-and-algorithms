package leetcode;

public class InvertBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * Recursive approach
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;
        TreeNode leftInvertedSubtree = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = leftInvertedSubtree;
        return root;
    }

}
