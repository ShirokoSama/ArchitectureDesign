package com.example.dao;

import com.example.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by echo on 17/4/10.
 */
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
}
