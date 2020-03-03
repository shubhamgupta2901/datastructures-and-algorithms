package grokking_ds_patterns.fast_and_slow_pointers;


import java.util.HashSet;

/**
 * Given the head of a Singly LinkedList, write a function to determine if the LinkedList has a cycle in it or not.
 */
public class LinkedListCycle {

    private static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    /**
     * Approach 1: Store the nodes in a hashset. If while traversing the list, we find a node which is already
     * stored in hashset, we return true.
     * Otherwise the traversal of list would end without finding a cycle and we return false
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static boolean hasCycleApproach1(ListNode head) {
        if(head == null)
            return  false;
        HashSet<ListNode> hashSet = new HashSet<>();
        ListNode ptr = head;
        while(ptr!=null){
            if(hashSet.contains(ptr))
                return true;
            hashSet.add(ptr);
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

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycleApproach2(head));

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycleApproach2(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycleApproach2(head));
    }
}
