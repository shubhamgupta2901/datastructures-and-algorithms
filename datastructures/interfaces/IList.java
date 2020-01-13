package datastructures.interfaces;

public interface IList {

    void add(int element);

    void add(int index, int element);

    void clear();

    boolean contains(int element);

    int get(int index);

    int indexOf(int element);

    boolean isEmpty();

    int removeAtIndex(int index);

    boolean removeElement(int element);

    int size();

}
