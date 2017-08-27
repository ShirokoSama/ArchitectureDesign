package com.example.serviceimpl;

import com.example.dao.GoodsInfoRepository;
import com.example.entity.GoodsInfo;
import com.example.filter.*;
import com.example.service.GoodsInfoService;
import com.example.utils.GoodsInfoPipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by echo on 17/4/9.
 */
@Component
public class GoodsInfoServiceImpl implements GoodsInfoService{

    @Autowired
    CompositeFilter compositeFilter;

    @Autowired
    NameFilter nameFilter;

    @Autowired
    PriceFilter priceFilter;

    @Autowired
    ShieldFilter shieldFilter;

    @Autowired
    SourceFilter sourceFilter;

    @Autowired
    GoodsInfoPipe goodsInfoPipe;

    @Autowired
    GoodsInfoRepository goodsInfoRepository;

    private Map<String, IFilter> filterMap;

    @Override
    public List<GoodsInfo> searchGoodsList(String key) {

        List<GoodsInfo> goodsInfoList = goodsInfoRepository.findBySynonym(key, key);
        compositeFilter.addFilter(shieldFilter);
        goodsInfoPipe.setFilter(compositeFilter);

        return goodsInfoPipe.getFilterResult(goodsInfoList);
    }

    @Override
    public List<GoodsInfo> searchGoodsList(String key, List<String> filterStrategy) {

        List<GoodsInfo> goodsInfoList = goodsInfoRepository.findBySynonym(key, key);
        compositeFilter.addFilters(constructFilterList(filterStrategy));
        compositeFilter.addFilter(shieldFilter);
        goodsInfoPipe.setFilter(compositeFilter);

        return goodsInfoPipe.getFilterResult(goodsInfoList);
    }

    @Override
    public GoodsInfo getGoodsInfo(String key) {
        return goodsInfoRepository.findOne(key);
    }

    private List<IFilter> constructFilterList(List<String> filterStrategy){

        if(filterMap == null){
            initFilterMap();
        }
        List<IFilter> filterList = new ArrayList<>();

        for(String filterName: filterStrategy){
            filterList.add(filterMap.get(filterName));
        }

        return filterList;
    }

    private void initFilterMap(){
        filterMap = new TreeMap<>();
        filterMap.put("price", priceFilter);
        filterMap.put("name", nameFilter);
        filterMap.put("source", sourceFilter);
    }
}
