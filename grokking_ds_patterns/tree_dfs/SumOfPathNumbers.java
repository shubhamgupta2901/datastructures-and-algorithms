package grokking_ds_patterns.tree_dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree where each node can only have a digit (0-9) value, each root-to-leaf path will
 * represent a number. Find the total sum of all the numbers represented by all paths.
 * Example:
 *     3
 *    / \
 *   9  2
 *     / \
 *    1  7
 *  Sum = 39 + 321 + 327 = 687
 */
public class SumOfPathNumbers {
    /**
     * Approach 1 : Aggregate all the numbers represented by root - leaf paths, and add them
     * Time Complexity: O(n)
     * Space Complexity: O(n) For recursive stack : O(h) where h is height
     * Additionally, Array which contains a number corresponding to every root-leaf path.
     * For a binary tree with height h, Max number of leaf nodes could be (2^h-1). So Space Complexity would
     * be O(2^h) ~ O(n)
     */
    public static int findSumOfPathNumbers1(TreeNode root) {
        if(root == null)
            return 0;
        //Aggregate numbers in root-leaf paths: [101,116,115]
        List<Integer> numbers = new ArrayList<>();
        helper(root,numbers,0);
        //Add all the numbers
        int sum = 0;
        for(int i = 0; i<numbers.size(); i++)
            sum+= numbers.get(i);
        //return numbers
        return sum;

    }

    private static void helper(TreeNode root, List<Integer> numbers, int val){
        //base case: leaf node
        val = val*10 + root.val;
        if(root.left == null && root.right == null){
            numbers.add(val);
        }
        //recursion:
        if(root.left!=null)
            helper(root.left, numbers, val);
        if(root.right!=null)
            helper(root.right, numbers,val);
        //return statment
        return;
    }

    /**
     * Approach 2: Without aggregating the numbers, sum can be obtained, by returning a number when we encounter a leaf
     * node, and add all of them.
     * Time Complexity: O(n)
     * Space Complexity: O(h) for recursive stack.
     */
    public static int findSumOfPathNumbers2(TreeNode root) {
        return helper(root, 0);
    }

    private static int helper(TreeNode root, int val){
        if(root == null)
            return 0;
        val = val*10 + root.val;
        if(root.left == null && root.right == null)
            return val;
        return helper(root.left, val) + helper(root.right, val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);
        System.out.println("Total Sum of Path Numbers: " + SumOfPathNumbers.findSumOfPathNumbers1(root));
    }
}
