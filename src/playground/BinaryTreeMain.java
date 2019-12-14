package playground;

import ds.BinaryTree;

import java.util.List;

public class BinaryTreeMain {
    public static void main(String[] args) {
        BinaryTreeMain demo = new BinaryTreeMain();
        demo.testBinaryTree();

    }

    private void testBinaryTree(){
        BinaryTree tree = new BinaryTree();
        tree.createTestTree();
        List<Integer> traversal;
        traversal= tree.preorderTraversal();
        Utils.printList(traversal);
        traversal = tree.recursivePreorderTraversal();
        Utils.printList(traversal);
        System.out.println("----------");
        traversal= tree.inorderTraversal();
        Utils.printList(traversal);
        traversal= tree.recursiveInorderTraversal();
        Utils.printList(traversal);
        System.out.println("----------");
    }
}
