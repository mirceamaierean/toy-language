package model.adt.dictionary;

import java.util.List;

public interface IGenericDictionary<K, V> {

    void insert(K key, V value);

    boolean isDefined(K key);

    V lookup(K key) throws KeyNotFoundAppException;

    void delete(K key) throws KeyNotFoundAppException;

    List<K> getKeys();

    boolean exists(K key);
}
