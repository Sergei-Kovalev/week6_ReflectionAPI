package com.gmail.kovalev.cache.impl;

import com.gmail.kovalev.cache.Cache;

import java.util.LinkedHashMap;

/**
 * Объект для создания LRU кэша
 * @param <K> - объект ключа
 * @param <V> - объект значения
 */
public class LRUCache<K, V> implements Cache<K, V> {

    /**
     * вместимость кэша (количество объектов для хранения)
     */
    int capacity;

    /**
     * хранилище для кэша. Обеспечивает упорядоченное хранения объектов по мере поступления
     * + даёт возможность их простого удаления
     */
    LinkedHashMap<K, V> cache = new LinkedHashMap<>();
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Метод для получения объекта
     * @param key - объект ключа
     * @return объект
     * + обновляет счётчик обращений
     */
    @Override
    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        makeRecently(key);
        return cache.get(key);
    }

    /**
     * Метод для добавления объекта либо обновления его по ключу
     * @param key - объект ключа
     * @param value - объект значения
     */
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

    /**
     * Метод для удаления объекта из кэша
     * @param key - объект ключа
     */
    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    /**
     * Метод для актуализации объекта(переносит как последний обновлённый)
     * @param key - объект ключа
     */
    private void makeRecently(K key){
        V val = cache.get(key);
        cache.remove(key);
        cache.put(key,val);
    }
}
