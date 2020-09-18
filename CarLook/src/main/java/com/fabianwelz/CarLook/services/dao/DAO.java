package com.fabianwelz.CarLook.services.dao;

import com.fabianwelz.CarLook.process.control.exception.DatabaseException;

public interface DAO <K,T> {   
    public void create(T obj) throws DatabaseException;
    public T read(K id) throws DatabaseException;
}