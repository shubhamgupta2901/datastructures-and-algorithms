package leetcode;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> traversal = new ArrayList<>();
        if(root == null)
            return traversal;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode ptr = root;
        while(!stack.isEmpty() || ptr!=null){
            while(ptr!=null){
                stack.push(ptr);
                ptr = ptr.left;
            }

            ptr = stack.pop();
            traversal.add(ptr.val);
            ptr = ptr.right;
        }
        return traversal;
    }

}
