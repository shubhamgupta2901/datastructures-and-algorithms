package leetcode;

import java.util.HashSet;

/**
 * https://leetcode.com/problems/linked-list-cycle/
 * Given a linked list, determine if it has a cycle in it.
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed)
 * in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 *
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 */

public class LinkedListCycle {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;
        ListNode ptr = head;
        HashSet<ListNode> hashSet = new HashSet<ListNode>();
        hashSet.add(ptr);
        while (ptr.next != null) {
            if (hashSet.contains(ptr.next))
                return true;
            hashSet.add(ptr.next);
            ptr = ptr.next;
        }
        return false;
    }

    public static boolean hasCycleApproach2(ListNode head){
        if(head == null)
            return false;
        ListNode slow = head, fast = head;

        while(fast.next!=null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast)
                return true;
        }

        return false;
    }
}
