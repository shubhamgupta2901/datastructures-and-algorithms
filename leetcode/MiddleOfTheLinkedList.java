package leetcode;

/**
 * Given a non-empty, singly linked list with head node head, return a middle node of linked list.
 * If there are two middle nodes, return the second middle node.
 *
 * Example 1:
 * Input: [1,2,3,4,5]
 * Output: Node 3 from this list (Serialization: [3,4,5])
 * The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
 * Note that we returned a ListNode object ans, such that:
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
 *
 * Example 2:
 * Input: [1,2,3,4,5,6]
 * Output: Node 4 from this list (Serialization: [4,5,6])
 * Since the list has two middle nodes with values 3 and 4, we return the second one.
 */
public class MiddleOfTheLinkedList {
    private static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    /**
     * One approach could have been to find the length of linked list, and return node at length/2 by traversing again.
     * While the time complexity would have been linear, This would have been 2-pass approach.
     * Approach 2: A One pass approach
     * Here, we use two pointers, slow and fast, move slow by one node, and fast by two nodes.
     * By the time fast reaches the end of list, slow points to the middle of linked list.
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        if(head == null)
            return null;
        ListNode slow = head, fast = head;
        while(fast.next!=null && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //List with odd number of nodes
        if(fast.next == null)
            return slow;
        return slow.next;
    }
}
