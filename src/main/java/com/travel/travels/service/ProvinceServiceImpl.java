package com.travel.travels.service;

import com.travel.travels.dao.ProvinceDao;
import com.travel.travels.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //捕获异常时，要想使事务生效，需要手动抛出RuntimeException异常或者添加rollbackFor = Exception.class
public class ProvinceServiceImpl implements ProvinceService{

    @Autowired
    ProvinceDao provinceDao;

    //分页查找
    @Override
    public List<Province> findByPage(Integer page, Integer rows) {
        // 传入的是当前页数, 以及页面显示的数量
        // 所以要根据这两个参数计算从mysql中查询数据要从第几行开始查几条
        int state = (page - 1) * rows; // 计算要查询的数据是从第几条数据开始的
        return provinceDao.findByPage(state,rows);
    }

    /**
     * 通过名称查询
     * @param name
     * @return
     */
    @Override
    public List<Province> findByName(String name) {
        return provinceDao.findByName(name);
    }


    //查询所有
    @Override
    public Integer findTotals() {
        return provinceDao.findTotals();
    }

    //添加省份
    @Override
    public void save(Province province) {
        province.setPlacecounts(0);//新省份添加时景点数为0
        provinceDao.save(province);
    }

    //删除省份
    @Override
    public void delete(String id) {
        provinceDao.delete(id);
    }

    //查询一个省份
    @Override
    public Province findOne(String id) {
        return provinceDao.findOne(id);
    }

    //修改省份
    @Override
    public void update(Province province) {
        provinceDao.update(province);
    }


}
