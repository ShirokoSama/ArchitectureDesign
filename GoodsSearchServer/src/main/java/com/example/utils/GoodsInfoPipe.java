package com.example.utils;

import com.example.entity.GoodsInfo;
import com.example.filter.CompositeFilter;
import com.example.filter.IFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echo on 17/4/9.
 */

@Component
public class GoodsInfoPipe {

    private CompositeFilter compositeFilter;

    public void setFilter(CompositeFilter compositeFilter){
        this.compositeFilter = compositeFilter;
    }

    public List<GoodsInfo> getFilterResult(List<GoodsInfo> goodsInfoList){
        return this.doFilter(goodsInfoList, this.compositeFilter);
    }

    private List<GoodsInfo> doFilter(List<GoodsInfo> goodsInfoList, CompositeFilter compositeFilter){

        List<IFilter> filterList = compositeFilter.getNextFilter();
        List<GoodsInfo> result = new ArrayList<>(goodsInfoList);

        for(IFilter filter: filterList){
            result = filter.doFilter(result);

            if(filter instanceof CompositeFilter){
                result = doFilter(result, (CompositeFilter) filter);
            }
        }
        return result;
    }
}
