package com.my.service.db.api.service;

import java.util.List;

public interface BatchCrudService<T> {

    T saveAll(List<T> t);

    List<T> findAll();

    T deleteAll(String id);
}
