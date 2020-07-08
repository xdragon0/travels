package com.travel.travels.controller;

import com.travel.travels.entity.Province;
import com.travel.travels.entity.Result;
import com.travel.travels.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    ProvinceService provinceService;

    @GetMapping("/findByPage")
    public Map<String,Object> findByPage(Integer page, Integer rows){
        page = page==null? 1 : page;
        rows = rows==null? 4 : rows;
        System.out.println(page+ " : " + rows);
        HashMap<String,Object> map = new HashMap<>();

        //分页查询出当前页面显示的数据
        List<Province> provinces = provinceService.findByPage(page,rows);

        //查询总数据条数
        Integer totals = provinceService.findTotals();
        // 计算总页数
        // 如果总数据条数可以整除每一页数据个数, 说明结果正好为总页数
        // 如果总数据条数无法整除每一页数据个数, 说明总页数需要结果 + 1
        Integer totalPage = totals % rows == 0 ? totals/rows : totals/rows + 1;

        map.put("provinces",provinces);
        map.put("page",page);
        map.put("totalPage",totalPage);
        map.put("totals",totals);

        map.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });

        return map;

    }

    /**
     * 通过名称查询
     * @param name
     * @return
     */
    @GetMapping("/findByName")
    public List<Province> findByName(String name){
        return  provinceService.findByName(name);
    }


    /**
     * 添加省份
     * @param province
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody Province province){
        Result result = new Result();
        try{
            provinceService.save(province);
            result.setMsg("添加省份成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("添加省份失败！");
        }
        return result;
    }

    /**
     * 删除省份
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Result delete(String id){
        Result result = new Result();
        try{
            provinceService.delete(id);
            result.setMsg("删除省份成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("删除省份失败！");
        }
        return result;
    }

    /**
     * 修改省份
     * @param province
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Province province){
        Result result = new Result();
        try{
            provinceService.update(province);
            result.setMsg("修改省份成功！");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("修改省份失败");
        }
        return result;
    }

    /**
     * 查询一个省份
     * @param id
     * @return
     */
    @GetMapping("/findOne")
    public Province findOne(String id){
        return provinceService.findOne(id);
    }
}
