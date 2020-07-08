package com.travel.travels.service;

import com.travel.travels.entity.Place;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlaceService {

    // 根据省份Id 进行分页查询
    List<Place> findByProvinceIdPage(Integer page,Integer rows, String provinceId);

    // 查询总记录条数
    Integer findByProvinceIdCounts(String provinceId);
}
