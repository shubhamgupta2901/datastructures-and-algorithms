package datastructures;

public class LinkedList {

    /**
     * Creation,
     * Insertion of node,
     * Traversal
     * Searching
     * Deletion of a node
     * Deletion of list
     */
    static class ListNode {
        public int data;
        public ListNode next;

        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private ListNode head;

    public LinkedList() {
        head = null;
    }

    /**
     * Inserts node at the beginning of Linked List
     * @param data
     * @returnn head
     * Time Complexity = O(1)
     */
    public ListNode insertNodeAtBegining(int data){
        ListNode node = new ListNode(data);
        if(head == null) {
            head = node;
        }
        else {
            node.next = head;
            head  = node;
        }
        return head;
    }

    /**
     * Inserts node at the end of Linked List
     * @param data
     * @return head
     * Time Complexity = O(n)
     */
    public ListNode inserNodeAtEnd(int data){
        ListNode node = new ListNode(data);
        if(head==null){
            head = node;
        }else {
            ListNode pointer = head;
            while (pointer.next !=null){
                pointer = pointer.next;
            }
            pointer.next = node;
        }
        return head;
    }

    /**
     * Insert location at a given position/location
     * @param data
     * @param position starting from 0
     * @return head
     * Time Complexity: O(n)
     * Space Complexity:O(1)
     */
    public ListNode insertNodeAtPosition(int data, int position){
        if(position == 0)
            return insertNodeAtBegining(data);
        ListNode pointer = head;
        for(int i =1; i< position; i++){
            if(pointer == null)
                break;
            pointer = pointer.next;
        }
        if(pointer!=null){
            ListNode node = new ListNode(data);
            node.next = pointer.next;
            pointer.next = node;
        }
        return head;
    }

    /**
     * Traverse the linked list and print every data of every node.
     * Time Complexity: O(n)
     * Space Complexity:O(1)
     */
    public void traverseLinkedList (){
        ListNode pointer = head;
        while (pointer!=null){
            System.out.print(pointer.data+" -> ");
            pointer = pointer.next;
        }
        System.out.print("NULL");
        System.out.println("");
    }

    /**
     * Search a node with given value
     * @param value value of node to be searched
     * @return return node if found else null
     * Time Complexity: O(n)
     * Space Complexity:O(1)
     */
    public ListNode searchNode(int value){
         ListNode pointer = head;
         while (pointer !=null){
             if(pointer.data == value)
                 return pointer;
             pointer = pointer.next;
         }
         return null;
    }

    /**
     * delete a node with given value of data
     * @param value
     * @return head
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode deleteNode(int value){
        if(head == null)
            return null;

        ListNode prev = null, curr = head;
        while(curr.next!=null){
            if(curr.data == value)
                break;
            prev = curr;
            curr = curr.next;
        }

        //Node with value was not found
        if(curr.next == null && curr.data!=value)
            return head;

        //First node
        if(prev==null)
            head = curr.next;
        else prev.next = curr.next;
        curr.next = null;
        return head;

    }

    /**
     * Delete the entire LinkedList.
     * @return head of the LinkedList which anyway points to null;
     * Time Complexity: O(1)
     */
    public ListNode deleteList () {
        head = null;
        return head;
    }

}
