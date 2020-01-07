package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/n-ary-tree-preorder-traversal/"/>
 * Given an n-ary tree, return the preorder traversal of its nodes' values
 */
public class NaryTreePreorderTraversal {

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
     *  Approach 1: Recursive preorder without helper function.
     * @param root
     * @return
     * Time complexity: O(n) for visiting every node once
     * Space Complexity: O(n) for recursive stack.
     *
     */
    public List<Integer> recursivePreOrder(Node root) {
        List<Integer> traversal = new ArrayList<>();
        if(root == null)
            return traversal;
        traversal.add(root.val);
        for(Node node: root.children){
            traversal.addAll(recursivePreOrder(node));
        }
        return traversal;
    }


    /**
     * Approach 2: Recursive preorder with helper function
     * @param root
     * @return
     * Time complexity: O(n) for visiting every node once
     * Space Complexity: O(n) for recursive stack.
     */
    public List<Integer> preorder(Node root) {
        List<Integer> traversal = new ArrayList<>();
        preorder(root, traversal);
        return traversal;
    }

    private void preorder(Node root, List<Integer> traversal){
        if(root == null)
            return;
        traversal.add(root.val);
        for(Node node: root.children)
            preorder(node,traversal);
        return;
    }


    /**
     * TODO:
     * Approach 3: Iterative preorder
     */
    public List<Integer> iterativePreorder(Node root) {
        return null;
    }

}
