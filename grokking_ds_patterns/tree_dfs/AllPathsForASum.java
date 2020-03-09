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
    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        List<List<Integer>> paths = new ArrayList<>();
        if(root == null)
            return paths;
        List<Integer> path = new ArrayList<>();
        helper1(root, paths, path, sum);
        return paths;
    }

    private void helper1(TreeNode root, List<List<Integer>> paths, List<Integer> path , int sum){
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
        helper1(root.left, paths, new ArrayList<Integer>(path), sum-root.val);
        helper1(root.right, paths, new ArrayList<Integer>(path), sum - root.val);

        //return statement
        return;
    }

    /**
     * Approach 2: Optimizing creating new arraylists - Accepted
     *
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> paths = new ArrayList<>();
        if(root == null)
            return paths;
        List<Integer> path = new ArrayList<>();
        helper2(root, paths, path, sum);
        return paths;
    }

    private void helper2(TreeNode root, List<List<Integer>> paths, List<Integer> path , int sum){
        //base cases
        if(root == null)
            return;
        if(root.left == null && root.right == null && root.val == sum){
            //TODO: create a new list, copy path in it, add to paths.
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(root.val);
            paths.add(newPath);
            return;
        }

        //recursion
        if(root.left != null){
            path.add(root.val);
            helper2(root.left, paths, path, sum-root.val);
            path.remove(path.size()-1);
        }

        if(root.right!=null){
            path.add(root.val);
            helper2(root.right, paths, path, sum-root.val);
            path.remove(path.size()-1);
        }
        //return statement
        return;
    }
}
