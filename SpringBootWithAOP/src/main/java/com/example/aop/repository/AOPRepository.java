package com.example.aop.repository;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class AOPRepository {
    private static final Map<String, Integer> REPO = new LinkedHashMap<>();

    public int insertMap(String key, int value) {
        return Optional.ofNullable(REPO.put(key, value)).orElse(0);
    }

    public int removeMap(String key) {
        return Optional.ofNullable(REPO.remove(key)).orElse(0);
    }

    public int findMap(String key) {
        return REPO.entrySet().stream()
                .filter(item -> item.getKey().equals(key))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseGet(() -> 0);
    }

    public int updateMap(String key, int value) {
        return Optional.ofNullable(REPO.put(key, value)).orElse(0);
    }
}
