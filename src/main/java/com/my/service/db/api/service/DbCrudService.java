package com.my.service.db.api.service;


public interface DbCrudService<T> extends BatchCrudService<T> {

    T save(T t);

    T find(String table, String id);

    T delete(String id);

}
