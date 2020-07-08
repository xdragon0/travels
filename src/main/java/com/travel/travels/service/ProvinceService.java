package com.travel.travels.service;

import com.travel.travels.entity.Province;

import java.util.List;

public interface ProvinceService {
    //page:当前页,rows:每页显示记录数
    List<Province> findByPage(Integer page,Integer rows);
    //查询
    List<Province> findByName(String name);
    //查询总条数
    Integer findTotals();
    //保存省份
    void save(Province province);
    //删除省份
    void delete(String id);
    //查询一个省份
    Province findOne(String id);
    //修改省份
    void update(Province province);
}
