package com.gmail.kovalev.cache;

public interface Cache<K, V> {
    V get(K key);
    void set(K key, V value);
    void remove(K key);
}
