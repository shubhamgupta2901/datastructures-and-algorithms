package leetcode;

import java.util.HashSet;

/**
 * <a href="https://leetcode.com/problems/intersection-of-two-linked-lists/" />
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * Example 1:
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * Output: Reference of the node with value = 8
 * Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5].
 * There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 *
 * Example 3:
 * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * Output: null
 * Input Explanation: From the head of A, it reads as [2,6,4].
 * From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0,
 * while skipA and skipB can be arbitrary values.
 * Explanation: The two lists do not intersect, so return null.
 *
 * Notes:
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 */
public class IntersectionofTwoLinkedLists {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * Approach 1: traverse through the first linked list, store all nodes in hashset
     * Traverse through the second list and when you encounter a node in hashset return it.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public ListNode getIntersectionNodeApproach1(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;
        HashSet<ListNode> set = new HashSet<>();
        ListNode ptr = headA;
        while(ptr!=null){
            set.add(ptr);
            ptr = ptr.next;
        }
        ptr = headB;
        while(ptr!=null){
            if(set.contains(ptr))
                return ptr;
            ptr = ptr.next;
        }
        return null;
    }

    /**
     * For every node in first linked list, check whether second linked list has it.
     * Time Complexity: O(mn)
     * Space complexity: O(1)
     */
    public ListNode getIntersectionNodeApproach2(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;
        ListNode nodeI = headA, nodeJ;
        while(nodeI!=null){
            nodeJ = headB;
            while(nodeJ!=null){
                if(nodeI ==nodeJ)
                    return nodeI;
                nodeJ = nodeJ.next;
            }
            nodeI = nodeI.next;
        }
        return null;
    }


    /**
     * Best approach so far. Idea is to find the lengths of both linked lists in one pass.
     * Keep the pointer at head for shorter linked list, for longer linked list move it forward
     * by the difference in their lengths.
     * Now in second pass we can keep checking if the two nodes are similar otherwise move them to their
     * respective next nodes.
     * Time Complexity: O(m+n)
     * Space Complexity: O(1)
     */
    public ListNode getIntersectionNodeApproach3(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;
        int lenA = 0, lenB = 0;
        ListNode ptrA = headA, ptrB = headB;
        while(ptrA!=null || ptrB!=null){
            if(ptrA!=null){
                lenA++;
                ptrA= ptrA.next;
            }
            if(ptrB!=null){
                lenB++;
                ptrB = ptrB.next;
            }
        }

        ptrA = headA;
        ptrB = headB;

        if(lenA>lenB){
            for(int i = 0 ; i<lenA-lenB; i++)
                ptrA = ptrA.next;
        }else if(lenB> lenA){
            for(int i = 0; i<lenB-lenA; i++)
                ptrB = ptrB.next;
        }

        while(ptrA!=null && ptrB!=null){
            if(ptrA == ptrB)
                return ptrA;
            ptrA = ptrA.next;
            ptrB = ptrB.next;
        }

        return null;
    }
}
