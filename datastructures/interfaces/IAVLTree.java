package datastructures;



import java.util.List;

/**
 *
 */
public interface AVLTree {


    class AVLTreeNode {
        public int data;
        public AVLTreeNode left;
        public AVLTreeNode right;

        public AVLTreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    AVLTreeNode getRoot();

    List<Integer> preOrderTraversal();

    List<Integer> inOrderTraversal();

    List<Integer> postOrderTraversal();

    List<Integer> levelOrderTraversal();

    AVLTreeNode searchNode(int value);

    AVLTreeNode deleteNode(int value);

    AVLTreeNode insertNode(int value);

    int height();

    int size();
}
