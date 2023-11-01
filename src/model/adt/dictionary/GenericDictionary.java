package model.adt.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericDictionary<K, V> implements IGenericDictionary<K, V> {
    private Map<K, V> map;

    public GenericDictionary() {
        map = new HashMap<>();
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
    public V lookup(K key) throws KeyNotFoundAppException {
        if (!map.containsKey(key)) {
            throw new KeyNotFoundAppException("Key " + key.toString() + " not found.");
        }
        return map.get(key);
    }

    @Override
    public void delete(K key) throws KeyNotFoundAppException {
        if (!map.containsKey(key)) {
            throw new KeyNotFoundAppException("Key " + key.toString() + " not found.");
        }
        map.remove(key);
    }

    @Override
    public boolean exists(K key) {
        return map.containsKey(key);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (K key : map.keySet()) {
            result.append(key.toString()).append(" -> ").append(map.get(key).toString()).append("\n");
        }
        return "GenericDictionary{" +
                "map=" + result +
                '}';
    }

    public Map<K, V> getMap() {
        return map;
    }

    public void setMap(Map<K, V> map) {
        this.map = map;
    }

    @Override
    public List<K> getKeys() {
        return new ArrayList<>(this.map.keySet());
    }
}
