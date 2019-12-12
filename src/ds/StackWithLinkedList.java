package ds;

/**
 * Implementing stack with a linked list.
 * One advantage over its implementation in array is we can have a dynamic sized stack.
 *
 */
public class StackWithLinkedList {

    /**
     * Attempting to create a LinkedList which can efficiently perform insert and delete element at the end.
     * Remember one advantage of using array to implement Stack is we are able to perform push and pop operations in constant time.
     * With using a Double LinkedList which maintains a tail pointer in addition to a head pointer,
     * we can perform stack's push and pop operation in constant time as well
     */
    class LinkedList{

        class Node{
            public int data;
            public Node prev;
            public Node next;

            public Node(int data) {
                this.data = data;
                this.prev = null;
                this.next = null;
            }
        }

        private Node head, tail;

        public LinkedList() {
            head = null;
            tail = null;
        }

        /**
         * Check if the linked list is empty
         * @return
         */
        public boolean isEmpty(){
            return head == null;
        }

        /**
         * Delete the last node of linked list and return its data
         * @return data of deleted node or Integer.MIN_VALUE if list is empty
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public int deleteElementFromEnd(){
            if(isEmpty())
                return Integer.MIN_VALUE;

            //Only element in list
            if(head == tail){
                int value = head.data;
                head = tail = null;
                return value;
            }

            Node currNode = tail, prevNode = tail.prev;
            prevNode.next = null;
            currNode.prev = null;
            tail = prevNode;
            return currNode.data;
        }

        /**
         * Insert an element at the end of list
         * @param data value of node to be inserted
         * @return true if the node is inserted
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public boolean insertElementAtEnd(int data){
            Node node = new Node(data);
            if(isEmpty()){
                head = tail = node;
                return true;
            }
            tail.next = node;
            node.prev = tail;
            tail = node;
            return true;
        }

        /**
         * peek the data of tail element.
         * @return data of tail if not empty
         * Time Complexity: O(1)
         * Space Complexity: O(1)
         */
        public int peekTailElement(){
            if(isEmpty())
                return Integer.MIN_VALUE;
            return tail.data;
        }

    }


    public boolean push(int value) {
        return false;
    }


    public int pop() {
        return 0;
    }


    public int peek() {
        return 0;
    }


    public boolean isEmpty() {
        return false;
    }
}
