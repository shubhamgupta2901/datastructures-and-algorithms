package playground;

import ds.BinarySearchTree;
import ds.interfaces.IBinaryTree;


import java.util.List;

public class BinarySearchTreeMain {
    public static void main(String[] args) {
        BinarySearchTreeMain demo = new BinarySearchTreeMain();
        demo.testBinarySearchTree();
    }

    public void testBinarySearchTree(){
        BinarySearchTree tree = new BinarySearchTree();
        int[] values = new int[]{100,50,150,25,75,125,175,12,37};
        for(Integer value : values){
            //tree.insertNode(value);
            tree.recursivelyInsertNode(value);
        }

        System.out.println("Is valid BST: "+ tree.validateBST(tree.getRoot()));
        System.out.println("Inorder traversal");
        List<Integer> traversal = tree.inOrderTraversal();
        Utils.printList(traversal);

        System.out.println("Level order traversal");
        traversal = tree.levelOrderTraversal();
        Utils.printList(traversal);
        System.out.println("----------");


        System.out.println("Search:");
        boolean found = tree.searchNode(37) != null ? true :false;
        System.out.println("found 37:"+found);
        found = tree.recursiveSearchNode(tree.getRoot(),37) != null ? true :false;
        System.out.println("found 37(recursively):"+found);
        found = tree.searchNode(125) != null ? true :false;
        System.out.println("found 125:"+found);
        found = tree.recursiveSearchNode(tree.getRoot(),125) != null ? true :false;
        System.out.println("found 125(recursively):"+found);
        found = tree.searchNode(80) != null ? true :false;
        System.out.println("found 80:"+found);
        found = tree.recursiveSearchNode(tree.getRoot(),80) != null ? true :false;
        System.out.println("found 80(recursively):"+found);
        System.out.println("----------");

        System.out.println("Find min and max:");
        System.out.println("Find min (iteratively): " + tree.findMin(tree.getRoot()));
        System.out.println("Find min (recursively): " + tree.recursiveFindMin(tree.getRoot()));
        System.out.println("Find max (iteratively): " + tree.findMax(tree.getRoot()));
        System.out.println("Find max (recursively): " + tree.recursiveFindMax(tree.getRoot()));
        System.out.println("----------");


        System.out.println("Inorder successor");
        for(Integer value: values){
            IBinaryTree.TreeNode node = tree.searchNode(value);
            IBinaryTree.TreeNode successor = tree.inOrderSuccessor(tree.getRoot(),node);
            int successorData = successor != null ? successor.data : Integer.MIN_VALUE;
            System.out.println("Inorder successor of "+ value + ": "+ successorData);
        }

        System.out.println("----------");
        System.out.println("Inorder predecessor");
        for(Integer value: values){
            IBinaryTree.TreeNode node = tree.searchNode(value);
            IBinaryTree.TreeNode predecessor = tree.inOrderPredecessor(tree.getRoot(),node);
            int predecessorData = predecessor != null ? predecessor.data : Integer.MIN_VALUE;
            System.out.println("Inorder predecessor of "+ value + ": "+ predecessorData);
        }
        System.out.println("----------");

    }
}
