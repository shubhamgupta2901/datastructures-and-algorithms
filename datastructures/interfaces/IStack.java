package datastructures.interfaces;

public interface IStack {

    boolean push(int element);

    int pop();

    int peek();

    boolean empty();

    int size();

    int search(int element);
}
