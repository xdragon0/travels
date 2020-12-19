package com.travel.travels.service;

import com.travel.travels.dao.ShareWebDao;
import com.travel.travels.entity.ShareWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShareWebServiceImpl implements ShareWebService{

    @Autowired ShareWebDao shareWebDao;

    @Override
    public List<ShareWeb> findByCurrentPage(Integer currentPage) {

        return shareWebDao.findByCurrentPage(currentPage);
    }

    //查询所有
    @Override
    public Integer findTotals() {
        return shareWebDao.findTotals();
    }
}
