package com.gmail.kovalev.cache.impl;

import com.gmail.kovalev.cache.Cache;

import java.util.LinkedHashMap;

public class LRUCache<K, V> implements Cache<K, V> {
    int capacity;
    LinkedHashMap<K, V> cache = new LinkedHashMap<>();
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        makeRecently(key);
        return cache.get(key);
    }

    @Override
    public void set(K key, V value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
            cache.put(key,value);
            return;
        }
        if (cache.size() >= capacity) {
            K oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);

        }
        cache.put(key,value);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    private void makeRecently(K key){
        V val = cache.get(key);
        cache.remove(key);
        cache.put(key,val);
    }
}
