package datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.
 * For example:
 * ..........1
 * ......./    \
 * .....2       3
 * ..../ \     /
 * ...4   5   6
 * Here we are implementing complete binary tree as an ARRAY. So while we visualise a binary tree (complete), we use array to implement it.
 * While implementing a Binary Tree with array is not a space efficient way, the structure of Complete Binary Tree makes it a perfect
 * candidate for array implementation (improves time and space complexity of operations).
 * The idea is keep root at index 0, subsequent parent nodes in index n, their left child at index 2*n+1 and right child at index 2*n+2
 * [This is also the order in which nodes appear in level order traversal of tree].
 */
public class CompleteBinaryTree {

    private int[] arr;
    private int capacity;
    private int size;

    public CompleteBinaryTree(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.arr = new int[capacity];
        for(int i = 0; i<capacity;i++)
            arr[i] = Integer.MIN_VALUE;
    }

    public int getRootIndex() {
        if(size == 0)
            return -1;
        return 0;
    }

    /**
     *
     * @return
     * Time complexity: O(n)
     * Space Complexity: O(n)
     */
    public List<Integer> preOrderTraversal() {
        List<Integer> traversal = new ArrayList<>();
        if(size == 0)
            return traversal;
        Stack<Integer> stack = new Stack<>();
        int nodeIndex = 0;
        while(!stack.isEmpty() || nodeIndex>-1){
            while(nodeIndex>-1){
                stack.add(nodeIndex);
                traversal.add(arr[nodeIndex]);
                nodeIndex = getLeftChildIndex(nodeIndex);
            }

            if(!stack.isEmpty()){
                nodeIndex = stack.pop();
                nodeIndex = getRightChildIndex(nodeIndex);
            }
        }
        return traversal;
    }

    public List<Integer> inOrderTraversal() {
        List<Integer> traversal = new ArrayList<>();
        if(size == 0)
            return traversal;
        Stack<Integer> stack = new Stack<>();
        int nodeIndex = 0;
        while(!stack.isEmpty() || nodeIndex>-1){
            while(nodeIndex>-1){
                stack.add(nodeIndex);
                nodeIndex = getLeftChildIndex(nodeIndex);
            }

            if(!stack.isEmpty()){
                nodeIndex = stack.pop();
                traversal.add(arr[nodeIndex]);
                nodeIndex = getRightChildIndex(nodeIndex);
            }
        }
        return traversal;
    }

    public List<Integer> postOrderTraversal() {
        return null;
    }

    /**
     *
     * @return
     * Time Complexity: O(n)
     * Space Compexity: O(1)
     */
    public List<Integer> levelOrderTraversal() {
        List<Integer> traversal = new ArrayList<>();
        if(size == 0)
            return traversal;
        for(int i = 0; i< size; i++)
            traversal.add(arr[i]);
        return traversal;
    }

    /**
     * Return index of searched element
     * @param value value of node to be searched.
     * @return index of searched node in array; -1 if not found
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int searchNode(int value) {
        if(size == 0)
            return -1;
        for(int i = 0; i<size; i++){
            if(arr[i]==value)
                return i;
        }
        return  -1;
    }

    /**
     * delete a node with given value.
     * We will copy the value of last node in array to value of searched node
     * and delete the last node to keep the tree complete.
     * @param value
     * @return true if value was deleted; false otherwise
     */
    public boolean deleteNode(int value) {
        if(size==0)
            return false;
        int indexOfValue = -1;
        for(int i = 0; i<size; i++){
            if(arr[i]==value){
                indexOfValue = i;
                break;
            }
        }
        //Value was not found in tree
        if(indexOfValue == -1)
            return false;
        size--;
        arr[indexOfValue] = arr[size];
        arr[size] = Integer.MIN_VALUE;
        return true;
    }

    /**
     * Inserting node at last index (first empty space)
     * This will keep the binary tree complete after insertion.
     * @param value
     * @return true if value was inserted
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean insertNode(int value) {
        if(size == capacity)
            return false;
        arr[size++] = value;
        return true;
    }

    /**
     * My idea is to find height of complete binary tree in constant time.
     * The idea is for a tree with height x, the max number of possible nodes could be 2^(x+1)-1.
     * So assuming the height of tree is h and size of tree is size, it must satisfy following equation
     * 2^(h+1)-1 >= size > 2^h-1
     * so height = Math.ceiling(log(size+1)) -1; [base of log is 2]
     * @return
     */
    public int height() {
        return ((int) Math.ceil(log2(size+1))) -1;
    }


    /**
     * Return the size of tree
     * @return
     * Time complexity: O(1)
     * Space Complexity: O(1)
     */
    public int size() {
        return this.size;
    }

    public List<Integer> recursivePreOrderTraversal(int nodeIndex){
        List<Integer> traversal = new ArrayList<>();
        if(nodeIndex == -1)
            return traversal;
        traversal.add(arr[nodeIndex]);
        traversal.addAll(recursivePreOrderTraversal(getLeftChildIndex(nodeIndex)));
        traversal.addAll(recursivePreOrderTraversal(getRightChildIndex(nodeIndex)));
        return traversal;
    }

    public List<Integer> recursiveInOrderTraversal(int nodeIndex){
        List<Integer> traversal = new ArrayList<>();
        if(nodeIndex == -1)
            return traversal;
        traversal.addAll(recursiveInOrderTraversal(getLeftChildIndex(nodeIndex)));
        traversal.add(arr[nodeIndex]);
        traversal.addAll(recursiveInOrderTraversal(getRightChildIndex(nodeIndex)));
        return traversal;
    }

    public int recursiveHeight(int nodeIndex){
        if(nodeIndex == -1)
            return -1;
        return 1+ Math.max(recursiveHeight(getLeftChildIndex(nodeIndex)), recursiveHeight(getRightChildIndex(nodeIndex)));
    }

    private int getLeftChildIndex(int nodeIndex){
        int leftChildIndex = 2*nodeIndex +1;
        return leftChildIndex<size ? leftChildIndex : -1;
    }

    private int getRightChildIndex(int nodeIndex){
        int rightChildIndex = 2* nodeIndex +2;
        return rightChildIndex < size ? rightChildIndex : -1;
    }

    private double log2(double a){
        return Math.log10(a)/Math.log10(2);
    }
}
