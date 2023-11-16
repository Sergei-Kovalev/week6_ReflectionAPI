package com.gmail.kovalev.cache.impl;

import com.gmail.kovalev.cache.Cache;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Объект для создания LFU кэша
 * @param <K> - объект ключа
 * @param <V> - объект значения
 */
public class LFUCache<K, V> implements Cache<K, V> {

    /**
     * содержит пары ключ - значение
     */
    HashMap<K, V> values;

    /**
     * содержит пары ключ - количество обращений
     */
    HashMap<K, Integer> counts;

    /**
     * содержит пары количество обращений - связанный список ключей (в порядке добавления)
     */
    HashMap<Integer, LinkedHashSet<K>> lists;
    /**
     * вместимость кэша (количество объектов для хранения)
     */
    int capacity;

    /**
     * Индекс в lists, где содержатся объекты с минимальным количеством обращений
     */
    int min = -1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.values = new HashMap<>();
        this.counts = new HashMap<>();
        this.lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }

    /**
     * Метод для получения объекта
     * @param key - объект ключа
     * @return объект
     * + обновляет счётчик обращений путём переноса в другую LinkedHashSet
     */
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

    /**
     * Метод для добавления объекта либо обновления его по ключу
     * @param key - объект ключа
     * @param value - объект значения
     */
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

    /**
     * Метод для удаления объекта из кэша
     * @param key - объект ключа
     */
    @Override
    public void remove(K key) {
        Integer count = counts.get(key);
        values.remove(key);
        counts.remove(key);
        lists.get(count).remove(key);
    }
}
