package playground;

import ds.DoubleLinkedList;

public class DoubleLinkedListMain {
    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();

        list.insertNodeAtBeginning(2);
        list.insertNodeAtBeginning(0);
        list.insertNodeAtEnd(4);
        list.insertNodeAtEnd(6);
        list.insertNodeAtPosition(1,1);
        list.insertNodeAtPosition(3,3);
        list.insertNodeAtPosition(5,5);
        list.insertNodeAtPosition(7,7);
        list.insertNodeAtPosition(9,9);
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
        list.deleteNode(3);
        list.traverseLinkedList();
        list.deleteNode(2);
        list.traverseLinkedList();
        list.deleteNode(6);
        list.traverseLinkedList();
    }
}
