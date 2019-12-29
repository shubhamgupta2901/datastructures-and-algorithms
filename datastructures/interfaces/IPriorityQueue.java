package datastructures.interfaces;

public interface IPriorityQueue {

    int size();

    void insert(int value);

    int getMin();

    int extractMin();
}
