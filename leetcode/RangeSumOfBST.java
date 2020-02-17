package leetcode;

import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/range-sum-of-bst/"/>
 * Given the root node of a binary search tree, return the sum of values of all nodes with
 * value between L and R (inclusive).
 * The binary search tree is guaranteed to have unique values.
 *
 * Example 1:
 * Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
 * Output: 32
 *
 * Example 2:
 * Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * Output: 23
 *
 * Note:
 * The number of nodes in the tree is at most 10000.
 * The final answer is guaranteed to be less than 2^31.
 */
public class RangeSumOfBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * Approach 1: DFS: Recursive Implementation
     * Time Complexity: O(n) where n in number of nodes
     * Space Complexity: O(h) for recursive stack where h is the height of tree.
     */
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null)
            return 0;
        if(root.val<L)
            return rangeSumBST(root.right, L, R);
        if(root.val>R)
            return rangeSumBST(root.left, L, R);
        return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
    }

    /**
     * Approach 2: Iterative Approach
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */
    public int rangeSumBSTApproach2(TreeNode root, int L, int R) {
        if(root == null)
            return 0;
        Stack<TreeNode> stack = new Stack<>();
        int rangeSum = 0;

        TreeNode ptr = root;
        while(ptr!=null || !stack.isEmpty()){
            while(ptr!=null){
                stack.push(ptr);
                ptr = ptr.left;
            }

            ptr = stack.pop();
            if(ptr.val>=L && ptr.val <= R)
                rangeSum+= ptr.val;
            ptr = ptr.right;
        }
        return rangeSum;
    }
}
