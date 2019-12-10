import ds.CircularLinkedList;

public class CircularLinkedListMain {
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();
        list.insertAtBeginning(4);
        list.insertAtEnd(5);
        list.insertAtBeginning(3);
        list.insertAtBeginning(2);
        list.insertAtBeginning(0);
        list.insertAtEnd(6);
        list.insertAtPosition(1,1);
        list.insertAtPosition(7,7);
        list.traverseList();

        boolean found = list.searchNode(0) != null ? true :false;
        System.out.println("found 0:"+found);
        found = list.searchNode(7) != null ? true :false;
        System.out.println("found 7:"+found);
        found = list.searchNode(9) != null ? true :false;
        System.out.println("found 9:"+found);

        list.deleteNode(0);
        list.deleteNode(7);
        list.deleteNode(4);
        list.deleteNode(1);
        list.deleteNode(6);
        list.deleteNode(2);
        list.deleteNode(3);
        list.deleteNode(5);
        list.traverseList();
    }
}
