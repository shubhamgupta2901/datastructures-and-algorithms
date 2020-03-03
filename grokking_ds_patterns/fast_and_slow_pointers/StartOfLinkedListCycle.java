package grokking_ds_patterns.fast_and_slow_pointers;

import java.util.HashSet;

/**
 * Given the head of a Singly LinkedList that contains a cycle, write a function to find the starting node of the cycle.
 */
public class StartOfLinkedListCycle {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * Approach 1: store the nodes in a hashset, When a node is encountered which is already
     * present in the hashset, thats the node where the cycle starts
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null)
            return null;
        HashSet<ListNode> hashSet = new HashSet<>();
        ListNode ptr = head;
        while(ptr!=null){
            if(hashSet.contains(ptr))
                return ptr;
            hashSet.add(ptr);
            ptr = ptr.next;
        }
        return null;
    }

    /**
     * slow and fast pointer approach.
     * When the slow and fast pointers meet, initialize a new pointer ptr with head of linked list
     * Move ptr, and slow pointer by one node till both ptr and slow point to same node in linked list,
     * this it the start of cycle in a linked list.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode detectCycleApproach2(ListNode head) {
        if(head == null)
            return null;
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                ListNode ptr = head;
                while(ptr != slow){
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }

        return null;
    }
}
