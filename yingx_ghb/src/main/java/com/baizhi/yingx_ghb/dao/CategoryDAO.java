package com.baizhi.yingx_ghb.dao;

import com.baizhi.yingx_ghb.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDAO {

    //添加
    void add(Category category);

    //修改
    void update(Category category);

    //删除
    void delete(String id);

    //分页
    List<Category> queryAllPage(@Param("levels") String levels,@Param("begin") Integer begin,@Param("end") Integer end);

    //数量
    Integer queryCount();

    Category queryOne(String id);

    List<Category> queryAll(String levels);

    List<Category> queryTwo(String parent_id);
}
