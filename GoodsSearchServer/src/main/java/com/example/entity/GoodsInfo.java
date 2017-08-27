package com.example.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

/**
 * Created by echo on 17/4/8.
 */
@Data
@Document(indexName = "goods", type = "info",
        shards = 1, replicas = 0, refreshInterval = "-1")
public class GoodsInfo {

    @Id
    String id;
    String name;
    String goodsUrl;
    String avatarUrl;
    String source;
    double price;
    List<String> synonym;
}
