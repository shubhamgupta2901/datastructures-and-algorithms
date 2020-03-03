package grokking_ds_patterns.fast_and_slow_pointers;

/**
 * Given the head of a Singly LinkedList, write a method to return the middle node of the LinkedList.
 * If the total number of nodes in the LinkedList is even, return the second middle node.
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
