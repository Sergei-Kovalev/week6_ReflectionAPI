package com.gmail.kovalev.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache<K, V> implements Cache<K, V> {
    HashMap<K, V> values;
    HashMap<K, Integer> counts;
    HashMap<Integer, LinkedHashSet<K>> lists;
    int capacity;
    int min = -1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.values = new HashMap<>();
        this.counts = new HashMap<>();
        this.lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }

    @Override
    public V get(K key) {
        if (!values.containsKey(key)) {
            return null;
        }
        int count = counts.get(key);
        counts.put(key, count + 1);
        lists.get(count).remove(key);

        if (count == min && lists.get(count).isEmpty()) {
            min++;
        }
        if (!lists.containsKey(count + 1))
            lists.put(count + 1, new LinkedHashSet<>());
        lists.get(count + 1).add(key);
        return values.get(key);
    }

    @Override
    public void set(K key, V value) {
        if (capacity <= 0)
            return;
        if (values.containsKey(key)) {
            values.put(key, value);
            get(key);
            return;
        }
        if (values.size() >= capacity) {
            K keyForDelete = lists.get(min).iterator().next();
            lists.get(min).remove(keyForDelete);
            values.remove(keyForDelete);
            counts.remove(keyForDelete);
        }
        values.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }

    @Override
    public void remove(K key) {
        Integer count = counts.get(key);
        values.remove(key);
        counts.remove(key);
        lists.get(count).remove(key);
    }
}
