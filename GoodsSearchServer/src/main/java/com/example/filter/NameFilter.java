package com.example.filter;

import com.example.entity.GoodsInfo;
import com.example.utils.NameComparator;
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
public class NameFilter implements IFilter{

    @Autowired
    NameComparator nameComparator;

    @Override
    public List<GoodsInfo> doFilter(List<GoodsInfo> goodsInfoList) {

        List<GoodsInfo> tempList = new ArrayList<>(goodsInfoList);
        System.out.println("name filter");
        Collections.sort(tempList, nameComparator);

        return tempList;
    }
}
