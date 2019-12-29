package datastructures;

import datastructures.interfaces.IAVLTree;

/**
 * AVL tree is a self-balancing Binary Search Tree (BST) where the difference between heights of left and right subtrees cannot be more than one for all nodes.
 * Why AVL Trees? Most of the BST operations (e.g., search, max, min, insert, delete.. etc) take O(h) time where h is the height of the BST.
 * The cost of these operations may become O(n) for a skewed Binary tree.
 * If we make sure that height of the tree remains logn after every insertion and deletion, then we can guarantee an upper bound of O(logn) for all these operations.
 * For an AVL Tree h = 1.44log(n)
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

    /**
     * Insertion in AVL Tree is similar to BST, except we need to balance the tree after insertion to ensure the tree is
     * AVL after insertion.
     * We take help of left and right rotation functions for rebalancing.
     * @param value
     * @return
     * Time complexity: O(logn) since insertion takes O(logn) and rotate functions are O(1)
     * Space Complexity: O(logn) for using recursive stack.
     */
    @Override
    public AVLTreeNode insertNode(int value) {
        root = insertNode(root, value);
        return root;
    }

    private AVLTreeNode insertNode(AVLTreeNode root, int value){
        //Insert node to tree
        if(root == null)
            return new AVLTreeNode(value);
        //Duplicate keys are not allowed. This would simplify finding out which rotation to perform.
        if(root.data> value)
            root.left = insertNode(root.left, value);
        else if(root.data<value)
            root.right = insertNode(root.right,value);
        else return root;

        //Update height of node
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        //Balance the tree
        int balance = balance(root);

        //Left-Left case: Right Rotation
        if(balance >1 && root.left.data >value)
            root = rightRotate(root);

        //Left-Right case: LR rotation
        if(balance>1 && root.left.data < value){
            root.left = leftRotate(root.left);
            root = rightRotate(root);
        }

        //Right-Right Case: Left Rotation
        if(balance <-1 && root.right.data < value){
            root = leftRotate(root);
        }

        //Right-Left Case: RL rotation
        if(balance<-1 && root.right.data>value){
            root.right = rightRotate(root.right);
            root = leftRotate(root);
        }

        return root;

    }

    /**
     * AVL deletion is similar to BST deletion, except after deleting node we need to perform some re-balancing to ensure the
     * tree does not violate AVL property.
     * Observation: In AVL insertion, we looked at the first node in [inserted node-> root] path which violates the AVL property.
     * Once we balanced this node, all the other nodes in path automatically become balanced. Unlike insertion, during deletion
     * after balancing this first node its ancestor may still be unbalanced and we may have to balance its ancestors as well.
     * Algo:
     * 1) Perform the normal BST deletion.
     * 2) The current node must be one of the ancestors of the deleted node. Update the height of the current node.
     * 3) Get the balance factor (left subtree height – right subtree height) of the current node.
     * 4) If balance factor is greater than 1, then the current node is unbalanced and we are either in Left Left case or Left Right case.
     *    To check whether it is Left Left case or Left Right case, get the balance factor of left subtree.
     *    If balance factor of the left subtree is greater than or equal to 0, then it is Left Left case, else Left Right case.
     * 5) If balance factor is less than -1, then the current node is unbalanced and we are either in Right Right case or Right Left case.
     *    To check whether it is Right Right case or Right Left case, get the balance factor of right subtree.
     *    If the balance factor of the right subtree is smaller than or equal to 0, then it is Right Right case, else Right Left case.
     * @param value
     * @return root of the tree
     * Time Complexity: O(logn)
     * Space Complexity: O(logn) for using recursive stack.
     */
    @Override
    public AVLTreeNode deleteNode(int value) {
        root = deleteNode(root,value);
        return root;
    }

    private AVLTreeNode deleteNode(AVLTreeNode root, int value){
        if(root == null)
            return null;
        if(root.data > value)
            root.left = deleteNode(root.left,value);
        else if(root.data< value)
            root.right = deleteNode(root.right,value);
        else{
            if(root.left == null)
                root = root.right;
            else if(root.right ==  null)
                root = root.left;
            else{
                root.data = inOrderSuccessor(root).data;
                root.right = deleteNode(root.right, root.data);
            }
        }

        if(root == null)
            return root;

        //update height
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        //check balance
        int balance = balance(root);

        //balance AVL
        if(balance>1){
            //Left-Left Case: Right rotate
            if(balance(root.left)>=0)
                root = rightRotate(root);
            //Left-Right Case: LR rotate
            else{
                root.left = leftRotate(root.left);
                root = rightRotate(root);
            }
        }
        else if(balance<-1){
            //Right-Right Case: Left Rotate
            if(balance(root.left)<=0)
                root = leftRotate(root);
            //Right Left Case: RL Rotate
            else {
                root.right = rightRotate(root.right);
                root = leftRotate(root);
            }
        }

        return root;
    }

    private AVLTreeNode inOrderSuccessor(AVLTreeNode node){
        AVLTreeNode ptr = node.right;
        while (ptr.left!=null)
            ptr = ptr.left;
        return ptr;
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
        Y.left = Z;
        Z.right = T2;

        //update heights
        Z.height = Math.max(height(Z.left), height(Z.right)) +1;
        Y.height = Math.max(height(Y.left), height(Y.right)) + 1;
        return Y;
    }

    private int balance(AVLTreeNode node){
        if(node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

}
