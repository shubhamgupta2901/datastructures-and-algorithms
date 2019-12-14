package ds;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree {

     class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    TreeNode root;

    public BinaryTree() {
        root = null;
    }

    /**
     * Creating a binary tree for testing purposes
     * ..........1
     * ......./    \
     * .....2       3
     * ..../ \     / \
     * ...4   5   6   7
     * @return
     */
    public TreeNode createTestTree(){
        TreeNode[] nodes = new TreeNode[7];
        for(int i = 0; i<nodes.length; i++)
            nodes[i] = new TreeNode(i+1);
        root = nodes[0];

        nodes[0].left = nodes[1];
        nodes[0].right = nodes[2];

        nodes[1].left = nodes[3];
        nodes[1].right = nodes[4];

        nodes[2].left = nodes[5];
        nodes[2].right = nodes[6];
        return root;
    }

    /**
     * Preorder Traversal of Binary Tree is defined as: (Root-Left-Right)
     *  1.Visit the root
     *  2.Traverse left subtree in preorder
     *  3.Traverse right subtree in preorder
     * @return traversal list
     * Time Complexity: O(n) [Since we are traversing each node once]
     * Space Complexity: O(n) [Using a stack]
     */
    public List<Integer> preorderTraversal (){
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
     * Inorder Traversal of Binary Tree is defined as: (Left-Root-Right)
     *  1.Traverse left subtree in inorder
     *  2.Visit the root
     *  3.Traverse right subtree in inorder
     * @return traversal list
     * Time Complexity: O(n) [Since we are traversing each node once]
     * Space Complexity: O(n) [Using a stack]
     */
    public List<Integer> inorderTraversal(){
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
