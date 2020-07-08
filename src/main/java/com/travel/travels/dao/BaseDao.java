package com.travel.travels.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T,K> {
    void save(T t);
    void update(T t);
    void delete(K k);
    T findOne(K k);

    List<T> findAll();
    List<T> findByPage(@Param("start") Integer start, @Param("rows") Integer row);
    List<T> findByName(@Param("name") String name);
    Integer findTotals();

}
