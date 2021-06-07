package com.baizhi.yingx_ghb.dao;

import com.baizhi.yingx_ghb.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDAO {

    Admin queryName(@Param("username") String username);

    void add(Admin admin);

    void update(Admin admin);

    void delete(String id);

    List<Admin> queryBylevels(@Param("level") String level,@Param("begin") Integer begin,@Param("end") Integer end);

    Integer queryCount();

}
