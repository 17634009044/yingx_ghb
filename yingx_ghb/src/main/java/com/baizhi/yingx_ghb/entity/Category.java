package com.baizhi.yingx_ghb.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private String id;
    private String cate_name;
    private String levels;
    private String parent_id;

    private List<Category> cate;

    private List<Video> videos;


}
