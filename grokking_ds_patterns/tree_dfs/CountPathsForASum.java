package grokking_ds_patterns.tree_dfs;

public class CountPathsForASum {
    /**
     * Time Complexity: ? (probably O(n^2) because every node is being called twice
     * Space Complexity: O(h) where h is the space complexity
     */
    public static int countPaths(TreeNode root, int S) {
        if(root == null) return 0;
        return helper(root, S );
    }

    private static int helper(TreeNode root, int sum){
        if(root.left == null && root.right == null)
            return sum == root.val ? 1 : 0;
        int count = 0;
        if(root.left != null)
            count = helper(root.left, sum) + helper(root.left, sum-root.val);
        if(root.right!= null)
            count += helper(root.right, sum) + helper(root.right, sum- root.val);
        return count;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + CountPathsForASum.countPaths(root, 11));
    }
}
