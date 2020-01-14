package playground;


import datastructures.LinkedList;

public class LinkedListMain {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addFirst(4);
        list.addFirst(3);
        list.addLast(5);
        list.addLast(6);
        list.add(0,2);
        list.add(5,7);
        list.add(0,0);
        list.add(1,1);

        int el = list.get(list.size()-1);

        boolean found = list.contains(0);
        System.out.println("found 0:"+found);
        found = list.contains(7);
        System.out.println("found 7:"+found);
        found = list.contains(9);
        System.out.println("found 9:"+found);

        int index = list.indexOf(4);
        index = list.indexOf(8);

        list.removeElement(0);
        list.removeElement(7);
        list.removeElement(3);
        list.removeAtIndex(2);
        list.removeFirst();
        list.removeLast();
    }
}
