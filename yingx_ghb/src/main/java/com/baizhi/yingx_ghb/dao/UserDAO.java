package com.baizhi.yingx_ghb.dao;

import com.baizhi.yingx_ghb.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO {

    List<User> queryAll(@Param("begin") Integer begin,@Param("end") Integer end);

    Integer queryCount();

    void updateStatus(@Param("id") String id,@Param("status") String status);

    void add(User user);

    void update(User user);

    void delete(String id);

    List<User> queryAlls();
}
