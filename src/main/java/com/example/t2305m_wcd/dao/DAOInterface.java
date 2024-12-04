package com.example.t2305m_wcd.dao;

import java.util.List;

public interface DAOInterface<S,T> {
    List<S> all();
    void create(S s);
    void update(S s);
    void delete(T id);
    S find(T id);
}
