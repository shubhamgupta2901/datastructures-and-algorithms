package ds.interfaces;

public interface IBinarySearchTree extends IBinaryTree{

    int findMin();

    int findMax();

    TreeNode inOrderSuccessor(TreeNode root, TreeNode node);

}
