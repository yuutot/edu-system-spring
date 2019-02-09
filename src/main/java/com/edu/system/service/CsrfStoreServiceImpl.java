package com.edu.system.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CsrfStoreServiceImpl implements StoringService<String, String> {

    private final Map<String, String> store;

    public CsrfStoreServiceImpl() {
        this.store = new HashMap<>();
    }

    @Override
    public void save(String key, String object) {
        store.put(key, object);
    }

    @Override
    public String get(String key) {
        return store.get(key);
    }

}
