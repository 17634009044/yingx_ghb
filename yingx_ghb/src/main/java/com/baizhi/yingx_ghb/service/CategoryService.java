package com.baizhi.yingx_ghb.service;

import com.baizhi.yingx_ghb.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {


    //添加
    void add(Category category);

    //修改
    void update(Category category);

    //删除
    void delete(String id);

    //分页
    Map<String,Object> queryAllPage(String levels,Integer page,Integer rows);

    Category queryOne(String id);

    List<Category> queryTwo(String parent_id);
    List<Category> queryAll();
}
