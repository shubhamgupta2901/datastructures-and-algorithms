package datastructures.interfaces;

public interface ISet {

    int size();

    boolean isEmpty();

    boolean contains(String key);

    boolean add(String key);

    boolean remove(String key);
}
