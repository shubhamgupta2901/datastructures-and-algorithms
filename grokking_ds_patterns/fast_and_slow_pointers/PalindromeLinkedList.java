package grokking_ds_patterns.fast_and_slow_pointers;

import java.util.Stack;

public class PalindromeLinkedList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * Approach 1: Store the values of linked list in a stack till the middle of linked list,
     * after that keep poppping the values from stack , as we traverse the second half of list.
     * If the values are not similar, or at end stack is not empty , linked list is not palindrome
     * Time complexity: O(n) [One pass]
     * Space complexity : O(n) for using stack of size n/2
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null)
            return true;
        Stack<Integer> stack = new Stack<>();
        ListNode slow = head, fast = head;

        while(fast.next!=null && fast.next.next!=null){
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        //odd number of node in linked list
        if(fast.next == null)
            slow = slow.next;
        else {
            stack.push(slow.val);
            slow = slow.next;
        }

        while(slow!=null){
            if(slow.val != stack.peek())
                return false;
            stack.pop();
            slow = slow.next;
        }
        return stack.isEmpty();
    }

    /**
     * Approach 2: Find the middle of linked list, and reverse the linked list from middle to end.
     * With two pointers - one pointing to head, another to middle.next, start traversing the linked list.
     * If all the values are same, the linked list is palindrome.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public boolean isPalindrome2(ListNode head) {
        if(head == null)
            return true;
        //Find the middle of linked list
        ListNode slow = head, fast = head;
        while(fast.next!=null && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the linked list from middle to head
        ListNode secondHead = slow.next;
        ListNode prevPtr = null, currPtr = secondHead, nextPtr;
        while(currPtr!=null){
            nextPtr = currPtr.next;
            currPtr.next = prevPtr;
            prevPtr = currPtr;
            currPtr = nextPtr;
        }
        //slow.next = prevPtr;
        secondHead = prevPtr;

        //use two pointers one from head, one from middle.next, keep on comparing them
        ListNode ptr1 = head, ptr2 = secondHead;
        while(ptr2!=null){
            if(ptr1.val != ptr2.val)
                return false;
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return true;
    }
}
