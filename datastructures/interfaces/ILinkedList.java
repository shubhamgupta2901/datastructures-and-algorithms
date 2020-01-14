package datastructures.interfaces;

public interface ILinkedList extends IList{

    void addFirst(int element);

    void addLast(int element);

    int getFirst();

    int getLast();

    void removeFirst();

    void removeLast();

}
