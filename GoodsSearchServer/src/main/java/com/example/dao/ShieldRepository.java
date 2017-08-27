package com.example.dao;

import com.example.entity.ShieldInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by echo on 17/4/10.
 */
public interface ShieldRepository extends JpaRepository<ShieldInfo, Integer>{
}
