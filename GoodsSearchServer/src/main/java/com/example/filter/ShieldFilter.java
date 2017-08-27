package com.example.filter;

import com.example.dao.ShieldRepository;
import com.example.entity.GoodsInfo;
import com.example.entity.ShieldInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echo on 17/4/9.
 */
@Component
public class ShieldFilter implements IFilter {

    @Autowired
    ShieldRepository shieldRepository;

    private List<String> shieldList;

    @Override
    public List<GoodsInfo> doFilter(List<GoodsInfo> goodsInfoList) {

        List<GoodsInfo> result = new ArrayList<>();
        initShieldList();

        for(GoodsInfo goodsInfo: goodsInfoList){
            String goodsName = goodsInfo.getName();
            if(!isShield(goodsName)){
                result.add(goodsInfo);
            }
        }

        return result;
    }

    private boolean isShield(String goodsName){

        boolean result = false;

        for(String shieldWord: shieldList){
            if(goodsName.contains(shieldWord)){
                result = true;
                break;
            }
        }
        return result;
    }

    private void initShieldList(){
        if(shieldList == null){
            shieldList = getShieldList();
        }
    }

    private List<String> getShieldList(){
        List<String> shieldList = new ArrayList<>();
        List<ShieldInfo> shieldInfoList = shieldRepository.findAll();
        for(ShieldInfo info: shieldInfoList){
            shieldList.add(info.getWords());
        }

        return shieldList;
    }
}
