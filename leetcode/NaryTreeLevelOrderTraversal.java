package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://leetcode.com/problems/n-ary-tree-level-order-traversal/"/>
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 */
public class NaryTreeLevelOrderTraversal {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * Applying the same bfs logic as we use for binary tree,
     * except here we have n children and not 2.
     * @param root
     * @return
     * Time Complexity: O(n) as we traverse each node once.
     * Space Complexity: O(n) for using the queue.
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> traversal = new ArrayList<>();
        if(root == null)
            return traversal;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i<size; i++){
                Node curr = queue.remove();
                level.add(curr.val);
                for(Node child: curr.children)
                    queue.add(child);
            }
            traversal.add(level);
        }
        return traversal;
    }
}
