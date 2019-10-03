package com.example.helloworld.service;

import java.util.List;

import com.example.helloworld.core.Player;
import com.example.helloworld.errorhandling.AppException;

public interface Service<T>{
    Player findById(long id) throws AppException;
    List<T> findAll() throws AppException;
}