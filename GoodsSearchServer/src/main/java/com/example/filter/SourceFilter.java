package com.example.filter;

import com.example.entity.GoodsInfo;
import com.example.utils.SourceComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by echo on 17/4/9.
 */
@Component
public class SourceFilter implements IFilter{

    @Autowired
    SourceComparator sourceComparator;

    @Override
    public List<GoodsInfo> doFilter(List<GoodsInfo> goodsInfoList) {

        List<GoodsInfo> tempList = new ArrayList<>(goodsInfoList);
        System.out.println("source filter");
        Collections.sort(tempList, sourceComparator);

        return tempList;
    }
}
