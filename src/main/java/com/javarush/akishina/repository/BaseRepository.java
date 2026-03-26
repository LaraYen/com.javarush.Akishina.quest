package com.javarush.akishina.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseRepository<T> {

    protected Map<String, T> data = new HashMap<>();

    public BaseRepository() {
        initRepo();
    }

    public abstract void initRepo();

    public T findById(String id) {
        return data.get(id);
    }

    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

}
