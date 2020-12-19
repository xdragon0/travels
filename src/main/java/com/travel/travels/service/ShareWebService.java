package com.travel.travels.service;

import com.travel.travels.entity.ShareWeb;

import java.util.List;

public interface ShareWebService {
    List<ShareWeb> findByCurrentPage(Integer currentPage);
    //查询总条数
    Integer findTotals();
}
