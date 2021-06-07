package com.baizhi.yingx_ghb.service;

import com.baizhi.yingx_ghb.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {

    Admin login(String username);

    void add(Admin admin);

    void update(Admin admin);

    void delete(String id);

    Map<String,Object> queryBylevels(Integer page, Integer rows);
}
