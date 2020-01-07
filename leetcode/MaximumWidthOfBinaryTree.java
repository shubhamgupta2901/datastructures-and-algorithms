package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.com/problems/maximum-width-of-binary-tree/"/>
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.
 *
 * Example 1:
 *
 * Input:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 * Example 2:
 *
 * Input:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 * Example 3:
 *
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 *
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 * Example 4:
 *
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 *
 *
 * Note: Answer will in the range of 32-bit signed integer.
 */
public class MaximumWidthOfBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public class WeightedTreeNode {
        TreeNode node;
        int weight;
        WeightedTreeNode(TreeNode node, int weight){
            this.node = node;
            this.weight = weight;
        }
    }

    /**
     * The idea here is to assign a weight to each binary tree node.
     * For a node with weight n:
     * assign a weight 2*n+1 to its left child, and
     * assign a weight 2*n+2 to its right child.
     * The width of a level would be difference between the last node in the level and first node in the level plus one.
     * @param root
     * @return
     * Time Complexity: O(n) Since we are visiting each node only once using BFS
     * Space Complexity: O(n) We need a Dequeue to keep track of WeightedNodes
     */
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        int treeWidth = 0;
        Queue<WeightedTreeNode> queue = new LinkedList<>();
        queue.add(new WeightedTreeNode(root, 0));
        while (!queue.isEmpty()){
            int levelWidth = ((LinkedList<WeightedTreeNode>) queue).getLast().weight - ((LinkedList<WeightedTreeNode>) queue).getFirst().weight + 1;
            if(levelWidth > treeWidth) treeWidth = levelWidth;
            int size = queue.size();
            for(int i = 0; i<size; i++){
                WeightedTreeNode node = queue.remove();
                if(node.node.left != null)
                    queue.add(new WeightedTreeNode(node.node.left,2*node.weight+1));
                if(node.node.right != null)
                    queue.add(new WeightedTreeNode(node.node.right, 2*node.weight+2));
            }

        }
        return treeWidth;
    }

}
