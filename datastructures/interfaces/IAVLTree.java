package datastructures.interfaces;



import java.util.List;

/**
 *
 */
public interface IAVLTree {

    class AVLTreeNode {
        public int data, height;
        public AVLTreeNode left;
        public AVLTreeNode right;

        public AVLTreeNode(int data) {
            this.data = data;
            this.height = 0;
            this.left = null;
            this.right = null;
        }
    }

    AVLTreeNode getRoot();

    int height(AVLTreeNode root);

    boolean validateAVL(AVLTreeNode root);

    AVLTreeNode insertNode(int value);

    AVLTreeNode deleteNode(int value);


}
