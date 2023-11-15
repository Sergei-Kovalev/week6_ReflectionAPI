package com.gmail.kovalev.cache;

import com.gmail.kovalev.entity.Faculty;

import java.util.LinkedHashMap;
import java.util.UUID;

public class LRUCache implements Cache {
    int capacity;
    LinkedHashMap<UUID, Faculty> cache = new LinkedHashMap<>();
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Faculty get(UUID key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        makeRecently(key);
        return cache.get(key);
    }

    @Override
    public void set(UUID key, Faculty value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
            cache.put(key,value);
            return;
        }
        if (cache.size() >= capacity) {
            UUID oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);

        }
        cache.put(key,value);
    }

    @Override
    public void remove(UUID key) {
        cache.remove(key);
    }

    private void makeRecently(UUID key){
        Faculty val = cache.get(key);
        cache.remove(key);
        cache.put(key,val);
    }
}
