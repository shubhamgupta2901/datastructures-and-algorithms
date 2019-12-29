package playground;

import datastructures.AVLTree;
import datastructures.BinaryTree;

public class AVLTreeMain {

    public static void main(String[] args) {
        AVLTreeMain demo = new AVLTreeMain();
//        demo.testAVLTreeInsertion();
        demo.testAVLTreeDeletion();
    }

    private void testAVLTreeInsertion(){
        int [] ll_values = new int[]{13,10,15,5,11,16,4,8,3};
        int [] rr_values = new int[]{30,5,35,32,40,45};
        int [] lr_values = new int[]{13,10,15,5,11,16,4,6,7};
        int [] rl_values = new int[]{5,2,7,1,4,6,9,3,16,15};

        testAVLTreeInsertion(rl_values);

    }

    private void testAVLTreeInsertion(int [] values){
        AVLTree tree = new AVLTree();
        for(Integer value: values){
            System.out.println("Insert "+ value+" : ");
            tree.insertNode(value);
            BinaryTreePrinter.print(tree.getRoot());
        }
    }

    private void testAVLTreeDeletion(){
        int [] ll_values = new int[]{13,10,15,5,11,16,4,8,3};
        AVLTree tree = new AVLTree();
        for(int value: ll_values){
            tree.insertNode(value);
        }
        BinaryTreePrinter.print(tree.getRoot());
        System.out.println("Deleting 16:");
        tree.deleteNode(16);
        BinaryTreePrinter.print(tree.getRoot());
        System.out.println("Deleting 15:");
        tree.deleteNode(15);
        BinaryTreePrinter.print(tree.getRoot());
        System.out.println("Deleting 3:");
        tree.deleteNode(3);
        BinaryTreePrinter.print(tree.getRoot());
    }



}
