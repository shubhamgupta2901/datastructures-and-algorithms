package grokking_ds_patterns.tree_dfs;

/**
 * Given a binary tree, find the length of its diameter. The diameter of a tree is the number of nodes on the longest
 * path between any two leaf nodes. The diameter of a tree may or may not pass through the root.
 * Note: You can always assume that there are at least two leaf nodes in the given tree.
 *
 * Example:
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 4, which is the number of nodes in path [4,2,1,3] or [5,2,1,3].
 */
public class TreeDiameter {
    /**
     * The diameter of a node in binary tree is height of its left tree + height of its right tree + 1 ( 1
     * for the node itself). Diameter of a binary tree is the maximum diameter of a node in binary tree, (which may not
     * be root of binary tree.)
     * This will give us the maximum number of nodes in a path, if we need to find the maximum length of path, like in
     * this question, we may not need to add 1 height of left tree and height of right tree.
     *
     * Approach 1:
     * 1. For every node, we need to find the height of both children of the current node.
     * For this, we will create a annotated tree, every node will also have height in addition to its value.
     * 2. The height of the current node will be equal to the maximum of the heights of its left or right children,
     * plus ‘1’ for the current node.
     * 3 .The tree diameter at the current node will be equal to the height of the left child plus
     * the height of the right child plus ‘1’ for the current node:
     * diameter = leftTreeHeight + rightTreeHeight + 1.
     * To find the overall tree diameter, we will find the maximum diameter of all the nodes.
     * Time Complexity: O(n) two dfs functions
     * Space complexity: O(n). O(n) for creating annotated tree, and O(h) for recursive stack.
     *
     */
    class Approach1 {
        class AnnotatedTreeNode{
            int val;
            int height;
            AnnotatedTreeNode left;
            AnnotatedTreeNode right;
            AnnotatedTreeNode(int x){val = x;}
        }

        public int diameterOfBinaryTree(TreeNode root) {
            if(root == null)
                return 0;
            AnnotatedTreeNode aRoot = annotate(root);
            return diameter(aRoot);
        }

        private AnnotatedTreeNode annotate(TreeNode root){
            if(root == null)
                return null;
            AnnotatedTreeNode node = new AnnotatedTreeNode(root.val);
            node.left = annotate(root.left);
            node.right = annotate(root.right);
            int leftHeight = node.left == null ? 0 : node.left.height;
            int rightHeight = node.right == null? 0 : node.right.height;
            node.height = 1 + Math.max(leftHeight, rightHeight);
            return node;
        }

        private int diameter(AnnotatedTreeNode node){
            if(node == null)
                return 0;
            int nodeDiameter = (node.left == null ? 0 : node.left.height) +
                    (node.right == null ?  0 : node.right.height) ;
            int leftDiameter = diameter(node.left);
            int rightDiameter = diameter(node.right);
            return Math.max(nodeDiameter, Math.max(leftDiameter, rightDiameter));
        }
    }

    /**
     * Approach 2: Take a class level variable maxDiameter and while finding out the height of a node,
     * also calculate its diameter, update the value of maxDiameter if the current diameter is greater than
     * maxDiameter and then return the height of the node.
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */
    class Approach2 {
        int maxDiameter = 0;
        public int diameterOfBinaryTree(TreeNode root) {
            height(root);
            return maxDiameter;
        }

        private int height(TreeNode root){
            if(root == null)
                return 0;
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            int diameter = leftHeight + rightHeight;
            if(diameter>maxDiameter)
                maxDiameter = diameter;
            return 1 + Math.max(leftHeight, rightHeight);
        }
    }
}
