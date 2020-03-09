package grokking_ds_patterns.linkedlist_reversal;

/**
 * Reverse a singly linked list.
 *
 * Example:
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 *
 * Follow up:
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {
    /**
     * Iterative solution
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode reverseList(ListNode head) {
        if(head == null)
            return null;
        ListNode prevNode = null, currNode = head, nextNode;
        while(currNode!=null){
            nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        head = prevNode;
        return prevNode;
    }


    /**
     * Recursive solution
     * Time Complexity: O(n)
     * Space complexity: O(n) if we consider recursive stack
     */
    public ListNode reverseListRecursive(ListNode head) {
        return reverse(null, head);
    }
    private ListNode reverse(ListNode prevNode, ListNode currNode){
        //base case
        if(currNode == null)
            return prevNode;
        //recursion
        ListNode nextNode = currNode.next;
        currNode.next = prevNode;
        //return statement
        return reverse(currNode, nextNode);
    }

}
