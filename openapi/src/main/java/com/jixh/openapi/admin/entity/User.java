package com.jixh.openapi.admin.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class User implements Serializable {

    private Integer id;
    private Integer age;
    private String name;

}
