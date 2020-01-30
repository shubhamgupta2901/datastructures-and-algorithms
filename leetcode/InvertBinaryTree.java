package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * Recursive approach
     * Time Complexity: O(n)
     * Space Complexity: Because of recursion, O(h) function calls will be placed on the stack in the worst case,
     * where h is the height of the tree.
     * Because h can be n in worst case, the space complexity is O(n).
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;
        TreeNode leftInvertedSubtree = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = leftInvertedSubtree;
        return root;
    }

    /**
     * Approach 2: iterative approach
     * Following a breadth first approach
     * Time complexity: O(n)
     * Space Complexity: O(n)
     */
    public TreeNode invertTreeIterative(TreeNode root){
        if(root == null)
            return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.remove();
            TreeNode leftSubtree = node.left;
            node.left = node.right;
            node.right = leftSubtree;
            if(node.left!=null)
                queue.add(node.left);
            if(node.right!=null)
                queue.add(node.right);

        }
        return root;
    }

}
