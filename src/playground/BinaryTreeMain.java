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
        for(int i = 0; i<7; i++)
            tree.insertNode(i+1);

        List<Integer> traversal;
        System.out.println("Preorder Traversal:");
        traversal= tree.preOrderTraversal();
        Utils.printList(traversal);
        traversal = tree.recursivePreorderTraversal();
        Utils.printList(traversal);
        System.out.println("----------");

        System.out.println("Inorder Traversal:");
        traversal= tree.inOrderTraversal();
        Utils.printList(traversal);
        traversal= tree.recursiveInorderTraversal();
        Utils.printList(traversal);
        System.out.println("----------");

        System.out.println("Level order Traversal:");
        traversal= tree.levelOrderTraversal();
        Utils.printList(traversal);
        System.out.println("----------");

        System.out.println("Search:");
        boolean found = tree.searchNode(4) != null ? true :false;
        System.out.println("found 4:"+found);
        found = tree.searchNode(7) != null ? true :false;
        System.out.println("found 7:"+found);
        found = tree.searchNode(9) != null ? true :false;
        System.out.println("found 9:"+found);
        System.out.println("----------");

        for(int i = 0; i<7; i++){
            System.out.println("Deleting "+ (i+1));
            tree.deleteNode(i+1);
            traversal= tree.levelOrderTraversal();
            Utils.printList(traversal);
        }
        System.out.println("----------");

    }
}
