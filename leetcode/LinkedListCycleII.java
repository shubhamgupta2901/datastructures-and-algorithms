package leetcode;

import java.util.HashSet;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed)
 * in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 * Note: Do not modify the linked list.
 *
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 *
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: no cycle
 * Explanation: There is no cycle in the linked list.
 *
 * Follow-up:
 * Can you solve it without using extra space?
 */
public class LinkedListCycleII {
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
