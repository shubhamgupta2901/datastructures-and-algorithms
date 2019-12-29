package leetcode;

/**
 * https://leetcode.com/problems/delete-node-in-a-bst/
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 */
public class DeleteNodeInBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null)
            return null;
        if(root.val>key)
            root.left = deleteNode(root.left, key);
        else if(root.val< key)
            root.right = deleteNode(root.right, key);
        else{
            if(root.left == null)
                root = root.right;
            else if(root.right == null)
                root = root.left;
            else{
                TreeNode successor = inOrderSuccessor(root);
                root.val = successor.val;
                root.right = deleteNode(root.right, successor.val);
            }
        }
        return root;
    }

    private TreeNode inOrderSuccessor(TreeNode node){
        TreeNode ptr = node.right;
        while(ptr.left !=null)
            ptr = ptr.left;
        return ptr;
    }
}
