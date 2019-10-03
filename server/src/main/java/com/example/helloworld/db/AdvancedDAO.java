package com.example.helloworld.db;

import java.util.List;

public interface AdvancedDAO<T> {
    List<T> findAll();
}