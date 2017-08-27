package com.example.ServiceInterface;

import com.example.entity.GoodsInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by echo on 17/3/28.
 */
@FeignClient(value = "search-service", fallback = SearchClientHystrix.class)
public interface SearchClient {

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    Integer add(@RequestParam(value = "a")Integer a, @RequestParam(value = "b")Integer b);

    @RequestMapping(value = "/goodsInfo", method = RequestMethod.GET)
    GoodsInfo getGoodsInfo(@RequestParam(value = "goodsId")String goodsId);

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    List<GoodsInfo> testES(@RequestParam(value = "key") String key);

    @RequestMapping(value = "/testFilter" ,method = RequestMethod.GET)
    List<GoodsInfo> testESFilter(@RequestParam(value = "key") String key, @RequestParam(name = "filterStrategy") List<String> filterStrategy);
}
