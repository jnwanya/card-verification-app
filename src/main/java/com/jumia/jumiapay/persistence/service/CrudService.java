package com.jumia.jumiapay.persistence.service;

import java.util.Optional;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
public interface CrudService<T, ID> {

    Optional<T> findById(ID id);

    T getRecordById(ID id) throws RuntimeException;

    T saveRecord(T record);
}
