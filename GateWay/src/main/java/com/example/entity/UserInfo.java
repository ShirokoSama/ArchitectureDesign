package com.example.entity;

import lombok.Data;

/**
 * Created by echo on 17/4/10
 */
@Data
public class UserInfo {

    Integer id;
    String name;
    Integer status;

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
