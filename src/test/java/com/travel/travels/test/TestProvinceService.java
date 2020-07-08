package com.travel.travels.test;

import com.travel.travels.TravelsApplication;
import com.travel.travels.entity.Province;
import com.travel.travels.service.ProvinceService;
import lombok.experimental.Accessors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = TravelsApplication.class)
@RunWith(SpringRunner.class)
public class TestProvinceService {

    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testfindall(){
        provinceService.findByPage(1,5);
    }

    @Test
    public void test1(){
        stringRedisTemplate.opsForValue().set("name","张三");
    }

}
