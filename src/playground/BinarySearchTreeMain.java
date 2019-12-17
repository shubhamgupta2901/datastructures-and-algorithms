package playground;

import ds.BinarySearchTree;


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
        found = tree.recursivelySearchNode(tree.getRoot(),37) != null ? true :false;
        System.out.println("found 37(recursively):"+found);
        found = tree.searchNode(125) != null ? true :false;
        System.out.println("found 125:"+found);
        found = tree.recursivelySearchNode(tree.getRoot(),125) != null ? true :false;
        System.out.println("found 125(recursively):"+found);
        found = tree.searchNode(80) != null ? true :false;
        System.out.println("found 80:"+found);
        found = tree.recursivelySearchNode(tree.getRoot(),80) != null ? true :false;
        System.out.println("found 80(recursively):"+found);
        System.out.println("----------");



    }
}
