package grokking_ds_patterns.tree_dfs;

/**
 * Given a binary tree and a number sequence, find if the sequence is present as a root-to-leaf path in the given tree.
 * Example:
 *     3
 *    / \
 *   9  2
 *     / \
 *    1  7
 * Sequence [3,2,7] : true
 * Sequence [3,2] : false
 * Sequence [3,2,7,9]: false
 */
public class PathWithGivenSequence {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(h) where h is the height of the binary tree
     */
    public static boolean findPath(TreeNode root, int[] sequence) {
        return helper(root, sequence, 0);
    }
    private static boolean helper(TreeNode root, int[] sequence, int index){
        if(root == null)
            return index == sequence.length;
        if(index >= sequence.length || root.val != sequence[index])
            return false;
        return helper(root.left, sequence, index+1) || helper(root.right, sequence, index+1);
    }
}
