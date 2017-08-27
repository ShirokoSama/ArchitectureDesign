package com.example.ServiceInterface;

import com.example.entity.GoodsInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echo on 17/4/10.
 */
@Component
public class SearchClientHystrix implements SearchClient{
    @Override
    public Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        return -99999;
    }

    @Override
    public GoodsInfo getGoodsInfo(@RequestParam(value = "goodsId") String goodsId) {
        return null;
    }

    @Override
    public List<GoodsInfo> testES(@RequestParam(value = "key") String key) {
        return new ArrayList<>();
    }

    @Override
    public List<GoodsInfo> testESFilter(@RequestParam(value = "key") String key, @RequestParam(name = "filterStrategy") List<String> filterStrategy) {
        return new ArrayList<>();
    }
}
