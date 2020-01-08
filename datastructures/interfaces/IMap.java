package datastructures.interfaces;

public interface IMap {
    int size();

    void put(String key, Object value);

    Object get(String key);

    void remove(String key);
}
