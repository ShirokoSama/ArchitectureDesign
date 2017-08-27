package com.example.filter;

import com.example.entity.GoodsInfo;
import com.example.utils.PriceAscComparator;
import com.example.utils.PriceDescComparator;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by echo on 17/4/9.
 */
@Component
public class PriceFilter implements IFilter {


    @Autowired
    PriceDescComparator priceDescComparator;

    @Autowired
    PriceAscComparator priceAscComparator;

    @Override
    public List<GoodsInfo> doFilter(List<GoodsInfo> goodsInfoList) {

        List<GoodsInfo> tempList = new ArrayList<>(goodsInfoList);
        Collections.sort(tempList, priceAscComparator);
        System.out.println("price filter");

        return tempList;
    }
}
