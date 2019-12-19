package datastructures.interfaces;

public interface IBinarySearchTree extends IBinaryTree{

    boolean validateBST(TreeNode root);

    int findMin(TreeNode node);

    int findMax(TreeNode node);

    TreeNode inOrderSuccessor(TreeNode root, TreeNode node);

    TreeNode inOrderPredecessor(TreeNode root, TreeNode node);

}
