package model.adt.dictionary;

import java.util.HashMap;
import java.util.Map;

public class GenericDictionary<K, V> implements IGenericDictionary<K, V> {
    private Map<K, V> map;
    public GenericDictionary() {
        map = new HashMap<K, V>();
    }

    @Override
    public void insert(K key, V value) {
        map.put(key, value);
    }

    @Override
    public boolean isDefined(K key) {
        return map.get(key) != null;
    }

    @Override
    public V lookup(K key) {
        return map.get(key);
    }

    @Override
    public String toString() {
        return "GenericDictionary{" +
                "map=" + map +
                '}';
    }

    public Map<K, V> getMap() {
        return map;
    }

    public void setMap(Map<K, V> map) {
        this.map = map;
    }
}
