package com.example.service;

import com.example.entity.GoodsInfo;

import java.util.List;

/**
 * Created by echo on 17/4/9.
 */
public interface GoodsInfoService {
    List<GoodsInfo> searchGoodsList(String key);
    List<GoodsInfo> searchGoodsList(String key, List<String> filterStrategy);
    GoodsInfo getGoodsInfo(String key);
}
