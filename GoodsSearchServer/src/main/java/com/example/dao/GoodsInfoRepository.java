package com.example.dao;

import com.example.entity.GoodsInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by echo on 17/4/8.
 */
public interface GoodsInfoRepository extends ElasticsearchRepository<GoodsInfo, String> {

    @Query(
      "{\"bool\" : {\"should\" : [ {\"query\" : {\"match\" :  {\"name\" : \"?0\" }}}, {\"query\" : {\"match\" :  {\"synonym\" : \"?1\"}}} ]}}"
    )
    List<GoodsInfo> findBySynonym(String name, String key);

}
