package datastructures;

public class DoubleLinkedList {

    class ListNode{
        public int data;
        public ListNode prev;
        public ListNode next;

        public ListNode(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }
    private ListNode head;

    public DoubleLinkedList() {
        head = null;
    }

    /**
     * Insert node at the beginning of the list
     * @param data
     * @return head
     * Time complexity: O(1)
     * Space complexity: O(1)
     */
    public ListNode insertNodeAtBeginning(int data){
        ListNode node = new ListNode(data);
        //Empty list
        if(head == null){
            head = node;
            return head;
        }
        head.prev = node;
        node.next = head;
        head = node;
        return head;
    }

    /**
     * Insert node at the end of the list
     * @param data
     * @return head
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode insertNodeAtEnd(int data){
        ListNode node = new ListNode(data);
        //Empty list
        if(head==null){
            head = node;
            return head;
        }
        ListNode curr = head;
        while (curr.next!=null){
            curr = curr.next;
        }
        curr.next = node;
        node.prev = curr;
        return head;
    }

    /**
     * Insert a node at a specific position
     * @param data data for new node
     * @param position position at which the node has to be inserted, position 0 means at the start and so on
     *                 Do not insert if position is larger than size of list.
     * @return head
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode insertNodeAtPosition(int data, int position){
        //Insert at the beginning
        if(position == 0)
            return insertNodeAtBeginning(data);
        //If list is empty and position is not 0, node can not be inserted
        if(head == null)
            return null;
        ListNode node = new ListNode(data);
        ListNode curr = head;
        for(int i = 1; i<position; i++){
            //position greater than size of list, can not insert
            if(curr==null)
                break;
            curr = curr.next;
        }
        if(curr!=null){
            node.prev = curr;
            node.next = curr.next;
            curr.next = node;
            if(node.next!=null)
                node.next.prev = node;
        }
        return head;

    }

    /**
     * Search the first node with given data in list
     * @param data data of node to be searched.
     * @return searched node if found, else null
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode searchNode (int data){
        if(head == null)
            return null;
        ListNode ptr = head;
        while (ptr!=null){
            if(ptr.data == data)
                return ptr;
            ptr = ptr.next;
        }
        return null;
    }

    /**
     * delete a node with given value of data
     * @param value
     * @return head of linked list
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public ListNode deleteNode (int value){
        if(head == null)
            return null;
        ListNode ptr = head;
        while (ptr!=null){
            if(ptr.data ==value)
                break;
            ptr = ptr.next;
        }
        //Node to be deleted not in list
        if(ptr==null)
            return head;
        if(ptr.prev == null && ptr.next == null){
            //Delete the only node in list
            head =null;
        } else if(ptr.prev ==null){
            //Delete the first node in list
            head = ptr.next;
            ptr.next.prev =null;
            ptr.next = null;
        }else {
            ptr.prev.next = ptr.next;
            //If node is not the last node in list
            if(ptr.next!=null)
                ptr.next.prev = ptr.prev;
            ptr.next = null;
            ptr.prev = null;
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

}
