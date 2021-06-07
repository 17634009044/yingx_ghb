package com.baizhi.yingx_ghb.service;

import com.baizhi.yingx_ghb.entity.Log;

import java.util.List;
import java.util.Map;

public interface LogService {

    Map<String,Object> queryPage(Integer page, Integer rows);
    void add(Log log);
    List<Log> queryAll();
}
