package com.codegym.service;

import com.codegym.model.Account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IGeneralService<T> {
    List<T> findAll();
    boolean save(T t);
    boolean delete(Long id);
    T findById(Long id);
}
