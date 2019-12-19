package datastructures;

/**
 * CircularLinkedList is similar to LinkedList except the last node of the list points to the head node rather than pointing to null.
 *
 */
public class CircularLinkedList {
    class ListNode {
        public int data;
        public ListNode next;

        public ListNode(int data) {
            this.data = data;
            next = null;
        }
    }

    ListNode head;

    public CircularLinkedList() {
        head = null;
    }

    /**
     * Insert new node at beginning of a list.
     * @param data data for new Node
     * @return head of the linked list
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode insertAtBeginning (int data) {
        ListNode node = new ListNode(data);
        //List was empty
        if(head==null){
            head = node;
            node.next = head;
        }else{
            node.next = head;
            ListNode curr = head;
            while(curr.next!=head){
                curr = curr.next;
            }
            curr.next = node;
            head = node;
        }
        return head;
    }

    /**
     * Insert a node at the end of linked list
     * @param data data for the new node
     * @return head of the the linked list
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode insertAtEnd(int data){
        ListNode node = new ListNode(data);
        // If the list is empty
        if(head==null){
            head = node;
            node.next = head;
        }else {
            ListNode current = head;
            while(current.next != head){
                current = current.next;
            }
            current.next = node;
            node.next = head;
        }
        return head;
    }

    /**
     * insert a node at given position.
     * @param data data of the new node
     * @param position position at which the node will be inserted, starting at 0
     * @return head of the linked list
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public ListNode insertAtPosition(int data, int position){
        if(position==0)
            return insertAtBeginning(data);
        ListNode node = new ListNode(data);
        ListNode prev = head;
        for(int i =0;i<position-1;i++){
            prev = prev.next;
        }
        node.next = prev.next;
        prev.next= node;
        return head;
    }

    /**
     * Traverse the entire list and print data of every node till you encounter head node again.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void traverseList (){
        if(head == null){
            System.out.println("Empty!");
            return;
        }

        ListNode current = head;
        do {
            System.out.print(current.data +" -> ");
            current = current.next;
        }while (current!=head);

        System.out.print("|"+current.data+"|");
        System.out.println("");
    }

    /**
     * Search a node in circular linked list
     * @param value value of the node to be searched
     * @return searched Node
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public ListNode searchNode(int value) {
        if(head == null)
            return null;
        ListNode current = head;
        do{
            if(current.data == value)
                return current;
            current = current.next;
        }while (current!=head);
        return null;
    }

    /**
     * Delete first node with a given value
     * @param value value of the node to be deleted
     * @return head
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public ListNode deleteNode(int value){
        //Empty list
        if(head == null)
            return null;
        ListNode prev = null, curr = head;
        do{
            //Node to be deleted is found.
            if(curr.data == value){
                //First Node needs to be deleted
                if(prev==null){
                    //Only Node in LinkedList
                    if(curr.next==head){
                        head = null;
                    }else{
                        head = curr.next;
                        curr.next = null;
                        ListNode ptr = head;
                        while (ptr.next !=curr)
                            ptr = ptr.next;
                        ptr.next = head;
                    }
                }else {
                    prev.next = curr.next;
                    curr.next = null;
                }
                return head;
            }
            prev = curr;
            curr = curr.next;
        }while(curr!=head);
        //After traversing the list the node to be deleted was not found.
        return head;
    }

}
