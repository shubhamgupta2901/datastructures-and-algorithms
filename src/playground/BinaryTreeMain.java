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
        List<Integer> list = tree.preorderTraversal();
        Utils.printList(list);
    }
}
