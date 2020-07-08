package com.travel.travels.dao;

import com.travel.travels.entity.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlaceDao extends BaseDao<Place, String> {

    // 根据省份Id 进行分页查询
    List<Place> findByProvinceIdPage(@Param("state") Integer state,
                                     @Param("rows") Integer rows,
                                     @Param("provinceId") String provinceId);

    // 查询总记录条数
    Integer findByProvinceIdCounts(String provinceId);
}
