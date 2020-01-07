package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/"/>
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * Using two stacks left and right to maintain the order of traversed nodes.
     * In one level we will traverse from left to right and keep track of nodes in leftStack.
     * In next level we will traverse from right to left and keep track of nodes in rightStack.
     * @param root
     * @return
     * Time Complexity: O(n)
     * Space Complexity: O(n) Although using two stacks but we need to store only n elements.
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> traversal = new ArrayList<>();
        if(root == null)
            return traversal;
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();
        rightStack.push(root);
        boolean isLeft = true;
        while (!leftStack.isEmpty() || !rightStack.isEmpty()){
            List<Integer> level = new ArrayList<>();
            TreeNode curr;
            if(isLeft){
                while (!rightStack.isEmpty()){
                    curr = rightStack.pop();
                    level.add(curr.val);
                    if(curr.left != null)
                        leftStack.push(curr.left);
                    if(curr.right != null)
                        leftStack.push(curr.right);
                }
            }else {
                while (!leftStack.isEmpty()){
                    curr = leftStack.pop();
                    level.add(curr.val);
                    if(curr.right != null)
                        rightStack.push(curr.right);
                    if(curr.left != null)
                        rightStack.push(curr.left);
                }
            }
            isLeft = !isLeft;
            traversal.add(level);
        }
        return traversal;
    }
}
