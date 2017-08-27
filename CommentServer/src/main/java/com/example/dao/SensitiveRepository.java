package com.example.dao;

import com.example.entity.SensitiveInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by echo on 17/4/10.
 */
public interface SensitiveRepository extends JpaRepository<SensitiveInfo, Integer> {
}
