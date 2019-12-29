package playground;

import datastructures.interfaces.IBinaryTree;

public class BinaryHeapPrinter {

    public static void print(int[] heap){
        BinaryHeapPrinter printer = new BinaryHeapPrinter();
        IBinaryTree.TreeNode root = printer.insertLevelOrder(heap,null,0);
        BinaryTreePrinter.print(root);

    }

    public IBinaryTree.TreeNode insertLevelOrder(int[] arr, IBinaryTree.TreeNode root, int i) {
        // Base case for recursion
        if (i < arr.length) {
            IBinaryTree.TreeNode temp = new IBinaryTree.TreeNode(arr[i]);
            root = temp;

            // insert left child
            root.left = insertLevelOrder(arr, root.left,
                    2 * i + 1);

            // insert right child
            root.right = insertLevelOrder(arr, root.right,
                    2 * i + 2);
        }
        return root;
    }
}
