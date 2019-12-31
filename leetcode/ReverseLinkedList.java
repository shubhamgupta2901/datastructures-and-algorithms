package leetcode;

/**
 * <a href="https://leetcode.com/problems/reverse-linked-list/"/>
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * iterative solution
     * @param head
     * @return
     * Time complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode reverseList(ListNode head) {
        if(head == null)
            return null;
        ListNode curr = head, next;
        while(curr.next!=null){
            next = curr.next;
            curr.next = next.next;
            next.next = head;
            head = next;
        }
        return head;
    }

    /**
     * Recursive solution
     * @param head
     * @param curr
     * @return
     * Time complexity: O(n)
     * Space Complexity: O(n) For recursive stack.
     */
    private ListNode reverseList(ListNode head, ListNode curr){
        if(head == null)
            return null;
        if(curr.next == null)
            return head;
        ListNode next = curr.next;
        curr.next = next.next;
        next.next = head;
        return reverseList(next, curr);
    }
}
