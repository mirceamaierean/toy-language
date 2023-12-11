package model.adt.dictionary;

import java.util.List;
import java.util.Map;

public interface IGenericDictionary<K, V> {

    void insert(K key, V value);

    boolean isDefined(K key);

    V lookup(K key) throws KeyNotFoundAppException;

    void delete(K key) throws KeyNotFoundAppException;

    Map<K, V> getMap();

    List<K> getKeys();

    boolean exists(K key);
}