package leetcode;

import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/palindrome-linked-list/"/>
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Example 1:
 *
 * Input: 1->2
 * Output: false
 * Example 2:
 *
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 */
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
}
