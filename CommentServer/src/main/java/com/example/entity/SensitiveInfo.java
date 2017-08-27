package com.example.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by echo on 17/4/10.
 */
@Data
@Entity(name = "sensitives")
public class SensitiveInfo {

    @Id
    @GeneratedValue
    Integer id;
    String words;
}
