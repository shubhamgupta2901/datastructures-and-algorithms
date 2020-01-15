package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
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
     * Approach 3: Iterative preorder
     * Attempting something very similar to dfs.
     * Performs really badly though time complexity wise.
     */
    public List<Integer> iterativePreorder1(Node root) {
        List<Integer> traversal = new ArrayList<>();
        if(root== null)
            return traversal;
        HashSet<Node> visitedNodes = new HashSet<>();;
        Stack<Node> stack = new Stack();

        stack.push(root);
        traversal.add(root.val);

        while(!stack.isEmpty()){
            Node node = stack.peek();
            int i;
            for(i = 0; i<node.children.size(); i++){
                Node child = node.children.get(i);
                if(!visitedNodes.contains(child)){
                    stack.push(child);
                    traversal.add(child.val);
                    visitedNodes.add(child);
                    break;
                }
            }

            if(i == node.children.size())
                stack.pop();
        }

        return traversal;
    }


    /**
     * Approach 4: A better and clean dfs.
     * Since unlike graphs, trees don't have cycles we don't need
     * @param root
     * @return
     * Time Complexity: O(n)
     * Space Complexity: O(n) for using stack.
     */
    public List<Integer> iterativePreorder(Node root){
        List<Integer> traversal = new ArrayList<>();
        if(root == null)
            return traversal;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            traversal.add(node.val);
            for(int i = node.children.size()-1; i>=0; i--)
                stack.push(node.children.get(i));
        }
        return traversal;
    }

}
