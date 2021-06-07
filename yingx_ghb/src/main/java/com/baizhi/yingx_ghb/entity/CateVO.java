package com.baizhi.yingx_ghb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CateVO {


    private String id;
    private String cateName;
    private String levels;
    private String parentId;

    private List<CateVO> categoryList;
}
