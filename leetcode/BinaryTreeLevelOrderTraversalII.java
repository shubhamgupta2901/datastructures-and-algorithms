package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class BinaryTreeLevelOrderTraversalII {

    /**
     * Approach: Similar to Level order traversal, except rather than adding the new level
     * at the end of the list, add it add the beginning.
     * Another approach would have been to create level while traversing in bfs fashion and keep them adding
     * to a stack. At the end pop levels from stack and add it to the list which needs to be returned.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n) for queue and traversal list
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if(root == null)
            return levels;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode node;
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i<size; i++){
                node = queue.remove();
                level.add(node.val);
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }
            levels.add(0,level);
        }
        return levels;
    }
}
