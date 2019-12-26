package datastructures;

import datastructures.interfaces.IAVLTree;

import java.util.List;

/**
 * AVL tree is a self-balancing Binary Search Tree (BST) where the difference between heights of left and right subtrees cannot be more than one for all nodes.
 * Why AVL Trees? Most of the BST operations (e.g., search, max, min, insert, delete.. etc) take O(h) time where h is the height of the BST.
 * The cost of these operations may become O(n) for a skewed Binary tree.
 * If we make sure that height of the tree remains O(logn) after every insertion and deletion, then we can guarantee an upper bound of O(logn) for all these operations.
 * The height of an AVL tree is always O(logn) where n is the number of nodes in the tree.
 *
 * So all we are trying to do is balance a Binary Search Tree in such a way that after every insertion/deletion, the heights of left and right subtrees for every node
 * can not be more than 1. This will keep the height of BST ~O(logn).
 * All other operations remain similar to Binary Search Tree with improved time complexity O(logn). Refer {@link BinarySearchTree}
 */
public class AVLTree implements IAVLTree {

    private AVLTreeNode root;

    public AVLTree() {
        root = null;
    }

    @Override
    public AVLTreeNode getRoot() {
        return root;
    }

    /**
     * Since we have augmented the BST and keep track of height of each node, this operation can be done in constant time
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * @return
     */
    @Override
    public int height() {
        return root.height;
    }

    @Override
    public boolean validateAVL(AVLTreeNode root) {
        return false;
    }

    @Override
    public AVLTreeNode insertNode(int value) {
        return null;
    }


    @Override
    public AVLTreeNode deleteNode(int value) {
        return null;
    }

}
