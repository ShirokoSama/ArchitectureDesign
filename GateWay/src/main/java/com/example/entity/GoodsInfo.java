package com.example.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by echo on 17/4/9.
 */
@Data
public class GoodsInfo {

    String id;
    String name;
    String avatarUrl;
    String goodsUrl;
    String source;
    double price;
    List<String> synonym;

}
