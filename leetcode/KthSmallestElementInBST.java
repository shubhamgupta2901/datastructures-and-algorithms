package leetcode;
import java.util.*;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 *
 * Example 1:
 *
 * Input: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * Output: 1
 * Example 2:
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * Output: 3
 */
public class KthSmallestElementInBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode ptr = root;
        int i = 0;
        while( ptr!=null || !stack.isEmpty() ){
            while(ptr!=null){
                stack.push(ptr);
                ptr = ptr.left;
            }

            ptr = stack.pop();
            i++;
            if(i == k) return ptr.val;
            else ptr = ptr.right;
        }
        return Integer.MAX_VALUE;
    }
}
