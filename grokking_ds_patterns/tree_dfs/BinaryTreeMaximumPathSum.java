package grokking_ds_patterns.tree_dfs;

/**
 * <a href="https://leetcode.com/problems/binary-tree-maximum-path-sum/"/>
 * Given a non-empty binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along
 * the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 *
 * Example 2:
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 */
public class BinaryTreeMaximumPathSum {
    /**
     * This problem seems similar to finding the diameter of a binary tree
     * (https://github.com/shubhamgupta2901/datastructures-and-algorithms/blob/master/grokking_ds_patterns/tree_dfs/TreeDiameter.java)
     *
     * Approach 1: Wrong Answer
     * Max Path Sum for a node = Max root-leaf path sum for node.left + Max root-leaf path sum for node.right + node.val
     * Eventually the max path sum for a binary tree will be the highest max path sum of a node in binary tree.
     * 1. Keep a global variable to track the max path sum of a binary tree.
     * 2. For every node, find the max root-leaf path for its left and right nodes.
     * 3. Max root-leaf path for the node will be node.val + Math.max(left max root-leaf path sum, right max root-leaf path sum)
     * 4. Before returning the max root-leaf path for node, calculate the max path sum, and modify the global value if required.
     *
     * Time Complexity: O(n)
     * Space Compelxity: O(h)
     *
     */
    class Approach1 {
        int maxPathSum = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root) {
            maxSumRootToLeafPath(root);
            return maxPathSum;
        }

        private int maxSumRootToLeafPath(TreeNode root){
            if(root == null)
                return 0;
            int maxLeftRootToLeafSum = maxSumRootToLeafPath(root.left);
            int maxRightRootToLeafSum = maxSumRootToLeafPath(root.right);
            int totalPathSum = root.val + maxLeftRootToLeafSum + maxRightRootToLeafSum;
            if(totalPathSum>maxPathSum)
                maxPathSum = totalPathSum;
            return root.val + Math.max(maxLeftRootToLeafSum,maxRightRootToLeafSum);
        }
    }

    /**
     * Approach 2: Accepted.
     * While the logic in first approach seems correct it does not take into account the negative root-leaf path sums of a
     * node's children.
     * In those cases it is better to ignore them and only return the value of current node while finding the max root-leaf
     * path sum of a node.
     * The edge cases the first approach didn't cover were:
     * Case I:
     *        2
     *       /
     *      -1
     * Expected output: 2
     * Output: 1
     *
     * Case II:
     *                9
     *               / \
     *              6  -3
     *                 / \
     *                -6  2
     *                   / \
     *                  -6 -6
     * Expected Output: 16 [6->9->-3->2->2]
     * Ouput: 15[ 6->9]
     *
     * Time Complexity: O(n)
     * Space Compelxity: O(h)
     */
    class Approach2 {
        int maxPathSum = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root) {
            maxSumRootToLeafPath(root);
            return maxPathSum;
        }

        private int maxSumRootToLeafPath(TreeNode root){
            if(root == null)
                return 0;
            int maxLeftRootToLeafSum = maxSumRootToLeafPath(root.left);
            int maxRightRootToLeafSum = maxSumRootToLeafPath(root.right);
            int totalPathSum = largest(root.val, root.val + maxLeftRootToLeafSum, root.val + maxRightRootToLeafSum, root.val + maxLeftRootToLeafSum + maxRightRootToLeafSum );
            if(totalPathSum>maxPathSum)
                maxPathSum = totalPathSum;
            return Math.max(root.val , root.val + Math.max(maxLeftRootToLeafSum,maxRightRootToLeafSum));
        }

        private int largest(int a , int b, int c, int d){
            return Math.max(a, Math.max(b, Math.max(c,d)));
        }
    }
}
