package com.edu.system.service;

public interface StoringService<K, V> {
    void save(K key, V object);
    V get(K key);
}
