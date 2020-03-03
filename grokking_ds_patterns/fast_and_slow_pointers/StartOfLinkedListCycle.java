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
}
