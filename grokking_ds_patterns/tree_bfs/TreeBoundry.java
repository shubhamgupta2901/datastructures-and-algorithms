package grokking_ds_patterns.tree_bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return an array containing all the boundary nodes of the tree in an anti-clockwise direction.
 *
 * The boundary of a tree contains all nodes in the left view, all leaves, and all nodes in the right view.
 * Please note that there should not be any duplicate nodes. For example, the root is only included in the left view;
 * similarly, if a level has only one node we should include it in the left view.
 * Example:
 *
 *                         A
 *                     /       \
 *                    B         C
 *                  /  \      /  \
 *                 D   e     f   G
 *                / \  / \  / \  / \
 *               H  I  J K  L M  N O
 *  Boundary View: [A,B,D,H,I,J,K,L,M,N,O,G,C]
 *
 *          a
 *          \
 *          b
 *         / \
 *        c   d
 * Boundry View: [a,b,c,d]
 * Please note that whenever we've only one node on a level, we include it in the left view.
 *
 */
public class TreeBoundry {

    /**
     * We use BFS traversal and while traversing different levels, we will populate three arrays:
     *
     * Tree Left View - contains the first node of each level
     * Leaves - contains all leaf nodes.
     * Tree Right View - contains the last node of each level
     *
     * Finally, we will merge these three arrays to calculate the tree boundary.
     * To find the leaves in the left to right order we can’t use BFS because any leaf node coming in a higher level
     * will not necessarily come before a leaf on the lower level.
     * To handle this scenario we will be using Depth First Search (DFS).
     *
     * Time Complexity: O(n) for bfs and dfs
     * Space compelxity: O(n) for list, queue and recursive stack.
     */
    public List<TreeNode> findBoundary(TreeNode root) {
        List<TreeNode>result = new ArrayList<>();
        if(root == null)
            return result;

        List<TreeNode> leftNodes = new ArrayList<>();
        List<TreeNode> rightNodes = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode node;

        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i<size; i++){
                node = queue.remove();
                if(!(node.left == null && node.right ==null)){
                    if(i == 0)
                        leftNodes.add(node);
                    else if(i == size-1)
                        rightNodes.add(0,node);
                }

                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }
        }

        List<TreeNode> leafNodes = new ArrayList<>();
        dfs(root,leafNodes);

        result.addAll(leftNodes);
        result.addAll(leafNodes);
        result.addAll(rightNodes);
        return result;
    }

    private void dfs(TreeNode root, List<TreeNode> leafNodes){
        if(root == null)
            return;
        if(root.left == null && root.right == null)
            leafNodes.add(root);
        dfs(root.left, leafNodes);
        dfs(root.right, leafNodes);
        return;
    }
}
