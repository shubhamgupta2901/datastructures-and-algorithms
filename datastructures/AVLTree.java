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
     * @param root of the tree
     * @return
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int height(AVLTreeNode root) {
        if(root == null)
            return -1;
        return root.height;
    }

    /**
     * Takes the root of the Binary Tree as input and validates weather it is an AVL Tree or not.
     * Note if the input is root of a Binary Search Tree, all we need to check is the height of nodes.
     * For a Binary Tree we will need to validate the data as well as the height of every node.
     * @param root
     * @return
     * Time Complexity: O(n)
     * Space Complexity: O(n) For using a recursive stack.
     */
    @Override
    public boolean validateAVL(AVLTreeNode root) {
        return validateAVL(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean validateAVL(AVLTreeNode root, int minValue, int maxValue){
        if(root == null)
            return true;
        boolean bstCondition = root.data >= minValue && root.data <= maxValue;
        boolean avlCondition = Math.abs(height(root.left) -height(root.right)) <= 1 ;
        if(bstCondition && avlCondition)
            return validateAVL(root.left,minValue, root.data) && validateAVL(root.right, root.data, maxValue);
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

    /**
     * Right rotates a tree rooted at Z
     * Right rotation of tree looks like:
     *          Z                                      Y
     *         / \                                   /   \
     *        Y   T4      Right Rotate (Z)          X      Z
     *       / \          - - - - - - - - ->      /  \    /  \
     *      X   T3                               T1  T2  T3  T4
     *     / \
     *   T1   T2
     * where T1, T2, T3 and T4 are subtrees.
     * @return root of the rotated tree
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    private AVLTreeNode rightRotate(AVLTreeNode Z){
        //perform rotation
        AVLTreeNode Y = Z.left;
        Z.left = Y.right;
        Y.right = Z;

        //update heights
        Z.height = Math.max(height(Z.left),height(Z.right)) + 1;
        Y.height = Math.max(height(Y.left), height(Y.right)) + 1;
        return Y;
    }

    /**
     * Left Rotates the tree rooted at Z.
     * Left Rotation looks like:
     *    Z                               Y
     *  /  \                            /   \
     * T1   Y     Left Rotate(Z)       Z      X
     *     /  \   - - - - - - - ->    / \    / \
     *    T2   X                     T1  T2 T3  T4
     *        / \
     *      T3  T4
     * where T1, T2, T3 and T4 are subtrees.
     * @return root of the rotated tree
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    private AVLTreeNode leftRotate(AVLTreeNode Z){
        //perform rotation
        AVLTreeNode Y = Z.right;
        AVLTreeNode T2 = Y.left;
        Y.right = Z;
        Z.right = T2;

        //update heights
        Z.height = Math.max(height(Z.left), height(Z.right)) +1;
        Y.height = Math.max(height(Y.left), height(Y.right)) + 1;
        return Y;
    }

}
