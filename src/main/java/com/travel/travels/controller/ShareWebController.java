package com.travel.travels.controller;

import com.travel.travels.entity.Province;
import com.travel.travels.entity.ShareWeb;
import com.travel.travels.service.ShareWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
//@RequestMapping("/share")
public class ShareWebController {

    @Autowired
    ShareWebService shareWebService;

    @PostMapping("/share")
    public Map<String,Object> findByCurrentPage(Integer currentPage){
        currentPage = currentPage==null? 1 : currentPage;
        System.out.println(currentPage);
        HashMap<String,Object> map = new HashMap<>();

        //分页查询出当前页面显示的数据
        List<ShareWeb> shareWebs = shareWebService.findByCurrentPage(currentPage);

        //查询总数据条数
        Integer totals = shareWebService.findTotals();
        // 计算总页数
        // 如果总数据条数可以整除每一页数据个数, 说明结果正好为总页数
        // 如果总数据条数无法整除每一页数据个数, 说明总页数需要结果 + 1
        Integer totalPage = totals % 10 == 0 ? totals/10 : totals/10 + 1;

        map.put("shareWebs",shareWebs);
        map.put("currentPage",currentPage);
        map.put("totalPage",totalPage);
        map.put("totals",totals);

        map.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });

        return map;
    }
}
