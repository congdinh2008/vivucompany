package com.congdinh.services.base;

import java.util.List;

public interface IGenericServices<T, ID> {
    List<T> getAll() throws Exception;

    T getById(ID id) throws Exception;

    void create(T country);

    void update(ID id, T country);

    void delete(ID id);
}
