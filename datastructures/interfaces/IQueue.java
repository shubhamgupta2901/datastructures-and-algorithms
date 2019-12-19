package datastructures.interfaces;

public interface IQueue {

    boolean enqueue(int value);

    int dequeue();

    int peek();

    boolean isEmpty();
}
