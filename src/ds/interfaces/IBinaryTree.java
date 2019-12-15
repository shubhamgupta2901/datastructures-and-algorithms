package ds.interfaces;

import java.util.List;

public interface IBinaryTree {
    class TreeNode {
        public int data;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    TreeNode getRoot();

    List<Integer> preOrderTraversal();

    List<Integer> inOrderTraversal();

    List<Integer> postOrderTraversal();

    List<Integer> levelOrderTraversal();

    TreeNode searchNode(int value);

    TreeNode deleteNode(int value);

    TreeNode insertNode(int value);

    int height();

    int size();
}
