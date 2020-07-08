package com.travel.travels.controller;

import com.travel.travels.entity.Place;
import com.travel.travels.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/place")
@CrossOrigin
public class PlaceController {

    @Autowired
    PlaceService placeService;


    /**
     * 根据景点查询所有
     * @param page
     * @param rows
     * @param provinceId
     * @return
     */
    @GetMapping("/findAllPlace")
    public Map<String,Object> findAllPlace(Integer page, Integer rows, String provinceId){
        HashMap<String,Object> map = new HashMap<>();
        page = page == null? 1 : page;
        rows = rows == null? 4 : rows;
        //景点集合
        List<Place>  places = placeService.findByProvinceIdPage(page,rows,provinceId);

        //处理分页
        Integer counts = placeService.findByProvinceIdCounts(provinceId);
        Integer totalPage = counts % rows ==0 ? counts/rows : counts/rows + 1;

        map.put("page",page);
        map.put("places",places);
        map.put("counts",counts);
        map.put("totalPage",totalPage);

        return map;
    }
}
