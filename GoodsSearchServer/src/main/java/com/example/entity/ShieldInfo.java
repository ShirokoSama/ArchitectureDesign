package com.example.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by echo on 17/4/10.
 */
@Data
@Entity(name = "shields")
public class ShieldInfo {

    @Id
    @GeneratedValue
    private Integer id;

    String words;
}
