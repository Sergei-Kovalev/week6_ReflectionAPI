package com.gmail.kovalev.caches;

import com.gmail.kovalev.entity.Faculty;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.UUID;

public class LFUCache implements Cache {
    HashMap<UUID, Faculty> values;
    HashMap<UUID, Integer> counts;
    HashMap<Integer, LinkedHashSet<UUID>> lists;
    int cap;
    int min = -1;

    public LFUCache(int capacity) {
        cap = capacity;
        values = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }

    @Override
    public Faculty get(UUID key) {
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
    public void set(UUID key, Faculty value) {
        if (cap <= 0)
            return;
        if (values.containsKey(key)) {
            values.put(key, value);
            get(key);
            return;
        }
        if (values.size() >= cap) {
            UUID keyForDelete = lists.get(min).iterator().next();
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
    public void remove(UUID key) {
        Integer count = counts.get(key);
        values.remove(key);
        counts.remove(key);
        lists.get(count).remove(key);
    }
}
