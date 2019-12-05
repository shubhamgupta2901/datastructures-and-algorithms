import ds.LinkedList;

public class LinkedListMain {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insertNodeAtBegining(4);
        list.insertNodeAtBegining(3);
        list.inserNodeAtEnd(5);
        list.inserNodeAtEnd(6);
        list.insertNodeAtPosition(2,0);
        list.insertNodeAtPosition(7,5);
        list.insertNodeAtPosition(0,0);
        list.insertNodeAtPosition(1,1);
        list.traverseLinkedList();

        boolean found = list.searchNode(0) != null ? true :false;
        System.out.println("found 0:"+found);
        found = list.searchNode(7) != null ? true :false;
        System.out.println("found 7:"+found);
        found = list.searchNode(9) != null ? true :false;
        System.out.println("found 9:"+found);

        list.deleteNode(0);
        list.traverseLinkedList();
        list.deleteNode(1);
        list.traverseLinkedList();
        list.deleteNode(7);
        list.traverseLinkedList();
        list.deleteNode(4);
        list.traverseLinkedList();
        list.deleteNode(5);
        list.traverseLinkedList();
        list.deleteNode(9);
        list.traverseLinkedList();
    }
}
