package com.travel.travels.service;

import com.travel.travels.dao.PlaceDao;
import com.travel.travels.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //捕获异常时，要想使事务生效，需要手动抛出RuntimeException异常或者
              // 添加rollbackFor = Exception.class
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    PlaceDao placeDao;

    /**
     * 景点分页查询
     * @param page
     * @param rows
     * @param provinceId
     * @return
     */
    @Override
    public List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId) {
        Integer state =  (page - 1) * rows;
        return placeDao.findByProvinceIdPage(state,rows,provinceId);
    }

    /**
     * 查询景点总数
     * @param provinceId
     * @return
     */
    @Override
    public Integer findByProvinceIdCounts(String provinceId) {
        return placeDao.findByProvinceIdCounts(provinceId);
    }
}
