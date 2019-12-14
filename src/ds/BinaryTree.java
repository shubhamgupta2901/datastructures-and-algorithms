package ds;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree {

     class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    TreeNode root;

    public BinaryTree() {
        root = null;
    }

    /**
     * Creating a binary tree for testing purposes
     * ..........1
     * ......./    \
     * .....2       3
     * ..../ \     / \
     * ...4   5   6   7
     * @return
     */
    public TreeNode createTestTree(){
        TreeNode[] nodes = new TreeNode[7];
        for(int i = 0; i<nodes.length; i++)
            nodes[i] = new TreeNode(i+1);
        root = nodes[0];

        nodes[0].left = nodes[1];
        nodes[0].right = nodes[2];

        nodes[1].left = nodes[3];
        nodes[1].right = nodes[4];

        nodes[2].left = nodes[5];
        nodes[2].right = nodes[6];
        return root;
    }

    public List<Integer> preorderTraversal (){
        List<Integer> traversal = new ArrayList<>();
        if(root == null)
            return traversal;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(!stack.isEmpty() || curr!=null){
            while(curr!=null){
                stack.push(curr);
                traversal.add(curr.data);
                curr = curr.left;
            }

            if(!stack.isEmpty()){
                curr = stack.pop();
                curr = curr.right;
            }
        }
        return traversal;
    }

}
