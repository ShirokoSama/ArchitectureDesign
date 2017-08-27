package com.example.dao;

import com.example.entity.CommentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by echo on 17/4/10.
 */
public interface CommentRepository extends JpaRepository<CommentInfo, Integer>{

    List<CommentInfo> findByGoodsId(String goodsId);

    List<CommentInfo> findByUserId(Integer userId);

}
