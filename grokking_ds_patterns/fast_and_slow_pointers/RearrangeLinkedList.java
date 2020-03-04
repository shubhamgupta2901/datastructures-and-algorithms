package grokking_ds_patterns.fast_and_slow_pointers;

import java.util.List;

/**
 * Given the head of a Singly LinkedList, write a method to modify the LinkedList such that the nodes from the
 * second half of the LinkedList are inserted alternately to the nodes from the first half in reverse order.
 * So if the LinkedList has nodes : 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null,
 * your method should return :      1 -> 6 -> 2 -> 5 -> 3 -> 4 -> null.
 *
 * Your algorithm should not use any extra space and the input LinkedList should be modified in-place.
 *
 * Example 1:
 * Input: 2 -> 4 -> 6 -> 8 -> 10 -> 12 -> null
 * Output: 2 -> 12 -> 4 -> 10 -> 6 -> 8 -> null
 *
 * Example 2:
 * Input: 2 -> 4 -> 6 -> 8 -> 10 -> null
 * Output: 2 -> 10 -> 4 -> 8 -> 6 -> null
 */
public class RearrangeLinkedList {

    private static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param head
     * @return
     */
    public ListNode reorder(ListNode head) {
        if(head == null)
            return null;
        //find middle of the linked list
        ListNode slow = head, fast = head;
        while(fast.next!=null && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        //reverse the second half of the linked list
        ListNode secondHead = slow.next;
        slow.next = null;
        ListNode prevPtr = null, currPtr = secondHead, nextPtr = null;
        while(currPtr!=null){
            nextPtr = currPtr.next;
            currPtr.next = prevPtr;
            prevPtr = currPtr;
            currPtr = nextPtr;
        }
        secondHead = prevPtr;
        //reorder the linked list
        ListNode ptr1 = head, ptr2 = secondHead;
        ListNode nextPtr2;
        while (ptr2!=null){
            nextPtr2 = ptr2.next;
            ptr2.next = ptr1.next;
            ptr1.next = ptr2;
            ptr1 = ptr2.next;
            ptr2 = nextPtr2;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        //head.next.next.next.next.next = new ListNode(12);
        head = new RearrangeLinkedList().reorder(head);
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }
}
