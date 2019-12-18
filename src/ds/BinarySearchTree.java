package ds;


import ds.interfaces.IBinarySearchTree;

/**
 * A binary search tree is  a special kind of binary tree where all the left subtree elements should be less than (or equal to ) root data and all the right subtree elements should
 * be greater than (or equal to ) root data. This property should be satisfied at every node in the tree.
 * The main use of Binary Search Tree is Searching, which can be achieved in O(logn), in binary tree it was O(n)
 * <p>
 * The left subtree of a node contains only nodes with keys less than the nodes key.
 * The right subtree of a node contains only nodes with keys greater than the nodes key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * This is a  binary tree
 * ..........7
 * ......./    \
 * .....4       9
 * ..../ \
 * ...2   5
 * <p>
 * This is not a binary tree, since node 2 is part of right subtree of root node its value is smaller than the root node.
 * ..........3
 * ......./    \
 * .....1       6
 * ............/ \
 * ...........2   7
 *
 * Since root subtree is always between left subtree and right subtree data, performing inorder traversal on binary
 * search tree produces a sorted list
 */
public class BinarySearchTree extends BinaryTree implements IBinarySearchTree {

    public BinarySearchTree(){
        super();
    }

    /**
     * @param value value of node to be searched
     * @return searched node
     * Time Complexity: O(h), where h is the height of tree.
     * For a binary tree with n nodes, it will have minimum logn levels,it takes at least O(logn) time.
     * Unfortunately, in worst cases a BST can be a skewed tree where it can degenerate to a linked list, reducing the
     * search time to O(n)
     * Space Complexity: O(1)
     * For recursive implementation see {@link BinarySearchTree#recursiveSearchNode(TreeNode, int)}
     */
    @Override
    public TreeNode searchNode(int value){
        if(root == null)
            return null;
        TreeNode node = root;
        while(node!=null){
            if(node.data == value)
                return node;
            if(value< node.data)
                node = node.left;
            else node = node.right;
        }
        return null;
    }

    @Override
    public TreeNode deleteNode(int value){
        return super.deleteNode(value);
    }

    /**
     * Note that there may exist multiple valid ways for insertion, as long as the tree remains a BST after insertion.
     * For example, given a tree:
     * ..........4
     * ......./    \
     * .....2      7
     * ..../ \
     * ...1   3
     * Inserting 5 in the BST will give:
     * ..........4
     * ......./    \
     * .....2      7
     * ..../ \    /
     * ...1   3  5
     * <p>
     * Another valid tree after insertion of 5 is:
     * ..........5
     * ......./    \
     * .....2      7
     * ..../ \
     * ...1   3
     * .......\
     * .......4
     * @param value value of the node to be inserted
     * @return root of the tree
     * Time Complexity: O(h) where h is height of tree,
     * for a complete binary tree this would be O(log n) where n is the number of nodes in tree
     * Space Complexity: O(1)
     * For recursive implementation see {@link BinarySearchTree#insertNode(TreeNode, int)}
     */
    @Override
    public TreeNode insertNode(int value){
        TreeNode node = new TreeNode(value);

        TreeNode curr = root, parent = null;
        while(curr!=null){
            parent = curr;
            if(curr.data> value)
                curr = curr.left;
            else curr = curr.right;
        }

        //Empty tree
        if(parent == null){
            root = node;
            return  root;
        }

        if(parent.data>value)
            parent.left = node;
        else
            parent.right = node;
        return root;

    }

    /**
     * Minimum value of a bst is the first node traversed in inorder traversal of bst. (the left most node)
     * We keep traversing to the left till we find the right most node
     * @return
     * Time Complexity: O(h) where h is the height of binary search tree
     * Space Complexity: O(1)
     * For recursive implementation see {@link BinarySearchTree#recursiveFindMin(TreeNode)}
     */
    @Override
    public int findMin(){
        if(root == null)
            return Integer.MIN_VALUE;
        TreeNode curr = root;
        while(curr.left != null){
            curr = curr.left;
        }
        return curr.data;
    }

    /**
     * Maximum value of a bst is the last node traversed in inorder traversal of bst. (the right most node)
     * We keep traversing to the right till we find the right most node
     * @return
     * Time Complexity: O(h) where h is the height of binary search tree
     * Space Complexity: O(1)
     * For recursive implementation see {@link BinarySearchTree#recursiveFindMax(TreeNode)}
     */
    @Override
    public int findMax(){
        if(root == null)
            return Integer.MIN_VALUE;
        TreeNode curr = root;
        while(curr.right!=null){
            curr = curr.right;
        }
        return curr.data;
    }


    /**
     * In Binary Tree, Inorder successor of a node is the next node in inorder traversal of the Binary Tree.
     * Inorder Successor is NULL for the last node in inorder traversal.
     * In Binary Search Tree, Inorder Successor of an input node can also be defined as the node with the smallest key
     * greater than the key of input node.
     * For node X, if it has right subtree, then inorder successor of X is minimum value in right subtree.
     * if it does not have right subtree, then inorder successor must be the ancestor of node.
     * Here the idea is start from root and use search like technique - travel down the tree, if a node’s data is greater than root’s data then go right side,
     * otherwise go to left side.
     * <p>
     * ..........20
     * ......./    \
     * .....8       22
     * ..../ \
     * ...4   12
     * ....../  \
     * .....10  14
     * Here inorder successor of 8 is 10, inorder successor of 10 is 12, and inorder successor of 14 is 20.
     * @param root Root of Binary Search Tree.
     * @param node Node whose inorder successor needs to be found.
     * @return Inorder Successor of node.
     * Time complexity O(h), h is height of tree.
     * Space complexity: O(1) if we find out the min value in bst iteratively.
     */
    @Override
    public TreeNode inOrderSuccessor(TreeNode root, TreeNode node) {
        if(root == null || node == null)
            return null;

        if(node.right != null)
            return findMin(node.right);

        TreeNode curr = root,successor = null;
        while(curr!=null){
            if(curr.data>node.data){
                successor = curr;
                curr = curr.left;
            }
            else
                curr = curr.right;
        }
        return successor;
    }


    /**
     *
     * Time complexity : O(h) where h is height of the tree.
     * Space Complexity: O(n) for using recursive stack.
     * @param value value of node to be searched
     * @return searched node
     */
    public TreeNode recursiveSearchNode(TreeNode root, int value){
        if(root == null)
            return null;
        if(root.data == value)
            return root;
        if(value<root.data)
            return recursiveSearchNode(root.left,value);
        return recursiveSearchNode(root.right,value);
    }

    public TreeNode recursivelyInsertNode(int value){
        root = insertNode(root,value);
        return root;
    }
    /**
     * Recursive implementation of insert node in bst.
     * NoteL After inserting the element in subtree, the updated subtree is returned to its parent,
     * as a result complete tree gets updated.
     * @param root the root of tree in which you want to insert the node
     * @param value the value which needs to be inserted
     * @return root of the tree
     * Time complexity: O(h) where h is the height,
     * For complete binary trees O(log n) where n is the number of nodes.
     * Space Complexity: O(h) for using recursive stack.
     */
    private TreeNode insertNode(TreeNode root,int value){
        if(root == null){
            root = new TreeNode(value);
            return root;
        }
        if(root.data> value)
             root.left = insertNode(root.left,value);
        else
            root.right =  insertNode(root.right, value);
        return root;
    }

    /**
     * Maximum value of a bst is the last node traversed in inorder traversal of bst. (the right most node)
     * @param root
     * @return max value in a tree or Integer.MIN_VALUE if empty
     * Time Complexity: O(h) where h is the height of binary search tree
     * Space Complexity: O(n) for using recursive stack.
     */
    public int recursiveFindMax(TreeNode root){
        if(root == null)
            return Integer.MIN_VALUE;
        if(root.right == null)
            return root.data;
        return recursiveFindMax(root.right);
    }

    /**
     * Minimum value of a bst is the first node traversed in inorder traversal of bst. (the left most node)
     * @param root
     * @return min value in a tree or Integer.MAX_VALUE if empty
     * Time Complexity: O(h) where h is the height of binary search tree
     * Space Complexity: O(n) for using recursive stack.
     */
    public int recursiveFindMin(TreeNode root){
        if(root == null)
            return Integer.MAX_VALUE;
        if(root.left == null)
            return root.data;
        return recursiveFindMin(root.left);
    }

    /**
     * Variation of {@link BinarySearchTree#findMin()}
     * @param root root of tree
     * @return node which contains the minimum value
     */
    private TreeNode findMin(TreeNode root){
        if(root == null)
            return null;
        TreeNode curr = root;
        while(curr.left!=null)
            curr = curr.left;
        return curr;
    }

}
