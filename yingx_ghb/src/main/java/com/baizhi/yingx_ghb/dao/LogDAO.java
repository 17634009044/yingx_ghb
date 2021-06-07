package com.baizhi.yingx_ghb.dao;

import com.baizhi.yingx_ghb.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDAO {

    List<Log> queryPage(@Param("begin") Integer begin,@Param("end") Integer end);

    Integer queryCount();

    void add(Log log);

    List<Log> queryAll();

}
