package grokking_ds_patterns.tree_dfs;

import java.util.ArrayList;
import java.util.List;
/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 *
 * Return:
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class AllPathsForASum {
    /**
     * Approach 1: Create a new List for every path - Accepted
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> paths = new ArrayList<>();
        if(root == null)
            return paths;
        List<Integer> path = new ArrayList<>();
        helper(root, paths, path, sum);
        return paths;
    }

    private void helper(TreeNode root, List<List<Integer>> paths, List<Integer> path , int sum){
        //base cases
        if(root == null)
            return;
        if(root.left == null && root.right == null && root.val == sum){
            //add root to path, add path to paths
            path.add(root.val);
            paths.add(path);
            return;
        }
        //recursion
        path.add(root.val);
        helper(root.left, paths, new ArrayList<Integer>(path), sum-root.val);
        helper(root.right, paths, new ArrayList<Integer>(path), sum - root.val);

        //return statement
        return;
    }
}
