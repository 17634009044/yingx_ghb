package com.baizhi.yingx_ghb.service;

import com.baizhi.yingx_ghb.entity.FeedBack;

import java.util.List;
import java.util.Map;

public interface FeedBackService {

    Map<String,Object> queryAll(Integer page, Integer rows);

}
