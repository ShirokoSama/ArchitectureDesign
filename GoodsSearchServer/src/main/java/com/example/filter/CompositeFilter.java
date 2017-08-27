package com.example.filter;

import com.example.entity.GoodsInfo;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by echo on 17/4/9.
 */
@Component
public class CompositeFilter implements IFilter{

    protected List<IFilter> filterList = new ArrayList<>();

    public List<GoodsInfo> doFilter(List<GoodsInfo> goodsInfoList){
        return null;
    }

    public void addFilter(IFilter filter){
        this.filterList.add(filter);
    }

    public void addFilters(List<IFilter> filterList){
        this.filterList.addAll(filterList);
    }

    public List<IFilter> getNextFilter(){
        return this.filterList;
    }
}
