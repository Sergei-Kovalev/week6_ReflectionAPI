package com.gmail.kovalev.caches;

import com.gmail.kovalev.entity.Faculty;

import java.util.UUID;

public interface Cache {
    Faculty get(UUID key);
    void set(UUID key, Faculty value);
    void remove(UUID key);
}
