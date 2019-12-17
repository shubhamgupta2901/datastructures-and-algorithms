package ds;


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
public class BinarySearchTree extends BinaryTree{

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
     * For recursive implementation see {@link BinarySearchTree#recursivelySearchNode(TreeNode, int)}
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
     *
     * Time complexity : O(h) where h is height of the tree.
     * Space Complexity: O(n) for using recursive stack.
     * @param value value of node to be searched
     * @return searched node
     */
    public TreeNode recursivelySearchNode(TreeNode root, int value){
        if(root == null)
            return null;
        if(root.data == value)
            return root;
        if(value<root.data)
            return recursivelySearchNode(root.left,value);
        return recursivelySearchNode(root.right,value);
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

}
