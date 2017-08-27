package com.example.filter;

import com.example.entity.GoodsInfo;

import java.util.List;

/**
 * Created by echo on 17/4/9.
 */
public interface IFilter {

    List<GoodsInfo> doFilter(List<GoodsInfo> goodsInfoList);
}
