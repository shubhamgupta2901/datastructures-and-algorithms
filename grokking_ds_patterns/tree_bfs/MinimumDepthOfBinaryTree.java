package grokking_ds_patterns.tree_bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its minimum depth = 2.
 *
 *    3
 *   /
 *  2
 * returns its minimum depth 2
 *
 *   3
 *   \
 *   2
 * returns its minimum depth 2
 *
 */
public class MinimumDepthOfBinaryTree {

    /**
     * Approach 1: DFS
     * Writing a recursive function :
     * If a node is leaf return 1
     * If, a node has both left and right children, min depth will be 1 + min(mindepth(node.left), mindepth(node.right))
     * If node has only left child min depth will be 1 + mindepth(node.left)
     * else if it has only right child min depth will be 1 + mindepth(node.right)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(h) for recursive stack where h is the height of binary tree
     */
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return 1;
        int leftDepth = Integer.MAX_VALUE;
        if(root.left!=null)
            leftDepth = minDepth(root.left);
        int rightDepth = Integer.MAX_VALUE;
        if(root.right!=null)
            rightDepth = minDepth(root.right);
        return 1 + Math.min(leftDepth, rightDepth);
    }

    /**
     * Approach 2: BFS approach
     * Instead of keeping track of all the nodes in a level, we will only track the level of the tree.
     * As soon as we find our first leaf node, that level will represent the minimum depth of the tree.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int minDepthBFS(TreeNode root) {
        if(root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;
        TreeNode node;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i<size; i++){
                node = queue.remove();
                if(node.left == null && node.right == null)
                    return depth;
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }
            depth++;
        }
        return depth;
    }
}
