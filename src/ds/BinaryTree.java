package ds;


import ds.interfaces.IBinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * A tree is a data structure similar to linked list, but instead of each node pointing simply to to the next node in a linear fashion,
 * each node points to a number of nodes. A tree is a binary tree if each node has zero, one or two child.
 * Using following tree for
 * ..........1
 * ......./    \
 * .....2       3
 * ..../ \     / \
 * ...4   5   6   7
 */
public class BinaryTree implements IBinaryTree {

    TreeNode root;

    public BinaryTree() {
        root = null;
    }

    /**
     * 1,2,4,5,3,6,7
     * Preorder Traversal of Binary Tree is defined as: (Root-Left-Right)
     *  1.Visit the root
     *  2.Traverse left subtree in preorder
     *  3.Traverse right subtree in preorder
     * @return traversal list
     * Time Complexity: O(n) [Since we are traversing each node once]
     * Space Complexity: O(n) [Using a stack]
     */
    @Override
    public List<Integer> preOrderTraversal() {
        List<Integer> traversal = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(!stack.isEmpty() || curr!=null){
            while(curr!=null){
                stack.push(curr);
                traversal.add(curr.data);
                curr = curr.left;
            }

            if(!stack.isEmpty()){
                curr = stack.pop();
                curr = curr.right;
            }
        }
        return traversal;
    }

    /**
     * 4,2,5,1,6,3,7
     * Inorder Traversal of Binary Tree is defined as: (Left-Root-Right)
     *  1.Traverse left subtree in inorder
     *  2.Visit the root
     *  3.Traverse right subtree in inorder
     * @return traversal list
     * Time Complexity: O(n) [Since we are traversing each node once]
     * Space Complexity: O(n) [Using a stack]
     */
    @Override
    public List<Integer> inOrderTraversal(){
        List<Integer> traversal = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(!stack.isEmpty() || curr !=null){

            while(curr!=null){
                stack.push(curr);
                curr = curr.left;
            }

            if(!stack.isEmpty()){
                curr = stack.pop();
                traversal.add(curr.data);
                curr = curr.right;
            }
        }
        return traversal;

    }

    /**
     * TODO: non-iterative post order traversal.
     * @return
     */
    @Override
    public List<Integer> postOrderTraversal(){
        return null;
    }

    /**
     * Level order traversal of above tree in comment would look like:
     * 1,2,3,4,5,6,7
     * @return traversal list
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public List<Integer> levelOrderTraversal(){
        List<Integer> traversal = new ArrayList<>();
        if(root == null)
            return traversal;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode curr = queue.remove();
            traversal.add(curr.data);
            if(curr.left!=null)
                queue.add(curr.left);
            if(curr.right != null)
                queue.add(curr.right);
        }
        return traversal;
    }

    /**
     * Search a node with a given value
     * Using level order traversal here
     * @param value value of node to be searched
     * @return searched node
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    @Override
    public TreeNode searchNode(int value) {
        if(root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode curr = queue.remove();
            if(curr.data == value)
                return curr;
            if(curr.left!=null)
                queue.add(curr.left);
            if(curr.right!=null)
                queue.add(curr.right);
        }
        return null;
    }

    /**
     * Easiest way to delete a node in binary tree is find the node that needs to be deleted,
     * and find the deepest node in binary tree (last node in level order traversal)
     * swap the value of node with value of deepest node and delete the deepest node.
     * @param value value of node to be deleted
     * @return root of the tree
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    @Override
    public TreeNode deleteNode(int value) {
        if(root==null)
            return null;
        TreeNode lastParent =null, searchNode =null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode curr = queue.remove();
            //Get node to be deleted
            if(curr.data == value)
                searchNode = curr;
            //Get possible parent of deepest node
            if(curr.left!=null || curr.right!=null)
                lastParent = curr;
            if(curr.left!=null)
                queue.add(curr.left);
            if(curr.right!=null)
                queue.add(curr.right);
        }

        //Node to be deleted not found
        if(searchNode == null)
            return root;

        //root is only node in tree
        if(lastParent == null){
            //Only node in tree needs to be deleted
            if(root.data == value){
                root = null;
                return null;
            }
            //There was only node in tree and it does not need to be deleted
            return root;
        }

        TreeNode deepestNode = lastParent.right !=null ? lastParent.right : lastParent.left;
        searchNode.data = deepestNode.data;
        if(lastParent.right!=null)
            lastParent.right = null;
        else lastParent.left = null;
        return root;

    }

    /**
     * Insert a node at first vacant place
     * Will perform a level order traversal and find the first node which does not have a left or right child,
     * here we can insert our node.
     * @param value
     * @return root of the tree.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    @Override
    public TreeNode insertNode(int value) {
        TreeNode node = new TreeNode(value);
        if(root ==null){
            root = node;
            return root;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(true){
            TreeNode curr = queue.remove();
            if(curr.left == null){
                curr.left = node;
                return root;
            }else if (curr.right == null){
                curr.right = node;
                return root;
            }else{
                queue.add(curr.left);
                queue.add(curr.right);
            }
        }

    }

    public List<Integer> recursivePreorderTraversal(){
        List<Integer> traversal = new ArrayList<>();
        traversal = recursivePreOrderTraversalHelper(root,traversal);
        return traversal;

    }

    private List<Integer> recursivePreOrderTraversalHelper(TreeNode root, List<Integer> traversal){
        if(root== null)
            return traversal;
        traversal.add(root.data);
        recursivePreOrderTraversalHelper(root.left,traversal);
        recursivePreOrderTraversalHelper(root.right, traversal);
        return traversal;
    }

    public List<Integer> recursiveInorderTraversal(){
        List<Integer> traversal = new ArrayList<>();
        traversal = recursiveInOrderTraversalHelper(root,traversal);
        return traversal;

    }

    private List<Integer> recursiveInOrderTraversalHelper(TreeNode root, List<Integer> traversal){
        if(root== null)
            return traversal;
        recursiveInOrderTraversalHelper(root.left,traversal);
        traversal.add(root.data);
        recursiveInOrderTraversalHelper(root.right, traversal);
        return traversal;
    }

}
