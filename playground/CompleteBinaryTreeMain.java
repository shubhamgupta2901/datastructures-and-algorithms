package playground;


import datastructures.CompleteBinaryTree;

import java.util.List;

public class CompleteBinaryTreeMain {

    public static void main(String[] args) {
        CompleteBinaryTreeMain demo = new CompleteBinaryTreeMain();
        demo.testCompleteBinaryTree();
//        demo.testCompleteBinaryTreeHeight();
    }

    private void testCompleteBinaryTree(){

        CompleteBinaryTree tree = new CompleteBinaryTree(10);
        for(int i = 1; i<8; i++)
            tree.insertNode(i);

        List<Integer> traversal;
        System.out.println("Preorder Traversal:");
        traversal= tree.preOrderTraversal();
        Utils.printList(traversal);
        traversal = tree.recursivePreOrderTraversal(tree.getRootIndex());
        Utils.printList(traversal);
        System.out.println("----------");

        System.out.println("Inorder Traversal:");
        traversal= tree.inOrderTraversal();
        Utils.printList(traversal);
        traversal= tree.recursiveInOrderTraversal(tree.getRootIndex());
        Utils.printList(traversal);
        System.out.println("----------");

        System.out.println("Level order Traversal:");
        traversal= tree.levelOrderTraversal();
        Utils.printList(traversal);
        System.out.println("----------");

        System.out.println("Search:");
        boolean found = tree.searchNode(4) != -1 ? true :false;
        System.out.println("found 4:"+found);
        found = tree.searchNode(7) != -1 ? true :false;
        System.out.println("found 7:"+found);
        found = tree.searchNode(9) != -1 ? true :false;
        System.out.println("found 9:"+found);
        System.out.println("----------");

        tree.insertNode(8);
        System.out.println("Height of tree: "+ tree.height());
        System.out.println("Height of tree(recursive): "+ tree.recursiveHeight(tree.getRootIndex()));
        System.out.println("----------");

        System.out.println("Size of tree: "+ tree.size());
        System.out.println("----------");

        for(int i = 0; i<7; i++){
            System.out.println("Deleting "+ (i+1));
            tree.deleteNode(i+1);
            traversal= tree.levelOrderTraversal();
            Utils.printList(traversal);
        }
        System.out.println("----------");
    }

    private void testCompleteBinaryTreeHeight(){
        CompleteBinaryTree tree = new CompleteBinaryTree(20000);
        for(int i = 1; i<=31; i++)
            tree.insertNode(i);
        System.out.println("Height of tree: " + tree.height());
        System.out.println("Height of tree: " + tree.recursiveHeight(tree.getRootIndex()));
    }

}
