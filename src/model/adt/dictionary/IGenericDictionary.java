package model.adt.dictionary;

public interface IGenericDictionary<K, V> {

    void insert(K key, V value);
    boolean isDefined(K key);
    V lookup(K key);
}
