package grokking_ds_patterns.tree_bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, populate an array to represent its level-by-level traversal.
 * You should populate the values of all nodes of each level from left to right in separate sub-arrays.
 */
public class LevelOrderTraversal {

    /**
     * Time Complexity: O(n)
     * Space Complexity: We will need O(n) space for the queue.
     * Additionally, we need O(n) to return a list containing the level order traversal.
     * Actually, if w is the maximum width of a level in binary tree, space actually used by queue is O(w).
     * And since we can have a maximum of n/2 nodes at any level (this could happen only at the lowest level),
     * therefore we will need O(n) space to store them in the queue.
     *
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if(root == null)
            return levels;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode node;
        List<Integer> level;
        while(!queue.isEmpty()){
            int size = queue.size();
            level = new ArrayList<>();
            for(int i = 0; i<size; i++){
                node = queue.remove();
                level.add(node.val);
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }
            levels.add(level);
        }
        return levels;
    }
}
