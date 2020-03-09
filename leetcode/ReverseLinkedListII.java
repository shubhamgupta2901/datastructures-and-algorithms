package leetcode;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * Note: 1 ≤ m ≤ n ≤ length of list.
 *
 * Example:
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 */
public class ReverseLinkedListII {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * Approach 1: Two pass approach - Identify sublist, and reverse it.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null)
            return null;

        //identify the sub list
        ListNode exStart = null, start = head, exEnd= head;
        for(int i = 1; i<=n; i++){
            if(i < m){
                exStart = start;
                start = start.next;
            }
            if(i<= n)
                exEnd = exEnd.next;
        }

        //reverse the sublist
        ListNode prevNode = exStart, currNode = start,nextNode;
        while(currNode!=exEnd){
            nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }

        //connect sublist to rest list
        if(exStart!=null)
            exStart.next = prevNode;
        else head = prevNode;
        start.next = exEnd;

        //return head of new list
        return head;
    }
}
