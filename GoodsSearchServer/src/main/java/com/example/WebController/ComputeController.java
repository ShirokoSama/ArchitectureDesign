package com.example.WebController;

import com.example.entity.GoodsInfo;
import com.example.dao.GoodsInfoRepository;
import com.example.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by echo on 17/2/22.
 */
@RestController
public class ComputeController {

    @Autowired
    GoodsInfoService goodsInfoService;

    private final Logger logger = Logger.getLogger(getClass().toString());
    @Autowired
    private DiscoveryClient client;
    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);

        return r;
    }

    @RequestMapping(value = "/goodsInfo", method = RequestMethod.GET)
    public GoodsInfo getGoodsInfo(@RequestParam(value = "goodsId")String goodsId){
        return goodsInfoService.getGoodsInfo(goodsId);
    }

    @RequestMapping(value = "/test" ,method = RequestMethod.GET)
    public List<GoodsInfo> testES(@RequestParam String key){
        return goodsInfoService.searchGoodsList(key);
    }

    @RequestMapping(value = "/testFilter" ,method = RequestMethod.GET)
    public List<GoodsInfo> testESFilter(@RequestParam String key, @RequestParam List<String> filterStrategy){

        for(String s: filterStrategy){
            System.out.println(s);
        }
        return goodsInfoService.searchGoodsList(key, filterStrategy);
    }
}
